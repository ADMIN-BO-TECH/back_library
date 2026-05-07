# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

`botech-back-library` is a **shared Java library** (not an executable application) published to GitHub Packages and consumed as a Maven dependency by BO-TECH microservices. It provides the shared data access layer for a school transportation system: JPA entities, Spring Data repositories, DTOs, and utilities.

## Build Commands

All commands use the Maven Wrapper (`./mvnw`):

```bash
# Full build with tests
./mvnw clean verify

# Build without tests
./mvnw clean package -DskipTests

# Deploy to GitHub Packages
./mvnw clean deploy -DskipTests

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=ClassName

# Run a single test method
./mvnw test -Dtest=ClassName#methodName
```

There are no linting plugins configured (no Checkstyle, PMD, SpotBugs).

## Architecture

The library is organized into five packages under `co.com.botech`:

- **`entity/`** — 38 JPA entities (`@Entity`, `@Table`) using Lombok (`@Data`, `@Builder`). All map to a relational database schema shared across microservices.
- **`repository/`** — 36 Spring Data JPA interfaces (CRUD + custom queries). Spring Data is declared `provided`/`optional` so consumers must supply it.
- **`dto/`** — 250+ transfer objects organized by subdomain (`announcement/`, `attendance/`, `route/`, `student/`, `vehicle/`, `n8n/`, `tracking/`, etc.). These are the API contracts between microservices.
- **`util/`** — Stateful services and pure utilities:
  - `firebase/` — Firestore and Cloud Storage operations via Firebase Admin SDK
  - `excelutils/` — Apache POI `.xlsx`/`.xls` generation
  - `geocoding/` — Google Maps geocoding
  - `generalUtils/` — `DateUtils`, `TextUtils`, `CustomException` (wraps checked exceptions with business error codes from `CustomExceptionCodeConstants`)
- **`constants/`** — 19 enums/constant classes defining domain rules (`UserType`, `AttendanceType`, `PermitStatus`, `FirebaseCollectionsConstants`, etc.)

### Domain

The domain models a school bus system: Students, Parents, Employees (drivers), Admins, Routes, Vehicles, Stops, Attendance, Authorizations, RFID registers, Permits, Notifications, and Geofencing.

## Versioning & CI/CD

Version format is controlled by the `publish-library-version.yml` workflow using branch name and commit message keywords:

| Branch | Version format |
|--------|---------------|
| `main` | `PROD-X.Y.Z` |
| `dev` | `DEV-X.Y.Z` |
| `feature/TICKET-123` | `BOTECH-TICKET-123-X.Y.Z` |

Increment type is driven by commit message keywords: `[major]`, `[minor]`, or `[patch]` (default: patch).

Pushing to any branch auto-publishes a new version to GitHub Packages. The `Ci-test.yml` workflow runs `./mvnw clean verify` on every push/PR.

## Local Setup

Requires Java 17 and a `.mvn/settings.local.xml` file (git-ignored) with a GitHub PAT that has `write:packages`, `read:packages`, and `repo` scopes. A template is at `.mvn/settings.template.xml`. Maven is forced to use this file via `.mvn/maven.config`.

## Key Conventions

- Entities use field-level `@Column` mapping with snake_case column names.
- All DTOs use Lombok `@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder`.
- Spring beans (`FirebaseService`, `GeocodingService`, etc.) are designed to be injected by consuming microservices — this library itself has no Spring Boot application context.
- `CustomException` is the standard exception type; always use codes from `CustomExceptionCodeConstants`.
