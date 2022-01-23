package com.rest.cinemaroomrestservice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class BuyTicket {
    private UUID token;
    private Map<String, Integer> ticket = new LinkedHashMap<>();

    public BuyTicket(UUID token, Integer row, Integer column, Integer price) {
        this.token = token;
        ticket.put("row", row);
        ticket.put("column", column);
        ticket.put("price", price);
    }

    public Map<String, Integer> getTicket() {
        return ticket;
    }

    public void setTicket(Map<String, Integer> ticket) {
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public boolean compareTocken(UUID tock) {
        if (tock.equals(this.token)) {
            return true;
        } else {
            return false;
        }
    }
}
