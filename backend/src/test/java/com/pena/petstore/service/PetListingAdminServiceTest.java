package com.pena.petstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pena.petstore.domain.AvailabilityStatus;
import com.pena.petstore.domain.PetListing;
import com.pena.petstore.domain.PetCategory;
import com.pena.petstore.domain.dto.PetListingRequest;
import com.pena.petstore.repository.PetListingRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PetListingAdminServiceTest {

  @Mock
  PetListingRepository petListingRepository;

  @InjectMocks
  PetListingAdminService petListingAdminService;

  @Test
  void createsPet() {
    PetListingRequest request = new PetListingRequest();
    request.setName("Buddy");
    request.setCategory(PetCategory.DOGS);
    request.setAgeMonths(12);
    request.setPrice(BigDecimal.valueOf(100));
    request.setAvailability(AvailabilityStatus.AVAILABLE);

    when(petListingRepository.save(org.mockito.ArgumentMatchers.any(PetListing.class))).thenAnswer(invocation -> invocation.getArgument(0));

    assertThat(petListingAdminService.create(request).getName()).isEqualTo("Buddy");
  }
}
