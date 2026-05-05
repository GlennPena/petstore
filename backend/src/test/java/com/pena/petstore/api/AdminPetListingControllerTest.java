package com.pena.petstore.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pena.petstore.domain.AvailabilityStatus;
import com.pena.petstore.domain.PetCategory;
import com.pena.petstore.domain.dto.PetListingRequest;
import com.pena.petstore.domain.dto.PetListingResponse;
import com.pena.petstore.service.PetListingAdminService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AdminPetListingController.class)
class AdminPetListingControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  PetListingAdminService petListingAdminService;

  @Test
  void createsPet() throws Exception {
    PetListingRequest request = new PetListingRequest();
    request.setName("Buddy");
    request.setCategory(PetCategory.DOGS);
    request.setAgeMonths(12);
    request.setPrice(BigDecimal.valueOf(100));
    request.setAvailability(AvailabilityStatus.AVAILABLE);
    when(petListingAdminService.create(any(PetListingRequest.class))).thenReturn(new PetListingResponse());

    mockMvc.perform(post("/pena/admin/pets")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated());
  }

  @Test
  void listsAdminPets() throws Exception {
    mockMvc.perform(get("/pena/admin/pets"))
        .andExpect(status().isOk());
  }
}
