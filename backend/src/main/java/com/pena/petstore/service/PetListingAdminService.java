package com.pena.petstore.service;

import com.pena.petstore.domain.PetListing;
import com.pena.petstore.domain.dto.PetListingRequest;
import com.pena.petstore.domain.dto.PetListingResponse;
import com.pena.petstore.repository.PetListingRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PetListingAdminService {

  private final PetListingRepository petListingRepository;

  public PetListingAdminService(PetListingRepository petListingRepository) {
    this.petListingRepository = petListingRepository;
  }

  public List<PetListingResponse> listAll() {
    return petListingRepository.findAll().stream().map(PetListingResponse::fromEntity).toList();
  }

  public PetListingResponse create(PetListingRequest request) {
    PetListing listing = new PetListing();
    apply(request, listing);
    return PetListingResponse.fromEntity(petListingRepository.save(listing));
  }

  public PetListingResponse update(Long id, PetListingRequest request) {
    PetListing listing = petListingRepository.findById(id)
        .orElseThrow(() -> new PetListingNotFoundException(id));
    apply(request, listing);
    return PetListingResponse.fromEntity(petListingRepository.save(listing));
  }

  public void delete(Long id) {
    if (!petListingRepository.existsById(id)) {
      throw new PetListingNotFoundException(id);
    }
    petListingRepository.deleteById(id);
  }

  private void apply(PetListingRequest request, PetListing listing) {
    listing.setName(request.getName());
    listing.setCategory(request.getCategory());
    listing.setAgeMonths(request.getAgeMonths());
    listing.setPrice(request.getPrice());
    listing.setAvailability(request.getAvailability());
    listing.setDescription(request.getDescription());
    listing.setImageUrl(request.getImageUrl());
  }
}
