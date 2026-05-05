# Research: Petstore Commerce

## 1. Backend Stack

Decision: Use Java 17+ with Spring Boot 3 and Spring Data JPA backed by PostgreSQL.

Rationale: This aligns with the approved constitution, gives a mature REST and persistence stack, and supports
clear separation between API, service, and data access layers.

Alternatives considered: Direct SQL access, a lighter Java framework, or a different relational database.

## 2. Frontend Stack

Decision: Use React with Vite, Tailwind CSS, and MUI.

Rationale: Vite keeps local development fast, Tailwind is efficient for responsive layout work, and MUI supplies
accessible components for structured interactions such as filters, dialogs, and listing controls.

Alternatives considered: Plain CSS only, a different React build tool, or a fully custom component system.

## 3. API Design

Decision: Expose REST endpoints under the `/pena` prefix with resource-oriented routes for pet listings and related
shopping interactions.

Rationale: The constitution requires the `/pena` namespace, and resource-oriented routes keep the storefront and
admin actions predictable for both the frontend and future clients.

Alternatives considered: Non-namespaced routes, RPC-style endpoints, or GraphQL.

## 4. Data Model Strategy

Decision: Model pet listings as the core catalog entity with category, price, age, availability, and descriptive
fields, and keep cart/wishlist state separate from the listing itself.

Rationale: This keeps catalog data canonical while allowing user-facing saved-item flows to evolve independently.

Alternatives considered: Embedding cart or wishlist state inside the listing record, or collapsing all commerce data
into a single table.

## 5. Deployment and Environment

Decision: Target Render free-tier services and use Docker for local parity.

Rationale: Docker keeps local and deployable environments close together, while Render free-tier constraints favor a
stateless web app with environment-based configuration and a managed PostgreSQL service.

Alternatives considered: Self-hosted infrastructure, long-running background workers, or stateful application nodes.

## 6. Testing Strategy

Decision: Validate backend behavior with service and API tests, then verify the storefront through focused UI tests
for browsing, filtering, and saved-item flows.

Rationale: The feature crosses UI, API, and database boundaries, so boundary-focused testing gives the best safety
without overcommitting to low-value coverage.

Alternatives considered: Manual-only verification, unit tests only, or broad end-to-end coverage for every path.