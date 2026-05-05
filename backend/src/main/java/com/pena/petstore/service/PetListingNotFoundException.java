package com.pena.petstore.service;

public class PetListingNotFoundException extends RuntimeException {

  public PetListingNotFoundException(Long id) {
    super("Pet listing not found: " + id);
  }
}
