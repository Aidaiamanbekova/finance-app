# Personal Finance Management System

## Faculty of Computer Science and Information Technology  
## Department of Software Engineering

**Student:** Aidai Amanbekova  
**Supervisor:** Mr. Erustan Erkebulanov

**Specialization:** Back‑end Development

**University:** International Ala-Too University


---

## Live Demo

[[https://finance-app-6fw3.onrender.com](https://finance-app-6fw3.onrender.com/register)]

> Create your own account via `/register` or use the credentials above.

---

## Problem

Individuals and small businesses struggle to track income and expenses without relying on expensive subscription‑based apps (e.g., Mint, YNAB) that often share user data with third parties. Free alternatives lack structured APIs, self‑hosting options, or proper data ownership guarantees.

---

## Solution

A **secure, self‑hostable backend system** that provides:

- Full CRUD operations for categories and transactions
- JWT‑based stateless authentication (password hashed with BCrypt)
- RESTful API ready to be consumed by any frontend (web, mobile, desktop)
- Docker containerization for easy deployment on any cloud platform
- Simple web interface (Thymeleaf + Bootstrap) for demonstration

---

## Goal

To deliver a production‑ready, open‑source personal finance backend that gives users complete control over their financial data without recurring fees or privacy concerns.

---

## Objectives

- Design and implement a layered backend using Spring Boot 3 and PostgreSQL.
- Integrate Spring Security with JWT for stateless authentication.
- Enforce row‑level security – users can only access their own categories and transactions.
- Provide a clean REST API with proper error handling and HTTP status codes.
- Containerize the application with Docker and deploy on a free cloud platform (Render).
- Validate all endpoints using Postman and unit tests (JUnit, MockMvc).

---

## Technology Stack

| Layer               | Technology                                      |
|---------------------|-------------------------------------------------|
| Backend Framework   | Spring Boot 3.2.5 (Java 17)                    |
| Security            | Spring Security + JJWT (0.11.5)                |
| Object‑Relational Mapping | Spring Data JPA (Hibernate)                |
| Database            | PostgreSQL (also works with any JDBC DB)       |
| Templating (Web UI) | Thymeleaf + Bootstrap 5                         |
| Build Tool          | Maven                                           |
| Testing             | JUnit 5, Mockito, MockMvc, Postman             |
| Containerization    | Docker                                          |
| Deployment          | Render.com (free tier)                          |

---

## Installation and Local Setup

### Prerequisites
- Java 17 (JDK)
- Maven 3.8+
- PostgreSQL (local instance or cloud)
- Git

### Steps

```bash
# Clone the repository
git clone https://github.com/Aidaiamanbekova/finance-app.git
cd finance-app

# Create a PostgreSQL database (example)
createdb finance_db

# Configure application.properties
# Copy src/main/resources/application.properties.example to application.properties
# Fill in your database credentials and JWT secret

# Run the application
./mvnw spring-boot:run
```
---

## Features

### Authentication & Security
- Registration with username, email, password
- Login returns JWT token (24h expiration)
- Passwords hashed with BCrypt (10 rounds)
- Protected API endpoints require `Authorization: Bearer <token>`

### Categories
- Create, view, update, delete categories (e.g., Food, Rent, Salary)
- Each category is owned by the user who created it

### Transactions
- Add income or expense records (amount, description, date, type, category)
- List all transactions of the authenticated user
- Delete a transaction (only if owned)

### Data Ownership
- Every service method checks that the resource belongs to the current user before modification or deletion

### Web Interface (Thymeleaf + Bootstrap)
- Registration page (`/register`)
- Login page (`/login`)
- Dashboard with two sections (categories and transactions)
- Auto‑refresh after any add/delete – no page reload needed

---

## Screenshots

| Page | Screenshot |
|------|-------------|
| Registration | <img width="1108" height="536" alt="image" src="https://github.com/user-attachments/assets/a865926c-f5c4-4ce1-af79-412e0a081ee1" /> |
| Login | <img width="1095" height="542" alt="image" src="https://github.com/user-attachments/assets/5c3e1965-685d-40ac-8251-371a4f68ed67" /> |
| Dashboard – Categories | <img width="1187" height="605" alt="image" src="https://github.com/user-attachments/assets/8c14076d-f9f4-4815-b316-7c319305c872" /> |
| Dashboard – Transactions | <img width="1125" height="467" alt="image" src="https://github.com/user-attachments/assets/96847a52-9f8a-4cc0-8223-dac41304d335" /> |
| Postman – Login | <img width="1101" height="320" alt="image" src="https://github.com/user-attachments/assets/375088da-e9c4-471e-a0ac-69d46bebc63f" /> |
| Postman – Transactions | <img width="718" height="569" alt="image" src="https://github.com/user-attachments/assets/ff37c479-6ccf-4ea6-ae5d-51bd0c090dd4" /> |

---
## Architecture Diagrams

System Architecture (Layered)

<img width="2028" height="5445" alt="deepseek_mermaid_20260501_58dc63" src="https://github.com/user-attachments/assets/d9c17864-f939-4f4a-b72a-98334b08c14d" />

Database Entity‑Relationship Diagram (ERD)

<img width="1112" height="3064" alt="deepseek_mermaid_20260501_736ba1" src="https://github.com/user-attachments/assets/b02d9cc7-0c85-459f-9213-9a03e86effa0" />

JWT Authentication Flow

<img width="5663" height="2745" alt="deepseek_mermaid_20260501_8eee58" src="https://github.com/user-attachments/assets/883a68f6-5371-4126-bb94-e46694d235ed" />



## Project Structure

```
finance-app/
├── src/main/java/com/finance/
│   ├── config/         # SecurityConfig, JwtAuthenticationFilter
│   ├── controller/     # AuthController, CategoryController, TransactionController, WebController
│   ├── service/        # AuthService, CategoryService, TransactionService, CustomUserDetailsService
│   ├── repository/     # UserRepository, CategoryRepository, TransactionRepository
│   ├── model/          # User, Category, Transaction, Role
│   ├── dto/            # AuthRequest, AuthResponse, CategoryDTO, TransactionDTO
│   ├── exception/      # GlobalExceptionHandler
│   └── util/           # JwtUtil
├── src/main/resources/
│   ├── templates/      # login.html, register.html, dashboard.html
│   └── application.properties
├── Dockerfile
├── .dockerignore
├── pom.xml
└── README.md
```

---

## API Endpoints

All endpoints return JSON. Protected endpoints require a valid JWT token in the `Authorization: Bearer <token>` header.

### Authentication (Public)

| Method | Endpoint | Body | Response |
|--------|----------|------|----------|
| POST | `/api/auth/register` | `{"username":"a","email":"a@b.com","password":"p"}` | `"User registered"` (200) |
| POST | `/api/auth/login` | `{"username":"a","password":"p"}` | `{"token":"..."}` (200) |

### Categories (Protected)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/categories` | Create new category (body: `{"name":"..."}`) |
| GET | `/api/categories` | List all user's categories |
| PUT | `/api/categories/{id}` | Update category (body: `{"name":"..."}`) |
| DELETE | `/api/categories/{id}` | Delete category (ownership check) |

### Transactions (Protected)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/transactions` | Create transaction. Body: `{"amount":45.99,"description":"...","type":"EXPENSE","categoryId":3,"date":"2026-05-01T18:30:00"}` |
| GET | `/api/transactions` | List all user's transactions |
| DELETE | `/api/transactions/{id}` | Delete transaction (ownership check) |

**Error codes:**
- `400` – invalid input / business rule violation
- `401` – missing or expired JWT
- `403` – authenticated but not resource owner
- `404` – resource not found

---

## Deployment Notes

The application is containerized with Docker and deployed on **Render.com** (free tier).

- Free web service spins down after 15 minutes of inactivity.
- First request after idle takes 15–20 seconds (cold start).
- Free PostgreSQL database is deleted after 30 days – for long‑term use upgrade to a basic plan or use a different provider (Neon, Supabase).
- All environment variables (`SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`, `JWT_SECRET`) must be set in the Render dashboard.

**Live URL:** [https://finance-app-6fw3.onrender.com](https://finance-app-6fw3.onrender.com/register)

---

## Acknowledgements

Special thanks to **Mr. Erustan Erkebulanov** for continuous guidance, and to the Department of Computer Science at **International Ala‑Too University** for providing resources and support.

The project was built using open‑source tools: Spring Boot, PostgreSQL, Docker, and Render.

---

## Contact

- **GitHub:** [https://github.com/Aidaiamanbekova/finance-app](https://github.com/Aidaiamanbekova/finance-app)
- **Email:** [Aidai.amanbekova@alatoo.edu.kg](mailto:Aidai.amanbekova@alatoo.edu.kg)

---

© 2026 Aidai Amanbekova – Thesis Project
```
