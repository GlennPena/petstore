# Quickstart: Petstore Commerce

## Prerequisites

- Java 17+
- Node.js 20+
- Docker and Docker Compose
- PostgreSQL available locally or via Docker

## Local Development

1. Start the database and application services with `docker compose up --build`.
2. Run the backend locally from `backend/` with Spring Boot using the `docker` or default profile.
3. Run the frontend locally from `frontend/` with Vite using `npm run dev`.
4. Open the storefront at the Vite URL and verify catalog browsing, search, filters, detail views, cart, wishlist, and admin CRUD behavior.

## Backend Checks

- Confirm API routes are prefixed with `/pena`.
- Confirm pet listing CRUD works against PostgreSQL.
- Confirm unavailable pets remain flagged so the storefront can suppress purchase-style actions.

## Frontend Checks

- Confirm the gallery layout adapts to mobile and desktop widths.
- Confirm search, filters, and saved-item interactions update the visible state correctly.
- Confirm MUI controls and Tailwind layout work together without visual regressions.

## Deployment Notes

- Use Render free-tier web and database services.
- Provide all runtime configuration through environment variables.
- Keep the application stateless so instances can scale or restart cleanly.