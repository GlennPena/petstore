package com.pena.petstore;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetstoreApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(PetstoreApplication.class);

    String active = System.getenv("SPRING_PROFILES_ACTIVE");
    if (active == null) {
      active = System.getProperty("spring.profiles.active");
    }

    if (active != null && active.contains("prod")) {
      Map<String, Object> defaults = new HashMap<>();
      defaults.put("spring.jpa.hibernate.ddl-auto", "update");
      app.setDefaultProperties(defaults);
    }

    app.run(args);
  }
}
