package com.rest.cinemaroomrestservice.Test;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@RestController
public class AddressController {

    final ConcurrentHashMap<Long, String> items = new ConcurrentHashMap<>();
    final String ERROR = "Item wasn't found";

    public void addItems() {
        items.put(55L, "Chair");
        items.put(99L, "Table");
        items.put(345L, "Vase");
    }


    @GetMapping("/items/{id}")
    String getItem(@PathVariable long id) {
        String item = items.get(id);

        return item != null ? item : ERROR;
    }

    @DeleteMapping("/items/{id}")
    public String remove(@PathVariable long id) {
        String item = "Item wasn't found";

        if (items.get(id) == null)
            return item;
        else {
            item = items.get(id);
            items.remove(id);
            return item;
        }
    }
}
//http://localhost:8080/addresses?name=Bob&address=123 Younge Street
//http://localhost:8080/addresses