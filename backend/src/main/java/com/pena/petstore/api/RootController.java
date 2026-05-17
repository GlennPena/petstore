package com.pena.petstore.api;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

  @GetMapping("/")
  public Map<String, Object> home() {
    return Map.of(
        "service", "petstore-backend",
        "status", "ok",
        "catalog", "/pena/pets",
        "health", "/actuator/health");
  }
}
