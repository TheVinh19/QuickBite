package com.quickbite.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @GetMapping("/")
    public String status() {
        return "user-service is running";
    }

    @GetMapping({"/api/v1/users/actuator/health", "/actuator/health"})
    public Map<String, Object> health() {
        return Map.of("status", "UP");
    }
}
