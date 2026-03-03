# 🛒 Amazio Backend – Spring Boot REST API

Backend service for the Amazio Full Stack E-Commerce Application.

Built using **Spring Boot**, this REST API handles authentication, authorization, order processing, product management, and secure role-based access control using JWT.

---

## 🚀 Features

### 👤 Authentication & Authorization
- User signup & login
- Admin login
- JWT token generation & validation
- Role-based access control (ADMIN / CUSTOMER)
- Custom authentication filter
- Password encryption using BCrypt

---

### 🛍️ Order Management
- Create order
- Fetch orders by user
- Fetch detailed order information
- Payment tracking (COD supported)
- Order status management

---

### 🛒 Product Management
- Add product (Admin only)
- Update product (Admin only)
- Delete product (Admin only)
- Paginated product listing
- Search & category filtering

---

## 🧱 Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

---

## 🔐 Security Architecture

- JWT-based stateless authentication
- Custom `AuthFilter` extending `OncePerRequestFilter`
- Role extraction from token (ADMIN / CUSTOMER)
- `@PreAuthorize` for method-level authorization
- Password encryption using `PasswordEncoder`

Authentication flow:

1. User logs in
2. JWT token generated
3. Token sent in `Authorization: Bearer <token>` header
4. `AuthFilter` validates token
5. SecurityContext populated with user role

---

## 📁 Project Structure

```bash
src/
└── main/
    ├── java/
    │   └── com.ecommerce.amazio/
    │       ├── controller/      
    │       ├── convertors/      
    │       ├── enums/           
    │       ├── exceptions/      
    │       ├── model/           
    │       ├── repository/      
    │       ├── requestDto/      
    │       ├── security/
    │       │   ├── config/      
    │       │   ├── filter/      
    │       │   ├── jwt/         
    │       │   └── service/     
    │       ├── service/         
    │       └── AmazioApplication.java  
    └── resources/
        ├── application.properties  
        └── products.json           
```
---

## 📦 Core Modules

### Controllers
- UsersApi
- OrdersAPI
- ProductsAPI

### Services
- AuthenticationService
- OrderService
- UserService
- ProductService

### Models
- User
- Address
- Order
- Payment
- OrderItem
- Product

### Repositories
- UserRepo
- OrderRepo
- ProductRepo

---

## 🛠️ Run Locally

Clone the repository:

```bash
git clone https://github.com/sunil-reddy-2003/Amazio-backend.git
cd Amazio-backend

Configure application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/amazio
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update

Run the application:
mvn spring-boot:run



