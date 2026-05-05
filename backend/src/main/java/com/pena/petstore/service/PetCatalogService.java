package com.pena.petstore.service;

import com.pena.petstore.domain.AvailabilityStatus;
import com.pena.petstore.domain.PetCategory;
import com.pena.petstore.domain.PetListing;
import com.pena.petstore.domain.dto.PetListingRequest;
import com.pena.petstore.domain.dto.PetListingResponse;
import com.pena.petstore.repository.PetListingRepository;
import jakarta.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PetCatalogService {

  private final PetListingRepository petListingRepository;

  public PetCatalogService(PetListingRepository petListingRepository) {
    this.petListingRepository = petListingRepository;
  }

  public List<PetListingResponse> list(String search, PetCategory category, BigDecimal minPrice,
      BigDecimal maxPrice, Integer minAge, Integer maxAge, AvailabilityStatus availability) {
    Specification<PetListing> specification = buildSpecification(search, category, minPrice, maxPrice, minAge, maxAge,
        availability);
    return petListingRepository.findAll(specification).stream().map(PetListingResponse::fromEntity).toList();
  }

  public PetListingResponse getById(Long id) {
    PetListing listing = petListingRepository.findById(id)
        .orElseThrow(() -> new PetListingNotFoundException(id));
    return PetListingResponse.fromEntity(listing);
  }

  private Specification<PetListing> buildSpecification(String search, PetCategory category, BigDecimal minPrice,
      BigDecimal maxPrice, Integer minAge, Integer maxAge, AvailabilityStatus availability) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (search != null && !search.isBlank()) {
        String likeValue = "%" + search.toLowerCase() + "%";
        predicates.add(criteriaBuilder.or(
            criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), likeValue),
            criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), likeValue)));
      }
      if (category != null) {
        predicates.add(criteriaBuilder.equal(root.get("category"), category));
      }
      if (minPrice != null) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
      }
      if (maxPrice != null) {
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
      }
      if (minAge != null) {
        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("ageMonths"), minAge));
      }
      if (maxAge != null) {
        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("ageMonths"), maxAge));
      }
      if (availability != null) {
        predicates.add(criteriaBuilder.equal(root.get("availability"), availability));
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }
}
