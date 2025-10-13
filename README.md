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
## 🗂️ Project Structure
src/
- ├── config/ # Security & app configuration
- ├── controller/ # REST endpoints (CRM, users, salary)
- ├── dto/ # Data transfer objects
- ├── exceptions/ # Custom exception handlers
- ├── mapper/ # DTO mappers
- ├── model/ # JPA entities (User, OverTime, MissingDay, Department)
- ├── repository/ # Spring Data JPA repositories
- ├── security/ # JWT filter, authentication logic
- ├── service/ # Business logic (salary calc, data processing)
- ├── validator/ # Custom validation logic
- └── ProjectKServerApplication.java
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

SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/overtime_db
SPRING_DATASOURCE_USERNAME=root
SPRING_DATASOURCE_PASSWORD=your_password
JWT_SECRET=super_secret_key

