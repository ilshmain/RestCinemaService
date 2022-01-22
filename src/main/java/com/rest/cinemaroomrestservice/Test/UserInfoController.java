package com.rest.cinemaroomrestservice.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController {

    @PostMapping("/api/products")
    public String userStatus(@RequestBody UserInfo user) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(user);
    }
}
