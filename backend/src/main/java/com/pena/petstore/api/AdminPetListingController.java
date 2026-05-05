package com.pena.petstore.api;

import com.pena.petstore.domain.dto.PetListingRequest;
import com.pena.petstore.domain.dto.PetListingResponse;
import com.pena.petstore.service.PetListingAdminService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pena/admin/pets")
public class AdminPetListingController {

  private final PetListingAdminService petListingAdminService;

  public AdminPetListingController(PetListingAdminService petListingAdminService) {
    this.petListingAdminService = petListingAdminService;
  }

  @GetMapping
  public List<PetListingResponse> list() {
    return petListingAdminService.listAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PetListingResponse create(@Valid @RequestBody PetListingRequest request) {
    return petListingAdminService.create(request);
  }

  @PutMapping("/{id}")
  public PetListingResponse update(@PathVariable Long id, @Valid @RequestBody PetListingRequest request) {
    return petListingAdminService.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    petListingAdminService.delete(id);
  }
}
