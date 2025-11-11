# ⏱️ OverTime Tracker

A web application for tracking employee working hours, overtime, and absences — with automatic salary calculation based on hourly rates and custom multipliers.

---

## ⚙️ Tech Stack

- ☕ **Java 17+**
- 🚀 **Spring Boot 3**
  - Spring Web  
  - Spring Data JPA  
  - Spring Security (JWT)
- 🗄️ **MySQL / PostgreSQL**
- 🔄 **Liquibase** — database versioning
- 🧮 **Maven**
- 🧰 **Frontend:** HTML, CSS, JavaScript (Vanilla)
- 🐳 **Docker** (optional setup)

---

## 🧠 Project Overview

The system allows administrators to:
- Manage departments and employee base salaries  
- Track daily overtime and missed hours  
- Automatically calculate total pay including multipliers (×1, ×1.5, ×2)
- View salary summaries by month or department  
- Edit salaries directly in the UI  

Each record includes:
- 👤 Employee name  
- 📅 Date  
- ⏰ Worked / missed hours  
- ⚡ Multiplier (×1, ×1.5, ×2)  
- 💵 Calculated pay and deductions  

---

## 📁 Project Structure

```
src/
├── config/           # Security & app configuration
├── controller/       # REST endpoints (CRM, users, salary)
├── dto/              # Data transfer objects
├── exceptions/       # Custom exception handlers
├── mapper/           # DTO mappers
├── model/            # JPA entities (User, OverTime, MissingDay, Department)
├── repository/       # Spring Data JPA repositories
├── security/         # JWT filter, authentication logic
├── service/          # Business logic (salary calc, data processing)
├── validator/        # Custom validation logic
└── ProjectKServerApplication.java
```

---

## 💻 Frontend Preview

- Clean CRM-like dashboard  
- Department filter  
- Monthly overview  
- Interactive modals for:
  - Salary editing  
  - Overtime and absence details  
- Focus mode: highlight specific employee  
- Colored daily cells for quick visualization  

> 🟩 Green — overtime  
> 🟥 Red — absence  
> 🟦 Blue — editable salary field  

---

## 🧮 Salary Calculation Logic

The system automatically computes total salary per user:

Where:
- **overtimeX1** — pay for regular overtime  
- **overtimeX1_5** — weekend work bonus  
- **overtimeX2** — holiday or double-pay overtime  
- **totalDeductions** — deductions for missed hours  

---

## 🔌 Environment Variables

```
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/overtime_db
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your_password
JWT_SECRET=super_secret_key
```

---

## 🧭 API Endpoints

> All routes are automatically prefixed with `/api`

---

### 🔐 Authentication

| Method | Endpoint | Description |
|---------|-----------|-------------|
| **POST** | `/auth/register` | Register a new user |
| **POST** | `/auth/login` | Authenticate and receive JWT token |

---

### 👥 Departments

| Method | Endpoint | Description |
|---------|-----------|-------------|
| **GET** | `/departments` | Get all available departments |

---

### 👤 User Endpoints  
**Requires:** `ROLE_USER`

#### ⏱️ Overtime Management
| Method | Endpoint | Description |
|---------|-----------|-------------|
| **POST** | `/user/overtimes` | Create new overtime record |
| **GET** | `/user/overtimes?year={y}&month={m}` | Get all overtime entries for the current user |
| **PUT** | `/user/overtimes/{overtimeId}` | Update an existing overtime record |

#### 🟥 Missing Work Days
| Method | Endpoint | Description |
|---------|-----------|-------------|
| **POST** | `/user/missing-days` | Add a new missing workday record |
| **GET** | `/user/missing-days?year={y}&month={m}` | Get all missing workdays for current user |

#### 💵 Expenses
| Method | Endpoint | Description |
|---------|-----------|-------------|
| **POST** | `/user/expenses` | Add new expense entry |
| **GET** | `/user/expenses?year={y}&month={m}` | Get all expense records for current user |

---

### 🛠️ Admin Endpoints  
**Requires:** `ROLE_ADMIN`

#### 👥 Users
| Method | Endpoint | Description |
|---------|-----------|-------------|
| **GET** | `/admin/users/department/{departmentId}` | Get all users in a specific department |
| **GET** | `/admin/users/{userId}` | Get detailed info about a specific user |
| **PUT** | `/admin/users/{userId}/salary` | Update user’s salary details |

#### ⏱️ Overtime
| Method | Endpoint | Description |
|---------|-----------|-------------|
| **GET** | `/admin/overtimes?userId={id}&year={y}&month={m}` | Get all overtime entries for a specific user and period |

#### 🟥 Missing Work Days
| Method | Endpoint | Description |
|---------|-----------|-------------|
| **GET** | `/admin/missing-days?userId={id}&year={y}&month={m}` | Get all missing workdays for a specific user and period |

#### 💵 Expenses
| Method | Endpoint | Description |
|---------|-----------|-------------|
| **GET** | `/admin/expenses?userId={id}&year={y}&month={m}` | Get all expenses for a specific user and period |

---

### 📊 CRM Analytics  
**Requires:** `ROLE_ADMIN`

| Method | Endpoint | Description |
|---------|-----------|-------------|
| **GET** | `/admin/crm/departments/{departmentId}/users?year={y}&month={m}` | Get aggregated department data (overtime, missing days, salary) |

---

## 🔒 Security

- **JWT-based Authentication**
- Protected endpoints by role (`USER`, `ADMIN`)
- Tokens are included via:
  ```
  Authorization: Bearer <token>
  ```

---

## 📘 API Response Codes

| Code | Meaning |
|------|----------|
| **200 OK** | Successful operation |
| **201 Created** | Resource created successfully |
| **202 Accepted** | Update or async task accepted |
| **400 Bad Request** | Invalid input or parameters |
| **401 Unauthorized** | Missing or invalid JWT token |
| **403 Forbidden** | Access denied due to role restrictions |
| **404 Not Found** | Resource not found |

---

## 🧩 Example Request

```bash
POST /auth/login
Content-Type: application/json

{
  "username": "john.doe",
  "password": "secret123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIs..."
}
```

---

## 🌐 Swagger UI

After starting the application, you can explore and test all API endpoints via:
```
http://localhost:8080/swagger-ui.html
```

---

## 📄 License

This project is distributed under the MIT License.  
Feel free to use, modify, and distribute it for your own needs.

---
