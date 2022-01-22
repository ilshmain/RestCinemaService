package com.rest.cinemaroomrestservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PrintCinemaPlaces {
    AllPlaces allPlaces = new AllPlaces();
    Map<Integer, Integer> purchasePlace = new HashMap<>();


    @GetMapping("/seats")
    public String printCinemaPlaces() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        allPlaces.ShowAvailPlace();
        return gson.toJson(allPlaces);
    }

    @PostMapping("/purchase")
    ResponseEntity<String> buyPlace(@RequestBody Map<String, Integer> place) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Integer row = 0;
        Integer column = 0;
        for (Map.Entry<String, Integer> elem : place.entrySet()) {
            if (elem.getKey().equals("row")) {
                row = elem.getValue();
            } else {
                column = elem.getValue();
            }
        }
        if (row > allPlaces.getTotal_rows() || column >allPlaces.getTotal_columns() || row < 1 || column < 1) {
            ErrorMessage message = new ErrorMessage("The number of a row or a column is out of bounds!");
            return new ResponseEntity(gson.toJson(message), HttpStatus.BAD_REQUEST);

        }
        try {
            if (purchasePlace.get(row).equals(column)) {
                ErrorMessage message = new ErrorMessage("The ticket has been already purchased!");
                return new ResponseEntity(gson.toJson(message), HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException e) {}
        purchasePlace.put(row, column);
        return new ResponseEntity(gson.toJson(allPlaces.ShowBuyTicket(row, column)), HttpStatus.OK);
    }
}
