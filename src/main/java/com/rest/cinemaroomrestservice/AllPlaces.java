package com.rest.cinemaroomrestservice;

import java.util.*;

public class AllPlaces {
    final private Integer total_rows = 9;
    final private Integer total_columns = 9;
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

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
        Integer price;

        if (this.available_seats.isEmpty()) {
            for (int i = 1; i <= total_rows; i++) {
                for (int q = 1; q <= total_columns; q++) {
                    Map<String, Integer> avail = new LinkedHashMap<>();
                    price = i <= 4 ? 10 : 8;
                    avail.put("row", i);
                    avail.put("column", q);
                    avail.put("price", price);
                    this.available_seats.add(avail);
                }
            }
        }
    }

    public Map<String, Integer> ShowBuyTicket(Integer row, Integer column) {
        Integer price;
        price = row <= 4 ? 10 : 8;

        Map<String, Integer> avail = new LinkedHashMap<>();
        avail.put("row", row);
        avail.put("column", column);
        avail.put("price", price);
        return avail;
    }
}


