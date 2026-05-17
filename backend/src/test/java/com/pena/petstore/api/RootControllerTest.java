package com.pena.petstore.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RootController.class)
class RootControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void returnsServiceStatusAtRoot() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.service").value("petstore-backend"))
        .andExpect(jsonPath("$.status").value("ok"))
        .andExpect(jsonPath("$.catalog").value("/pena/pets"));
  }
}
