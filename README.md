# Ecommerce Microservice Project
Welcome to the "Ecommerce Microservice" project, a REST API implementation using the microservices architecture for an ecommerce platform built with Spring Boot. 
This project leverages various technologies and databases, including Keycloak for authentication, MongoDB for the catalog service using reactive Spring WebFlux, PostgreSQL for the order service, and MySQL for the shipping service. 
The goal of this project is to learn how to build a fully-fledged microservices-based REST API while exploring different database technologies, event-driven communication using RabbitMQ, service discovery with Eureka, and containerization with Docker.

## Table of Contents
- [Introduction](#introduction)
- [Services](#services)
- [Technologies Used](#technologies-used)
- [Getting Started](#getting-started)
- [Dockerization](#dockerization)
- [Testing](#testing)
- [License](#license)
- [Author](#author)

## Introduction
The "Ecommerce Microservice" project is an example of a microservices-based architecture for an ecommerce platform. It is designed to demonstrate best practices in building scalable and maintainable microservices using Spring Boot.

## Services
This project comprises several microservices:

- [ ] **Authentication Service (Redis)**: Provides authentication and authorization for users.
- [x] **Catalog Service (MongoDB with Reactive Spring WebFlux)**: Manages product catalog data with a NoSQL database and leverages the reactive programming model.
- [x] **Order Service (PostgreSQL)**: Handles order processing and management using a SQL database.
- [ ] **User Service (MySQL)**: Manages user information.
- [ ] **Event-Driven Communication (RabbitMQ)**: Implements event-driven architecture to enable communication and data synchronization between microservices.
- [ ] **Service Discovery (Eureka)**: Offers service registration and discovery capabilities for seamless communication between microservices.

##  Technologies Used
The project utilizes the following technologies:

- **Spring Boot**: For building microservices.
- **Redis**: For authentication and authorization.
- **MongoDB**: For the catalog service with reactive Spring WebFlux.
- **PostgreSQL**: For the order service.
- **MySQL**: For the shipping service.
- **RabbitMQ**: For event-driven communication.
- **Eureka**: For service discovery.
- **Docker**: For containerization and managing dependencies.

## Getting Started
To get started with the project, follow these steps:

1. Clone the repository to your local machine:
    ```shell
    git clone https://github.com/andrecastrosousa/ecommerce-microservice
2. Navigate to the project directory:
    ```shell
    cd ecommerce-microservice
3. Start the services, ensuring that dependencies like MongoDB, PostgreSQL, MySQL, RabbitMQ, and Eureka are up and running (Keycloak, RabbitMQ, Eureka, MySQL, MongoDB, PostgresSQL).

    using the following command:
    ```shell
   docker-compose up -d

4. Configure Keycloak for authentication and authorization.

5. Explore and interact with the services as needed for your ecommerce application.

## Dockerization
This project leverages Docker to manage software dependencies. You can use the provided Docker Compose file to build and run containers for the required services, including databases, RabbitMQ, Eureka, Keycloak, MongoDB, and PostgreSQL. Refer to the project's Docker documentation for detailed instructions.

## Testing
The project includes unit and integration tests for each microservice to demonstrate best practices in testing microservices. You can run these tests to ensure the correctness and reliability of your microservices.

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Author
* [@andrecastrosousa](https://github.com/andrecastrosousa)

Feel free to reach out to the author with any questions or feedback related to the project.

Thank you for exploring the "Ecommerce Microservice" project, and I hope it helps you understand and implement microservices architecture for your own projects.