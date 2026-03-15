# Pistasien

A secure backend application built with **Spring Boot** that provides REST APIs with authentication, authorization, and rate limiting.

## Features

- JWT Authentication
- Role-based authorization
- REST API architecture
- Rate limiting using Bucket4j
- Backend integration with database
- Dashboard built with HTML, CSS, and JavaScript

## Tech Stack

Backend
- Java
- Spring Boot
- Spring Security
- Hibernate / JPA

Database
- MySQL

Frontend
- HTML
- CSS
- JavaScript

Security
- JWT Authentication
- Role-based access control

## Project Structure
src/main/java
├── controller
├── service
├── repository
├── model
└── config


## How to Run the Project

1. Clone the repository


git clone https://github.com/Pranav-p-p/Pistasien.git


2. Configure the database in `application.properties`


```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```


3. Run the application


mvn spring-boot:run


The server will start on


http://localhost:8080


## Author

Pranav
