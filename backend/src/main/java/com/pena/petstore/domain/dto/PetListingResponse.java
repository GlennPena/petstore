package com.pena.petstore.domain.dto;

import com.pena.petstore.domain.AvailabilityStatus;
import com.pena.petstore.domain.PetListing;
import com.pena.petstore.domain.PetCategory;
import java.math.BigDecimal;
import java.time.Instant;

public class PetListingResponse {

  private Long id;
  private String name;
  private PetCategory category;
  private Integer ageMonths;
  private BigDecimal price;
  private AvailabilityStatus availability;
  private String description;
  private String imageUrl;
  private Instant createdAt;
  private Instant updatedAt;

  public static PetListingResponse fromEntity(PetListing listing) {
    PetListingResponse response = new PetListingResponse();
    response.id = listing.getId();
    response.name = listing.getName();
    response.category = listing.getCategory();
    response.ageMonths = listing.getAgeMonths();
    response.price = listing.getPrice();
    response.availability = listing.getAvailability();
    response.description = listing.getDescription();
    response.imageUrl = listing.getImageUrl();
    response.createdAt = listing.getCreatedAt();
    response.updatedAt = listing.getUpdatedAt();
    return response;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public PetCategory getCategory() {
    return category;
  }

  public Integer getAgeMonths() {
    return ageMonths;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public AvailabilityStatus getAvailability() {
    return availability;
  }

  public String getDescription() {
    return description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }
}
