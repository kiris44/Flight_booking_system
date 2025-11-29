# ✈️ Flight Booking System

A full-stack, microservices-based flight booking platform that connects airlines with customers, providing a seamless booking experience with real-time flight search, booking management, and airline administration.

## 🌟 Key Features

### User Management
- **Multi-role Authentication**
  - JWT-based authentication
  - Role-based access control (Admin, Airline, Customer)
  - Secure password hashing
  - Session management

### Flight Booking
- **Intuitive Search**
  - Search by source, destination, and date
  - Filter by price, airlines, and departure/arrival times
  - View available seats and pricing

- **Booking Management**
  - Secure payment integration
  - Booking confirmation and e-tickets
  - Booking history and status tracking
  - Cancellation and refund processing

### Airline Management
- **Airline Onboarding**
  - Registration with document verification
  - Admin approval workflow
  - Profile and fleet management

- **Flight Operations**
  - Aircraft registration and management
  - Flight scheduling and route planning
  - Dynamic seat mapping
  - Real-time flight status updates

### Admin Dashboard
- **User Management**
  - View and manage all users
  - Monitor user activities
  - Handle user reports and issues

- **Airline Oversight**
  - Approve/Reject airline applications
  - Monitor airline performance
  - Manage flight schedules and routes

## 🏗️ System Architecture

### Microservices
1. **Central API**
   - Core business logic
   - Request routing
   - Service orchestration

2. **Database Service**
   - Data persistence
   - Query optimization
   - Data integrity management

3. **Notification Service**
   - Email notifications
   - Booking confirmations
   - Flight status updates

### Technical Stack
- **Backend**: Java 17, Spring Boot 3.x
- **Security**: Spring Security, JWT
- **Database**: MySQL/PostgreSQL
- **API Documentation**: Swagger/OpenAPI 3.0
- **Build Tool**: Maven
- **Containerization**: Docker

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.8+
- MySQL 8.0+ or PostgreSQL 13+
- Node.js 16+ (for frontend)

### Installation
1. Clone the repository:
   ```bash
   git clone [https://github.com/yourusername/flight-booking-system.git](https://github.com/yourusername/flight-booking-system.git)
   cd flight-booking-system
