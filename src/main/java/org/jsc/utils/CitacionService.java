package org.jsc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

import org.jsc.dtos.Citacion;
import org.jsc.dtos.CitacionMapper;
import org.jsc.dtos.CitacionesResponse;

public class CitacionService {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<Citacion> parse(String json) throws Exception {

        CitacionesResponse response =
                mapper.readValue(json, CitacionesResponse.class);

        return response.rows.stream()
                .map(CitacionMapper::map)
                .toList();
    }
}