package com.rest.cinemaroomrestservice;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReturnTicket {
    private Map<String, Integer> returned_ticket = new LinkedHashMap<>();

    public ReturnTicket() {
    }

    public Map<String, Integer> getReturnTicket() {
        return returned_ticket;
    }

    public void setReturnTicket(Map<String, Integer> returnTicket) {
        this.returned_ticket = returnTicket;
    }
}

