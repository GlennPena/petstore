package com.pena.petstore.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.pena.petstore.domain.AvailabilityStatus;
import com.pena.petstore.domain.PetListing;
import com.pena.petstore.domain.PetCategory;
import com.pena.petstore.repository.PetListingRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PetCatalogServiceTest {

  @Mock
  PetListingRepository petListingRepository;

  @InjectMocks
  PetCatalogService petCatalogService;

  @Test
  void returnsPetById() {
    PetListing listing = new PetListing();
    listing.setId(1L);
    listing.setName("Buddy");
    listing.setCategory(PetCategory.DOGS);
    listing.setAgeMonths(12);
    listing.setPrice(BigDecimal.valueOf(100));
    listing.setAvailability(AvailabilityStatus.AVAILABLE);
    when(petListingRepository.findById(1L)).thenReturn(Optional.of(listing));

    assertThat(petCatalogService.getById(1L).getName()).isEqualTo("Buddy");
  }
}
