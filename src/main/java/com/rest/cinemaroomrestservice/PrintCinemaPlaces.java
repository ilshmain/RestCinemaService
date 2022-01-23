package com.rest.cinemaroomrestservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PrintCinemaPlaces {
    AllPlaces allPlaces = new AllPlaces();
    Map<Integer, Integer> purchasePlace = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    ErrorMessage message = new ErrorMessage();
    Stats stats = new Stats();
    List<BuyTicket> tickets = new ArrayList<>();


    @GetMapping("/seats")
    public String printCinemaPlaces() {
        return gson.toJson(allPlaces);
    }

    boolean checkRowColumn(Map<String, Integer> place, RCP rcp) {
        try {
            rcp.setRow(place.get("row"));
            rcp.setColumn(place.get("column"));
            rcp.setPrice(rcp.getRow() <= 4 ? 10 : 8);
        } catch (NullPointerException e) {
            message.setError("Wrong parametr");
            return true;
        }
        if (rcp.getRow() > allPlaces.getTotal_rows() || rcp.getColumn() >allPlaces.getTotal_columns() || rcp.getRow() < 1 ||  rcp.getColumn() < 1) {
            message.setError("The number of a row or a column is out of bounds!");
            return true;
        }
        try {
            if (purchasePlace.get(rcp.getRow()).equals(rcp.getColumn())) {
                message.setError("The ticket has been already purchased!");
                return true;
            }
        } catch (NullPointerException e) {
            purchasePlace.put(rcp.getRow(), rcp.getColumn());
            stats.setCurrent_income(stats.getCurrent_income() + rcp.getPrice());
            stats.setNumber_of_available_seats(stats.getNumber_of_available_seats() - 1);
            stats.setNumber_of_purchased_tickets(stats.getNumber_of_purchased_tickets() +1);
        }
        return false;
    }

    @PostMapping("/purchase")
    ResponseEntity<String> buyTicket(@RequestBody Map<String, Integer> place) {
        RCP rcp = new RCP();

        if (checkRowColumn(place, rcp)) {
            return new ResponseEntity(gson.toJson(message), HttpStatus.BAD_REQUEST);
        }

        BuyTicket ticket = new BuyTicket(UUID.randomUUID(), rcp.getRow(), rcp.getColumn(), rcp.getPrice());
        tickets.add(ticket);
        return new ResponseEntity(gson.toJson(ticket), HttpStatus.OK);
    }

    @PostMapping("/return")
    ResponseEntity<String> returnTicket(@RequestBody Map<String, UUID> place) {
        UUID tocken;
        tocken = place.get("token");

        int count = 0;
        for (BuyTicket elem : tickets) {
            if (elem.compareTocken(tocken)) {
                ReturnTicket returnTick = new ReturnTicket();
                returnTick.setReturnTicket(elem.getTicket());
                purchasePlace.remove(returnTick.getReturnTicket().get("row"),returnTick.getReturnTicket().get("column"));
                stats.setCurrent_income(stats.getCurrent_income() - returnTick.getReturnTicket().get("price"));
                stats.setNumber_of_available_seats(stats.getNumber_of_available_seats() + 1);
                stats.setNumber_of_purchased_tickets(stats.getNumber_of_purchased_tickets() - 1);
                tickets.remove(count);
                return new ResponseEntity(gson.toJson(returnTick), HttpStatus.OK);
            }
            count++;
        }
        message.setError("Wrong token!");
        return new ResponseEntity(gson.toJson(message), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    ResponseEntity<String> stats(@RequestParam(required = false) String password) {
        if (password != null && password.equals("super_secret")) {
            return new ResponseEntity(gson.toJson(stats), HttpStatus.OK);
        } else {
            message.setError("The password is wrong!");
            return new ResponseEntity(gson.toJson(message), HttpStatus.UNAUTHORIZED);
        }
    }
}
