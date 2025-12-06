✈️ Flight Booking System – Microservices Architecture (Java, Spring Boot)

A complete backend system for a Flight Booking Application, following a microservices architecture built using Java 17 and Spring Boot.
The system consists of:

Central API – Core business logic, flight operations, airline flow, user authentication

DB API – Dedicated database microservice for persistent storage

Notification API – Handles email notifications for airline registration workflows

📌 Table of Contents

Overview

Architecture

Microservices

Features

API Endpoints

Tech Stack

Prerequisites

Configuration

Running the Services

Project Structure

Security

Contributing

License

📝 Overview

This backend system provides a full microservices-based solution for a flight booking platform.
It includes flight search, airline registration, user authentication, aircraft & flight scheduling, and email notifications—all decoupled into separate services.

🏗️ Architecture
                           +--------------------+
                           |    Notification    |
                           |        API         |
                           | (Email Service)    |
                           +---------+----------+
                                     ^
                                     |
+---------------+        +-----------+-----------+         +---------------------+
|               |        |                       |         |                     |
|   Frontend    +------->+     Central API       +-------->+       DB API        |
| (Any Client)  | REST   | (Business Logic +     |   DB    |  (PostgreSQL + JPA) |
|               |        |  Authentication)      |         |                     |
+---------------+        +-----------------------+         +---------------------+


Central API communicates with

DB API for database operations

Notification API for sending emails

🧩 Microservices
1️⃣ Central API

Handles:

User authentication (JWT)

Airline registration workflow

Aircraft & flight scheduling

Flight search

Seat & status management

Runs on: localhost:7071

2️⃣ DB API

Responsible for all database transactions, entity persistence, and JPA interactions.
Uses PostgreSQL as the main database.

Runs on: localhost:7070

3️⃣ Notification API

Handles all email notifications:

Airline registration alerts to app admins

Accept/reject notifications to airlines

Runs on: localhost:7072

⭐ Features
🔐 User & Authentication

JWT-based login

Role-based access

Secure password hashing

🛫 Airline Management

Airline registration workflow

Aircraft registration

Flight scheduling

🔍 Flight Operations

Flight search API

Flight status handling

Seat mapping

📬 Notifications

Airline registration request notifications

Acceptance/rejection updates

🌐 API Endpoints
Central API Endpoints
👤 User
Method	Endpoint	Description
POST	/api/v1/central/user/login	User login
GET	/api/v1/central/user/search	Search available flights
🛩 Airline Management
Method	Endpoint	Description
POST	/api/v1/central/airline/register	Register new airline
GET	/api/v1/central/airline/request/accept/{id}	Approve airline
GET	/api/v1/central/airline/request/reject/{id}	Reject airline
POST	/api/v1/central/airline/aircraft/register	Add aircraft
POST	/api/v1/central/airline/flight/schedule	Schedule flight
Notification API Endpoints
Admin Notifications

POST /api/v1/notify/appadmin/airline-registration

Airline Status Notifications

PUT /api/v1/notify/airline/admin/accept-request

PUT /api/v1/notify/airline/admin/reject-request

🗄️ Tech Stack
Backend

Java 17

Spring Boot

Spring Security (JWT)

Spring Web

Spring Data JPA

Spring Mail (Notification API)

Database

PostgreSQL (DB API)

MySQL (optional if used)

Build

Maven

🔧 Prerequisites

Install the following:

Java 17+

Maven 3.6+

PostgreSQL 13+

MySQL (if used for Central API)

⚙️ Configuration
Central API – application.properties
spring.application.name=central-api
server.port=7071

db.api.url=http://localhost:7070/api/v1/db
notification.api.url=http://localhost:7072/api/v1/notify

jwt.secret.password=your-secret-here
jwt.expiration.time=1000000

DB API – application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/fbs-db
spring.datasource.username=postgres
spring.datasource.password=your-db-password
spring.jpa.hibernate.ddl-auto=update
server.port=7070

Notification API – application.properties
spring.application.name=notification-api
server.port=7072

spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

▶️ Running the Services
1. Clone the project
git clone your-repo-url
cd flight-booking-system

2. Build all services
mvn clean install

3. Run services
Central API
cd central-api
mvn spring-boot:run

DB API
cd db-api
mvn spring-boot:run

Notification API
cd notification-api
mvn spring-boot:run

📁 Project Structure
flight-booking-system/
│
├── central-api/          # Main business logic service
├── db-api/               # Database microservice
├── notification-api/     # Email notification microservice
└── README.md             # Combined documentation

🔐 Security

JWT-based authentication

Role-based access (Admin / Airline / User)

Password hashing

Secure inter-service communication

🤝 Contributing
1. Fork the repository
2. Create a branch: git checkout -b feature/NewFeature
3. Commit changes: git commit -m "Add new feature"
4. Push: git push origin feature/NewFeature
5. Submit a pull request
