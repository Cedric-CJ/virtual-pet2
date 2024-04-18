package com.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/home")
    public Map<String, Object> homeData() {
        return Map.of(
                "username", "PixelUser",
                "daysAlive", 45,
                "stats", Map.of(
                        "Hunger", 100,
                        "Trinken", 100,
                        "Energie", 100,
                        "Komfort", 100
                )
        );
    }
}