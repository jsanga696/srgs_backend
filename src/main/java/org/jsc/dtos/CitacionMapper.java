package org.jsc.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CitacionMapper {
    
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Citacion map(CitacionesResponse.Row row) {

        Citacion c = new Citacion();

        c.id = row.id;

        // 👇 índices reales de tu JSON
        c.placa = row.cell.get(3);
        c.documento = row.cell.get(4);

        c.fechaEmision = CitacionMapper.parseDate(row.cell.get(5));
        c.fechaVencimiento = CitacionMapper.parseDate(row.cell.get(6));

        c.entidad = row.cell.get(7);

        // valor (ojo: a veces viene "0" o decimal)
        c.valor = new BigDecimal(row.cell.get(12));

        // descripción infracción
        c.descripcion = row.cell.get(18);


        return c;
    }

    private static LocalDateTime parseDate(String value) {

        if (value == null || value.isBlank()) return null;

        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            return LocalDateTime.parse(value, f1);
        } catch (Exception e) {
            return LocalDateTime.parse(value, f2);
        }
    }
}
