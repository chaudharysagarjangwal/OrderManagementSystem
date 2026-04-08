# Order Management System

A backend Order Management System built using Java and Spring Boot that allows users to create and manage orders. The system uses Kafka for event-driven communication and sends order confirmation emails asynchronously.

## 🚀 Tech Stack

* Java
* Spring Boot
* Spring Security
* Apache Kafka
* Apache ZooKeeper
* Redis
* MySQL
* Swagger (OpenAPI Documentation)
* Maven

## 📌 Features

* JWT Authentication 
* Role-based Authorization
* Redis Caching
* Kafka Consumer for processing order events
* Email notification service for order confirmation
* RESTful APIs for order management
* API documentation using Swagger UI



## ⚙️ Setup and Installation

### 1️⃣ Clone the repository

```
git clone https://github.com/chaudharysagarjangwal/OrderManagementSystem.git
```

### 2️⃣ Configure MySQL database

Update `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/order_db
spring.datasource.username=root
spring.datasource.password=yourpassword
```

### 3️⃣ Start ZooKeeper

```
bin/windows/zookeeper-server-start.bat config/zookeeper.properties
```

### 4️⃣ Start Kafka Broker

```
bin/windows/kafka-server-start.bat config/server.properties
```

### 5️⃣ Run the Spring Boot application

```
mvn spring-boot:run
```

## 📖 API Documentation

Swagger UI is available at:

```
http://localhost:8080/swagger-ui/index.html
```

This allows you to test all APIs directly from the browser.

## 📬 Kafka Workflow

1. User places an order using API
2. Order event is published to Kafka topic
3. Kafka consumer listens to the topic
4. Email service sends order confirmation to the user

## 🧪 Example API

Create Order

```
POST /orders
```

Request Body

```
{
  "email": "user@example.com",
  "product": "Laptop",
  "quantity": 1
}
```

## 🔐 Authentication (Optional)

JWT authentication can be added to secure APIs.
Swagger UI supports Bearer token authentication for testing protected endpoints.

## 🎯 Future Improvements

* Add payment integration
* Add order status tracking
* Deploy using Docker and Kubernetes

## 👨‍💻 Author

Chaudhary Sagar jangwal

Java Backend Developer | Spring Boot | Kafka
| Redis | JWT Authorization