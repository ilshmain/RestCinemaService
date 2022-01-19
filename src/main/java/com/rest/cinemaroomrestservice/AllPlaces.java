package com.rest.cinemaroomrestservice;

import java.util.*;

public class AllPlaces {
    final private Integer total_rows = 9;
    final private Integer total_columns = 9;
    public List<Map<String, Integer>> available_seats = new ArrayList<>();

    public Integer getTotal_rows() {
        return total_rows;
    }

    public Integer getTotal_columns() {
        return total_columns;
    }

    public List<Map<String, Integer>> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(List<Map<String, Integer>> available_seats) {
        this.available_seats = available_seats;
    }

    public void ShowAvailPlace() {
        for (int i = 1; i <= total_rows; i++) {
            for (int q = 1; q <= total_columns; q++) {
                Map<String, Integer> avail = new LinkedHashMap<>();
                avail.put("row", i);
                avail.put("column", q);
                this.available_seats.add(avail);
            }
        }
    }
}


