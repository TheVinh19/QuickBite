package com.quickbite.cartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class CartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }

    @GetMapping("/")
    public String status() {
        return "cart-service is running";
    }

    @GetMapping({"/api/v1/carts/actuator/health", "/actuator/health"})
    public Map<String, Object> health() {
        return Map.of("status", "UP");
    }
}
