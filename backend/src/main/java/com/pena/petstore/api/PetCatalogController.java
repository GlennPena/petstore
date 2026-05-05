package com.pena.petstore.api;

import com.pena.petstore.domain.AvailabilityStatus;
import com.pena.petstore.domain.PetCategory;
import com.pena.petstore.domain.dto.PetListingResponse;
import com.pena.petstore.service.PetCatalogService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pena/pets")
public class PetCatalogController {

  private final PetCatalogService petCatalogService;

  public PetCatalogController(PetCatalogService petCatalogService) {
    this.petCatalogService = petCatalogService;
  }

  @GetMapping
  public List<PetListingResponse> list(
      @RequestParam(required = false) String search,
      @RequestParam(required = false) PetCategory category,
      @RequestParam(required = false) BigDecimal minPrice,
      @RequestParam(required = false) BigDecimal maxPrice,
      @RequestParam(required = false) Integer minAge,
      @RequestParam(required = false) Integer maxAge,
      @RequestParam(required = false) AvailabilityStatus availability) {
    return petCatalogService.list(search, category, minPrice, maxPrice, minAge, maxAge, availability);
  }

  @GetMapping("/{id}")
  public PetListingResponse get(@PathVariable Long id) {
    return petCatalogService.getById(id);
  }
}
