# Tasks: Petstore Commerce

**Input**: Design documents from `/specs/001-petstore-commerce/`
**Prerequisites**: plan.md (required), spec.md (required for user stories), research.md, data-model.md, contracts/

**Tests**: Automated test tasks are included because the constitution requires coverage for browsing, filtering,
saved-item, and CRUD commerce flows.

**Organization**: Tasks are grouped by user story to enable independent implementation and testing of each story.

## Format: `[ID] [P?] [Story] Description`

- **[P]**: Can run in parallel (different files, no dependencies)
- **[Story]**: Which user story this task belongs to (e.g., US1, US2, US3)
- Include exact file paths in descriptions

---

## Phase 1: Setup (Shared Infrastructure)

**Purpose**: Project initialization and baseline structure for backend and frontend

- [X] T001 Create the backend and frontend project directories per the plan in backend/ and frontend/
- [X] T002 Initialize the Spring Boot backend build and core dependencies in backend/pom.xml
- [X] T003 Initialize the React Vite frontend build, Tailwind CSS, and MUI dependencies in frontend/package.json, frontend/vite.config.js, and frontend/tailwind.config.js
- [X] T004 [P] Add local container and compose setup in docker-compose.yml, backend/Dockerfile, and frontend/Dockerfile
- [X] T005 [P] Configure backend and frontend test tooling in backend/pom.xml and frontend/package.json

---

## Phase 2: Foundational (Blocking Prerequisites)

**Purpose**: Core application plumbing that must exist before any user story can be completed

**Checkpoint**: Backend application bootstrap, data access foundation, and frontend shell are ready

- [X] T006 Create the Spring Boot application entry point and base package structure in backend/src/main/java/com/pena/petstore/PetstoreApplication.java and backend/src/main/java/com/pena/petstore/config/
- [X] T007 [P] Create the initial PostgreSQL migration baseline in backend/src/main/resources/db/migration/V1__init.sql
- [X] T008 [P] Define the shared pet listing domain model and JPA repository in backend/src/main/java/com/pena/petstore/domain/PetListing.java and backend/src/main/java/com/pena/petstore/repository/PetListingRepository.java
- [X] T009 Add API error handling and request validation infrastructure in backend/src/main/java/com/pena/petstore/api/ and backend/src/main/java/com/pena/petstore/config/
- [X] T010 Set up the frontend application shell, routing, and shared layout in frontend/src/App.jsx, frontend/src/main.jsx, and frontend/src/components/layout/
- [X] T011 Configure shared frontend styling and provider setup for Tailwind and MUI in frontend/src/styles/ and frontend/src/components/AppProviders.jsx

---

## Phase 3: User Story 1 - Browse the pet catalog (Priority: P1)

**Goal**: Shoppers can open the storefront, browse a responsive gallery, and view a pet's detail page

**Independent Test**: A user can open the storefront on mobile or desktop, see pets rendered in a responsive gallery, and open a detail page for any visible pet

- [X] T012 [P] [US1] Write catalog browsing contract and integration tests in backend/src/test/java/com/pena/petstore/api/PetCatalogControllerTest.java and frontend/src/pages/__tests__/CatalogPage.test.jsx
- [X] T013 [P] [US1] Implement the public pet catalog read API for listing and detail retrieval in backend/src/main/java/com/pena/petstore/api/PetCatalogController.java
- [X] T014 [P] [US1] Implement the catalog read service and DTO mapping in backend/src/main/java/com/pena/petstore/service/PetCatalogService.java and backend/src/main/java/com/pena/petstore/domain/dto/
- [X] T015 [P] [US1] Build the responsive pet gallery page in frontend/src/pages/CatalogPage.jsx and frontend/src/components/PetCard.jsx
- [X] T016 [P] [US1] Build the pet detail page and route wiring in frontend/src/pages/PetDetailPage.jsx and frontend/src/App.jsx
- [X] T017 [US1] Wire the storefront to the catalog API in frontend/src/services/petsApi.js and frontend/src/pages/CatalogPage.jsx
- [X] T018 [US1] Add loading, empty-state, and availability presentation rules in frontend/src/components/EmptyState.jsx and frontend/src/components/PetCard.jsx

---

## Phase 4: User Story 2 - Search, filter, and save pets (Priority: P2)

**Goal**: Shoppers can search and filter pets, then add or remove pets from a cart or wishlist

**Independent Test**: A user can narrow the catalog by type, price, age, and availability, then save and remove pets from a cart or wishlist without breaking catalog browsing

- [X] T019 [P] [US2] Write search, filter, and saved-item tests in backend/src/test/java/com/pena/petstore/service/PetCatalogServiceTest.java and frontend/src/features/cart/__tests__/cartStorage.test.jsx
- [X] T020 [P] [US2] Extend catalog querying for search and filter parameters in backend/src/main/java/com/pena/petstore/service/PetCatalogService.java and backend/src/main/java/com/pena/petstore/repository/PetListingRepository.java
- [X] T021 [P] [US2] Add catalog filter controls in frontend/src/components/CatalogFilters.jsx
- [X] T022 [US2] Connect the filter state and query-string behavior in frontend/src/pages/CatalogPage.jsx and frontend/src/services/petsApi.js
- [X] T023 [P] [US2] Implement cart and wishlist state management in frontend/src/features/cart/ and frontend/src/features/wishlist/
- [X] T024 [US2] Add cart and wishlist actions to the pet card and detail view in frontend/src/components/PetCard.jsx and frontend/src/pages/PetDetailPage.jsx
- [X] T025 [US2] Add local persistence for cart and wishlist selections in frontend/src/features/cart/cartStorage.js and frontend/src/features/wishlist/wishlistStorage.js

---

## Phase 5: User Story 3 - Manage pet listings through the API (Priority: P3)

**Goal**: Store operators can create, view, update, and delete pet listings through the REST API

**Independent Test**: An operator can use the API to create, update, and delete a pet listing and see the catalog reflect the change

- [X] T026 [P] [US3] Write admin CRUD and validation tests in backend/src/test/java/com/pena/petstore/api/AdminPetListingControllerTest.java and backend/src/test/java/com/pena/petstore/service/PetListingAdminServiceTest.java
- [X] T027 [P] [US3] Implement the admin pet listing controller for create, update, and delete operations in backend/src/main/java/com/pena/petstore/api/AdminPetListingController.java
- [X] T028 [P] [US3] Implement the admin listing service methods in backend/src/main/java/com/pena/petstore/service/PetListingAdminService.java
- [X] T029 [P] [US3] Add creation and update validation rules plus catalog availability enforcement in backend/src/main/java/com/pena/petstore/domain/dto/
- [X] T030 [US3] Wire the admin API to the repository and database changes in backend/src/main/java/com/pena/petstore/repository/PetListingRepository.java and backend/src/main/resources/db/migration/
- [X] T031 [P] [US3] Build the admin listing management page and form in frontend/src/pages/AdminPetManagementPage.jsx and frontend/src/components/AdminPetForm.jsx
- [X] T032 [US3] Connect the admin page to the CRUD API in frontend/src/services/adminPetsApi.js

---

## Phase 6: Polish & Cross-Cutting Concerns

**Purpose**: Finish deployment and repository-level concerns that affect multiple stories

- [X] T033 [P] Add Render deployment configuration and environment examples in render.yaml and backend/src/main/resources/application-prod.properties
- [X] T034 [P] Finalize Docker runtime configuration for backend and frontend in backend/Dockerfile, frontend/Dockerfile, and docker-compose.yml
- [X] T035 Update feature quickstart and operational notes in specs/001-petstore-commerce/quickstart.md
- [X] T036 Review and tighten the public API contract documentation in specs/001-petstore-commerce/contracts/pet-listings-api.md

---

## Dependencies & Execution Order

### Phase Dependencies

- **Setup (Phase 1)**: No dependencies - can start immediately
- **Foundational (Phase 2)**: Depends on Setup completion - blocks all user stories
- **User Stories (Phase 3+)**: Depend on Foundational completion
- **Polish (Phase 6)**: Depends on the desired user stories being complete

### User Story Dependencies

- **User Story 1 (P1)**: Can start after Foundational tasks finish - no dependency on other stories
- **User Story 2 (P2)**: Can start after Foundational tasks finish - may build on US1 UI, but should remain independently testable
- **User Story 3 (P3)**: Can start after Foundational tasks finish - may reuse shared domain and API infrastructure

### Within Each User Story

- Foundational backend and frontend plumbing first
- Shared read model before UI wiring
- UI composition before polish and persistence details
- Story complete before moving to the next priority

### Parallel Opportunities

- Setup tasks marked [P] can run in parallel
- Foundational tasks marked [P] can run in parallel once the backend and frontend skeletons exist
- US1 implementation tasks marked [P] can run in parallel across API, service, and UI files
- US2 implementation tasks marked [P] can run in parallel across filter UI and state management files
- US3 implementation tasks marked [P] can run in parallel across admin API and admin UI files

---

## Parallel Example: User Story 1

```text
Task: "Implement the public pet catalog read API for listing and detail retrieval in backend/src/main/java/com/pena/petstore/api/PetCatalogController.java"
Task: "Implement the catalog read service and DTO mapping in backend/src/main/java/com/pena/petstore/service/PetCatalogService.java and backend/src/main/java/com/pena/petstore/domain/dto/"
Task: "Build the responsive pet gallery page in frontend/src/pages/CatalogPage.jsx and frontend/src/components/PetCard.jsx"
Task: "Build the pet detail page and route wiring in frontend/src/pages/PetDetailPage.jsx and frontend/src/App.jsx"
```

---

## Implementation Strategy

### MVP First

1. Complete Phase 1: Setup
2. Complete Phase 2: Foundational
3. Complete Phase 3: User Story 1
4. Validate the browsing experience end to end

### Incremental Delivery

1. Deliver storefront browsing first
2. Add search, filters, cart, and wishlist next
3. Add admin CRUD last
4. Finish with deployment and documentation cleanup

### Parallel Team Strategy

1. One developer can own backend setup and domain plumbing
2. One developer can own frontend shell and catalog UI
3. One developer can own admin CRUD and deployment polish once the foundation is ready
