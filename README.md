# Blackjack API 🃏

Reactive Blackjack API built with **Spring Boot WebFlux**, **DDD**, and **Clean Architecture**.

## 🚀 Features

- Reactive API with Spring WebFlux
- Domain-Driven Design (DDD)
- Clean Architecture (domain / application / infrastructure)
- MongoDB for games
- MySQL (R2DBC) for players and rankings
- Domain Events + Spring Events
- Strategy Pattern for game actions (HIT / STAND)
- Global error handling
- Swagger / OpenAPI
- Docker & Docker Compose
- Unit tests with JUnit 5 & Mockito
- Object Mother pattern for tests

---

## 🧱 Architecture

```
domain
 ├── model
 ├── events
 └── exceptions

application
 ├── usecase
 ├── dto
 └── listener

infrastructure
 ├── persistence
 │    ├── mongodb
 │    └── mysql
 └── web
      ├── controller
      ├── mapper
      └── exception
```

---

## 📦 Technologies

- Java 21
- Spring Boot 3.3.x
- Spring WebFlux
- Spring Data MongoDB Reactive
- Spring Data R2DBC (MySQL)
- Spring Events
- Springdoc OpenAPI
- Docker & Docker Compose
- JUnit 5 / Mockito

---

## 🗄 Databases

### MySQL
- Stores players and rankings
- Initialized with `schema.sql` and `data.sql`

### MongoDB
- Stores Blackjack games
- No initial data (games are created via API)

---

## ▶️ Run with Docker

```bash
docker compose up --build
```

Services:
- API: http://localhost:8080
- Swagger: http://localhost:8080/swagger/index.html
- MySQL: localhost:3306
- MongoDB: localhost:27017

---

## 📘 API Documentation

Swagger UI:
```
http://localhost:8080/swagger/index.html
```

---

## 🧪 Tests

- Unit tests for domain logic
- Service tests with Mockito
- Controller tests with WebTestClient
- Object Mother pattern used for test data

Run tests:
```bash
mvn test
```

---

## 🧠 Game Flow

1. Create Player
2. Start Game
3. HIT or STAND
4. Game ends
5. Domain Events published
6. Player stats updated asynchronously

---

## 🏆 Ranking

Players are ranked by number of wins.

---

## ✍️ Author

Final project — Spring Advanced / DDD / WebFlux
