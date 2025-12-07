# 🚖 RideShare Backend  
A robust Ride Sharing REST API backend built with Spring Boot, MongoDB, and JWT Authentication.  


## 🚀 Features
- Secure JWT authentication  
- User ride requests  
- Driver dashboards for pending rides  
- Accept and complete ride flow  
- Role based access for `ROLE_USER` and `ROLE_DRIVER`  
- Input validation with Jakarta Validation  
- Centralized global exception handling  

---

## 🛠️ Tech Stack
- Java 17  
- Spring Boot 3.2.1  
- Spring Security + JJWT  
- Spring Data MongoDB  
- Maven  
- Lombok  

---

## 📂 Project Structure
```
src/main/java/org/example/rideshare/
├── config/          # Security & App Config
├── controller/      # REST Controllers
├── dto/             # Data Transfer Objects
├── exception/       # Global Exception Handling
├── model/           # MongoDB Entities
├── repository/      # Repository Interfaces
├── service/         # Business Logic
└── util/            # Utility Classes (JWT)
```

---

## 📋 Prerequisites
- Java 17 or newer  
- Maven installed  
- MongoDB running locally on port `27017`

---

## ⚙️ Setup & Run

### 1. Clone the repository
```bash
git clone https://github.com/akhulisumit/rideShare.git
cd rideShare
```

### 2. Start MongoDB
Ensure your MongoDB server is running.

### 3. Run the application
```bash
./mvnw spring-boot:run
```

Application will start at:
```
http://localhost:8081
```

---

## 🔌 API Endpoints

| Role | Method | Endpoint | Description |
|------|--------|-----------|-------------|
| Public | POST | `/api/auth/register` | Register user or driver |
| Public | POST | `/api/auth/login` | Login and receive JWT |
| User | POST | `/api/v1/rides` | Request a new ride |
| User | GET | `/api/v1/user/rides` | View ride history |
| Driver | GET | `/api/v1/driver/rides/requests` | View pending ride requests |
| Driver | POST | `/api/v1/driver/rides/{id}/accept` | Accept a ride |
| Any | POST | `/api/v1/rides/{id}/complete` | Mark ride as completed |

---

## 🧪 Testing with CURL

### Register User
```bash
curl -X POST http://localhost:8081/api/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"john","password":"1234","role":"ROLE_USER"}'
```

### Register Driver
```bash
curl -X POST http://localhost:8081/api/auth/register \
-H "Content-Type: application/json" \
-d '{"username":"driver1","password":"abcd","role":"ROLE_DRIVER"}'
```

### Login
```bash
curl -X POST http://localhost:8081/api/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"john","password":"1234"}'
```

### Create Ride (User)
```bash
curl -X POST http://localhost:8081/api/v1/rides \
-H "Authorization: Bearer <YOUR_TOKEN_HERE>" \
-H "Content-Type: application/json" \
-d '{"pickupLocation":"Koramangala","dropLocation":"Indiranagar"}'
```

---

## 👤 Author  
**Utkarsh Bahuguna**


