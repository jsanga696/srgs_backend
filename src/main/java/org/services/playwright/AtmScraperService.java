package org.services.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.Cookie;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.RequestOptions;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jsc.dtos.Citacion;
import org.jsc.dtos.VehiculoCitaciones;
import org.jsc.utils.CitacionService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.services.VehiculoService;

@ApplicationScoped
public class AtmScraperService {

    @Inject
    VehiculoService vehiculoService;

    @ConfigProperty(name = "scraper.headless", defaultValue = "true")
    boolean headless;

    CitacionService citacionService = new CitacionService();

    public VehiculoCitaciones consultar(String placa) {

        VehiculoCitaciones ret = new VehiculoCitaciones();
        List<Citacion> listTotal = new ArrayList<Citacion>();
        List<Citacion> list;
        String psPersona = "";

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true)
            );

            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            // 1. abrir sesión
            page.navigate("https://consultas.atm.gob.ec/PortalWEB/paginas/clientes/clp_criterio_consulta.jsp");
            page.waitForLoadState();

            page.selectOption("#pstipo_identificacion_", "PLA");
            page.fill("#psidentificacion_", placa);
            page.click("#consultar");

            page.waitForTimeout(3000);

            Document doc = Jsoup.parse(page.content());

            Element iframe = doc.getElementById("iframe_estado_cuenta");

            String src = iframe.attr("src");

            String query = src.split("\\?")[1];

            psPersona = Arrays.stream(query.split("&"))
                .map(param -> param.split("="))
                .filter(pair -> pair[0].equals("ps_persona"))
                .map(pair -> pair[1])
                .findFirst()
                .orElse(null);

            String marca = doc.select("td:contains(Marca:) + td").text();
            String color = doc.select("td:contains(Color:) + td").text();
            String modelo = doc.select("td:contains(Modelo:) + td").text();
            String anio = doc.select("td:contains(Año de Matrícula:) + td").text();
            String fechaMatricula = doc.select("td:contains(Fecha de Matrícula:) + td").text();
            String fechaCaducidad = doc.select("td:contains(Fecha de Caducidad:) + td").text();

            ret.setPlaca(placa);
            ret.setMarca(marca);
            ret.setColor(color);
            ret.setModelo(modelo);
            ret.setAnio_fabricacion(anio);
            ret.setFechaMatricula(fechaMatricula);
            ret.setFechaCaducidad(fechaCaducidad);
            
            List<Cookie> cookies = context.cookies();

            String cookieHeader = cookies.stream()
                    .map(c -> c.name + "=" + c.value)
                    .reduce((a, b) -> a + "; " + b)
                    .orElse("");

            String url = "https://consultas.atm.gob.ec/PortalWEB/paginas/clientes/clp_json_citaciones.jsp"
                    + "?ps_opcion=G"
                    + "&ps_id_persona=" + psPersona
                    + "&ps_placa=" + placa
                    + "&ps_identificacion=" + placa
                    + "&ps_tipo_identificacion=PLA"
                    + "&_search=false"
                    + "&rows=17"
                    + "&page=1"
                    + "&sidx=fecha_emision"
                    + "&sord=desc";

            APIResponse response = page.request().get(url, RequestOptions.create()
                    .setHeader("Cookie", cookieHeader)
                    .setHeader("X-Requested-With", "XMLHttpRequest")
                    .setHeader("Accept", "application/json"));

            String json = response.text();

            list = citacionService.parse(json);
            
            if(list != null){
                list.stream().forEach(obj -> obj.setTipo("G"));
                listTotal.addAll(list);
            }

            //

            url = "https://consultas.atm.gob.ec/PortalWEB/paginas/clientes/clp_json_citaciones.jsp"
                    + "?ps_opcion=P"
                    + "&ps_id_persona=" + psPersona
                    + "&ps_placa=" + placa
                    + "&ps_identificacion=" + placa
                    + "&ps_tipo_identificacion=PLA"
                    + "&_search=false"
                    + "&rows=17"
                    + "&page=1"
                    + "&sidx=fecha_emision"
                    + "&sord=desc";

            response = page.request().get(url, RequestOptions.create()
                    .setHeader("Cookie", cookieHeader)
                    .setHeader("X-Requested-With", "XMLHttpRequest")
                    .setHeader("Accept", "application/json"));

            json = response.text();

            list = citacionService.parse(json);
            
            if(list != null){
                list.stream().forEach(obj -> obj.setTipo("P"));
                listTotal.addAll(list);
            }

            //

            url = "https://consultas.atm.gob.ec/PortalWEB/paginas/clientes/clp_json_citaciones.jsp"
                    + "?ps_opcion=A"
                    + "&ps_id_persona=" + psPersona
                    + "&ps_placa=" + placa
                    + "&ps_identificacion=" + placa
                    + "&ps_tipo_identificacion=PLA"
                    + "&_search=false"
                    + "&rows=17"
                    + "&page=1"
                    + "&sidx=fecha_emision"
                    + "&sord=desc";

            response = page.request().get(url, RequestOptions.create()
                    .setHeader("Cookie", cookieHeader)
                    .setHeader("X-Requested-With", "XMLHttpRequest")
                    .setHeader("Accept", "application/json"));

            json = response.text();

            list = citacionService.parse(json);
            
            if(list != null){
                list.stream().forEach(obj -> obj.setTipo("A"));
                listTotal.addAll(list);
            }

            ret.setCitaciones(listTotal);

            browser.close();

            vehiculoService.guardarVehiculoConCitaciones(ret);

            return ret;
        }catch(Exception e){
            System.out.print(e.getMessage());
            return new VehiculoCitaciones();
        }   
    }

    // helper simple
    private String extract(String text, String start, String end) {
        int i = text.indexOf(start);
        if (i == -1) return null;
        int j = text.indexOf(end, i + start.length());
        if (j == -1) return null;
        return text.substring(i + start.length(), j).replaceAll("[^0-9]", "");
    }
}