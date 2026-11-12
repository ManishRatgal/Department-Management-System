# Department Management System

A RESTful Department Management System developed using Java and Spring Boot. This application provides APIs to create, retrieve, update, delete, search, and manage departments with pagination and sorting.

## Project Overview

The Department Management System is a backend application designed to manage department records efficiently through REST APIs.

## Technologies Used

- Java 17
- Spring Boot 3.5.7
- Spring Data JPA
- Hibernate ORM
- MySQL
- Maven
- RESTful APIs
- Swagger / OpenAPI
- Jakarta Bean Validation
- Eclipse IDE

## Features

- Create a new department
- Retrieve all departments
- Retrieve department by ID
- Update department details
- Delete department
- Search departments by keyword
- Pagination and sorting
- Request validation
- Global exception handling
- Duplicate resource handling
- Swagger API documentation

## Project Structure

```text
department-service/
├── src/main/java/
│   └── com/example/department_service/
│       ├── config/
│       ├── controller/
│       ├── dto/
│       ├── entity/
│       ├── exception/
│       ├── repository/
│       ├── service/
│       └── DepartmentServiceApplication.java
│
├── src/main/resources/
│   └── application.properties
│
├── src/test/java/
├── pom.xml
├── mvnw
└── README.md
```

## REST API Endpoints

Base URL: `http://localhost:8082`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/departments` | Create department |
| GET | `/api/departments` | Get all departments |
| GET | `/api/departments/{id}` | Get department by ID |
| PUT | `/api/departments/{id}` | Update department |
| DELETE | `/api/departments/{id}` | Delete department |
| GET | `/api/departments/search?keyword=value` | Search departments |
| GET | `/api/departments/page` | Pagination and sorting |

## Database Configuration

Database: MySQL

Database name: `department_db`

Configure your database credentials using environment variables:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/department_db
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD}
```

Set your database username and password before running the application.

## How to Run the Project

### 1. Clone the repository

```bash
git clone https://github.com/ManishRatgal/Department-Management-System.git
```

### 2. Navigate to the project

```bash
cd Department-Management-System
```

### 3. Configure MySQL

Create the database:

```sql
CREATE DATABASE department_db;
```

Configure your database credentials.

### 4. Run the application

```bash
./mvnw spring-boot:run
```

### 5. Open Swagger UI

```text
http://localhost:8082/swagger-ui/index.html
```

## API Testing

All REST API endpoints can be tested using Swagger UI or Postman.

## Developer

**Manish Ratgal**

GitHub: https://github.com/ManishRatgal

## License

This project is available for educational and learning purposes.