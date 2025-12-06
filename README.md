# ✈️ Flight Booking System

## 📝 Overview
A modern, scalable Flight Booking System built with microservices architecture. This system allows users to search for flights, book tickets, manage bookings, and handle user authentication and authorization.

### Key Features
- User registration and authentication
- Flight search and booking
- Booking management
- Payment processing
- Email notifications
- Admin dashboard

## 🏗️ Architecture

### 🔹 Tech Stack
- **Language**: Java 17+
- **Framework**: Spring Boot 3.x
- **API Gateway**: Spring Cloud Gateway
- **Service Discovery**: Netflix Eureka
- **Configuration**: Spring Cloud Config
- **Database**: PostgreSQL / MongoDB
- **Message Broker**: Apache Kafka
- **Containerization**: Docker
- **Monitoring**: Prometheus + Grafana
- **Tracing**: Zipkin
- **Logging**: ELK Stack

### 🔹 Microservices
| Service | Description | Port |
|---------|-------------|------|
| **API Gateway** | Single entry point for all client requests | 8080 |
| **User Service** | Handles user registration, authentication, and profile management | 8081 |
| **Flight Service** | Manages flight information, search, and availability | 8082 |
| **Booking Service** | Handles flight bookings and reservations | 8083 |
| **Payment Service** | Processes payments and handles transactions | 8084 |
| **Notification Service** | Sends email/SMS notifications | 8085 |

## 🚀 Getting Started

### 1️⃣ Prerequisites
- Java 17+
- Maven 3.8+
- Docker & Docker Compose
- PostgreSQL 14+
- Apache Kafka
- Node.js 16+ (for frontend)

### 🛠️ Installation & Setup

1. **Clone the Repository**
   ```bash
   git clone [https://github.com/your-username/flight-booking-system.git](https://github.com/your-username/flight-booking-system.git)
   cd flight-booking-system
