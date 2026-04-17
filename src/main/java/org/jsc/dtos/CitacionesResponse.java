package org.jsc.dtos;

import java.util.List;

public class CitacionesResponse {
    
    public String page;
    public int total;
    public int records;
    public List<Row> rows;

    public static class Row {
        public String id;
        public List<String> cell;
    }

}