package com.pena.petstore.domain.dto;

import com.pena.petstore.domain.AvailabilityStatus;
import com.pena.petstore.domain.PetCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PetListingRequest {

  @NotBlank
  private String name;

  @NotNull
  private PetCategory category;

  @NotNull
  @Min(0)
  private Integer ageMonths;

  @NotNull
  @DecimalMin("0.00")
  private BigDecimal price;

  @NotNull
  private AvailabilityStatus availability;

  private String description;
  private String imageUrl;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public PetCategory getCategory() {
    return category;
  }

  public void setCategory(PetCategory category) {
    this.category = category;
  }

  public Integer getAgeMonths() {
    return ageMonths;
  }

  public void setAgeMonths(Integer ageMonths) {
    this.ageMonths = ageMonths;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public AvailabilityStatus getAvailability() {
    return availability;
  }

  public void setAvailability(AvailabilityStatus availability) {
    this.availability = availability;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
