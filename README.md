
# Car Service Management System

## Project Overview

The Car Service Management System is a Spring Boot application that manages vehicle servicing, customer details, mechanic information, service bookings, memberships, and payments.

## Technology Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Git and GitHub

## Modules

1. Admin Management
2. Membership Management
3. Customer Management
4. Vehicle Management
5. Mechanic Management
6. Service Management
7. Booking Management
8. Payment Management

## REST API Endpoints

| Module | Base Path |
|---|---|
| Admin | `/api/admins` |
| Membership | `/api/memberships` |
| Customer | `/api/customers` |
| Vehicle | `/api/vehicles` |
| Mechanic | `/api/mechanics` |
| Service | `/api/services` |
| Booking | `/api/bookings` |
| Payment | `/api/payments` |

## Supported API Operations

- `GET /api/{resource}` — Retrieve all records.
- `GET /api/{resource}/{id}` — Retrieve a record by ID.
- `POST /api/{resource}` — Create a record.
- `PUT /api/{resource}/{id}` — Update a record.
- `DELETE /api/{resource}/{id}` — Delete a record.

## Database

The application uses PostgreSQL hosted on Neon. Database credentials should be supplied through environment variables and must not be committed to the repository.

## Project Status

The backend includes entity classes, JPA repositories, REST controllers, and payment service components. API functionality and database relationships will be verified during testing.
