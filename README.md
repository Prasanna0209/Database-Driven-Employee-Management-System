# Database-Driven Employee Management System

## Overview
This is a robust, Java-based backend system for managing employee records. It utilizes raw JDBC for database interaction, following the MVC (Model-View-Controller) architecture to ensure separation of concerns.

## Prerequisites
- **Java JDK 8+**
- **MySQL Database**
- **MySQL JDBC Driver** (Connector/J)

## Folder Structure
```
d:/Database-Driven Employee Management System/
├── database/
│   ├── schema.sql         # Table creation and indexes
│   └── data.sql           # Sample data generation
├── src/
│   └── com/employee/
│       ├── model/         # POJO classes
│       ├── dao/           # Data Access Object Layer
│       ├── service/       # Business Logic Layer
│       ├── controller/    # (Not used in console app, merged with Main)
│       ├── util/          # Database Connection Utility
│       └── main/          # Entry point and UI
├── compile.bat            # Helper script for compilation
└── README.md
```

## Setup & Run Instructions

1. **Database Setup**:
   - Log in to MySQL and execute `database/schema.sql` to create the table and indexes.
   - Execute `database/data.sql` to populate the table with dummy data.

2. **Project Configuration**:
   - Open `src/com/employee/util/DBConnection.java`.
   - Update `URL`, `USER`, and `PASSWORD` to match your local MySQL setup.

3. **External Dependency**:
   - Download `mysql-connector-j-*.jar`.
   - Create a `lib` folder in the project root and place the jar there (or update the classpath in `compile.bat`).

4. **Compile and Run**:
   - Open a terminal and run `compile.bat`.
   - Or manually:
     ```bash
     javac -d bin src/com/employee/**/*.java
     java -cp "bin;lib/mysql-connector-j-8.0.33.jar" com.employee.main.Main
     ```
   *(Note: On Linux/Mac use `:` instead of `;` in classpath)*

## Key Features
- **MVC Architecture**: Clean separation of Data (DAO), Logic (Service), and UI (Main).
- **Transaction Management**: Salary updates simulate transactions with rollback on failure.
- **Performance**: Indexes on `department` and `salary` optimize search queries.
- **Safety**: Uses `PreparedStatement` to prevent SQL Injection.

## Interview-Ready Explanation
"I built this system using **MVC architecture** to decouple the user interface from the database logic. The **DAO pattern** handles all raw JDBC operations, ensuring that the business logic in the Service layer doesn't need to know about SQL implementation details. 

For **performance**, I added indexes on frequently searched columns like 'department', transforming full-table scans into efficient index lookups. I handled **transactions** manually by disabling auto-commit during critical updates, ensuring data integrity—if a step fails, the entire operation rolls back. Finally, I used **PreparedStatements** which pre-compile SQL queries, improving performance and protecting against SQL injection."
