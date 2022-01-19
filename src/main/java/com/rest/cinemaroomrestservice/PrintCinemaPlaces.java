package com.rest.cinemaroomrestservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrintCinemaPlaces {

    @GetMapping("/seats")
    public String printCinema() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        AllPlaces allPlaces = new AllPlaces();
        allPlaces.ShowAvailPlace();
        return gson.toJson(allPlaces);
    }
}
