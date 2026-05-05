package com.pena.petstore.config;

import com.pena.petstore.domain.AvailabilityStatus;
import com.pena.petstore.domain.PetCategory;
import com.pena.petstore.domain.PetListing;
import com.pena.petstore.repository.PetListingRepository;
import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

  @Bean
  CommandLineRunner initData(PetListingRepository repository) {
    return args -> {
      if (repository.count() == 0) {
        repository.save(createPet("Buddy", PetCategory.DOGS, 14, 499.00, AvailabilityStatus.AVAILABLE,
            "Friendly golden retriever puppy.",
            "https://loremflickr.com/400/300/dog,puppy"));
        repository.save(createPet("Mittens", PetCategory.CATS, 9, 299.00, AvailabilityStatus.AVAILABLE,
            "Curious house cat with a calm temperament.",
            "https://loremflickr.com/400/300/cat"));
        repository.save(createPet("Rio", PetCategory.BIRDS, 6, 149.00, AvailabilityStatus.AVAILABLE,
            "Colorful parakeet with a playful chirp.",
            "https://loremflickr.com/400/300/bird,parrot"));
        repository.save(createPet("Bubbles", PetCategory.FISHES, 3, 39.00, AvailabilityStatus.UNAVAILABLE,
            "Freshwater fish currently not available.",
            "https://loremflickr.com/400/300/fish"));
        repository.save(createPet("Luna", PetCategory.CATS, 11, 349.00, AvailabilityStatus.AVAILABLE,
            "Affectionate cat suited for apartment living.",
            "https://loremflickr.com/400/300/cat,cute"));
        repository.save(createPet("Kiki", PetCategory.BIRDS, 8, 179.00, AvailabilityStatus.AVAILABLE,
            "Parrot with a bright plumage and social personality.",
            "https://loremflickr.com/400/300/parrot"));
      }
    };
  }

  private PetListing createPet(String name, PetCategory category, Integer ageMonths, Double price,
      AvailabilityStatus availability, String description, String imageUrl) {
    PetListing pet = new PetListing();
    pet.setName(name);
    pet.setCategory(category);
    pet.setAgeMonths(ageMonths);
    pet.setPrice(BigDecimal.valueOf(price));
    pet.setAvailability(availability);
    pet.setDescription(description);
    pet.setImageUrl(imageUrl);
    return pet;
  }
}
