<!--
Sync Impact Report
- Version change: template placeholder -> 1.0.0
- Modified principles:
	- Principle 1 -> Pena-Named Public Surface
	- Principle 2 -> Spring Boot as the Backend System of Record
	- Principle 3 -> Contracted Fullstack Boundaries
	- Principle 4 -> Reproducible Dockerized Delivery
	- Principle 5 -> Testable Commerce Flows
- Added sections:
	- Technology and Deployment Constraints
	- Delivery Workflow and Quality Gates
- Removed sections: none
- Templates requiring updates:
	- ✅ .specify/templates/plan-template.md — already compatible; no edit required
	- ✅ .specify/templates/spec-template.md — already compatible; no edit required
	- ✅ .specify/templates/tasks-template.md — already compatible; no edit required
	- ✅ .specify/templates/commands/ — no command templates present in workspace
- Follow-up TODOs: none
-->

# Petstore Constitution

## Core Principles

### I. Pena-Named Public Surface
All externally visible HTTP routes MUST be prefixed with `/pena`. The backend package namespace MUST start with
`com.pena.petstore`, and any shared package naming MUST preserve the same surname anchor. This keeps the API and
codebase consistently branded, avoids path collisions, and makes ownership obvious across services and generated
clients.

### II. Spring Boot as the Backend System of Record
The backend MUST be implemented in Java with Spring Boot, and PostgreSQL MUST be the authoritative data store for
product, inventory, order, and customer data. The catalog MUST support pet categories such as dogs, cats, birds,
and fishes, with room for additional attributes as the domain grows. Domain changes MUST be expressed through
explicit service layers and database migrations rather than ad hoc schema edits. This ensures predictable behavior,
repeatable deploys, and clean separation between HTTP handling and business rules.

### III. Contracted Fullstack Boundaries
The React frontend MUST consume backend capabilities through stable API contracts only. Tailwind MAY be used for
layout and spacing, while MUI MUST be used where it improves complex interactive components and accessibility.
UI state, forms, and error handling MUST reflect server truth instead of duplicating business logic in the browser.
This prevents divergence between the storefront and the backend domain model.

### IV. Reproducible Dockerized Delivery
Backend, frontend, and database dependencies MUST be runnable through Docker and Compose-friendly local workflows.
Container builds MUST be deterministic, environment configuration MUST come from runtime variables, and no secrets
MAY be committed to the repository. This keeps local development close to deployment and reduces surprises when
moving to Render.

### V. Testable Commerce Flows
Changes to catalog browsing, product detail, cart, checkout, authentication, and order placement MUST include
automated coverage at the cheapest effective level, with integration tests required when behavior crosses service,
database, or UI boundaries. Bugs MUST be prevented through tests before release, not discovered by manual storefront
use. This is necessary because e-commerce failures directly affect customer trust and revenue.

## Technology and Deployment Constraints

The approved stack for Petstore is Java Spring Boot, PostgreSQL, Docker, React, Tailwind, and MUI. The app MUST be
deployable on Render using free-tier services, which means the architecture MUST remain stateless,
resource-conscious, and compatible with managed external services. Background workers, caches, and third-party
integrations MUST be justified against free-tier limits before being introduced. Public endpoints MUST expose health
checks and startup behavior suitable for Render service health verification.

## Delivery Workflow and Quality Gates

Implementation MUST start from a minimal, working vertical slice for a single commerce journey, then expand
incrementally. Every change MUST preserve the ability to run the backend and frontend locally, and migrations MUST
be applied in a controlled order. Code review MUST check that the `/pena` API namespace, `com.pena.petstore`
package structure, Docker configuration, and Render deployment assumptions remain intact. When a change affects
persistence, API behavior, or storefront flows, the author MUST document the impact and update tests or migrations
in the same change set.

## Governance

This constitution overrides informal practice, README guidance, and ad hoc implementation habits when they
conflict. Amendments require a documented reason, a semantic version bump, and a review of downstream templates and
workflow guidance. Versioning follows semantic rules: MAJOR for incompatible governance changes, MINOR for new or
materially expanded principles, and PATCH for clarifications or wording fixes. Compliance is checked in planning
and review, and any intentional exception MUST be recorded with its rationale and a follow-up plan.

**Version**: 1.0.0 | **Ratified**: 2026-05-05 | **Last Amended**: 2026-05-05
