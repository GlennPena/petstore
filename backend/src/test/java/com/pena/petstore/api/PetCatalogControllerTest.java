package com.pena.petstore.api;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.pena.petstore.domain.AvailabilityStatus;
import com.pena.petstore.domain.PetCategory;
import com.pena.petstore.domain.PetListing;
import com.pena.petstore.domain.dto.PetListingResponse;
import com.pena.petstore.service.PetCatalogService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PetCatalogController.class)
class PetCatalogControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  PetCatalogService petCatalogService;

  @Test
  void listsPets() throws Exception {
    when(petCatalogService.list(null, null, null, null, null, null, null)).thenReturn(List.of(samplePet()));

    mockMvc.perform(get("/pena/pets"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Buddy"))
        .andExpect(jsonPath("$[0].category").value("DOGS"))
        .andExpect(jsonPath("$[0].availability").value("AVAILABLE"));

    verify(petCatalogService).list(null, null, null, null, null, null, null);
  }

  private PetListingResponse samplePet() {
    PetListing listing = new PetListing();
    listing.setId(1L);
    listing.setName("Buddy");
    listing.setCategory(PetCategory.DOGS);
    listing.setAgeMonths(12);
    listing.setPrice(BigDecimal.valueOf(499.00));
    listing.setAvailability(AvailabilityStatus.AVAILABLE);
    listing.setDescription("Friendly puppy");
    return PetListingResponse.fromEntity(listing);
  }
}
