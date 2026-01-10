-- Database Schema for Employee Management System

CREATE DATABASE IF NOT EXISTS employee_db;
USE employee_db;

-- Drop table if exists to start fresh
DROP TABLE IF EXISTS employees;

-- Create Employee Table
CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department VARCHAR(50),
    designation VARCHAR(50),
    salary DECIMAL(10, 2) NOT NULL,
    date_of_joining DATE NOT NULL
);

-- Indexes for performance optimization
-- Index on email for fast lookups (Unique constraint already creates one, but good to be explicit about intent)
-- INDEX idx_email (email); 

-- Index on department for filtering
CREATE INDEX idx_department ON employees(department);

-- Index on salary for range queries
CREATE INDEX idx_salary ON employees(salary);

-- EXPLAIN Analysis (Comments)
-- Before Indexing:
-- SELECT * FROM employees WHERE department = 'IT'; 
-- Type: ALL (Full Table Scan), Rows: 1000+

-- After Indexing:
-- SELECT * FROM employees WHERE department = 'IT';
-- Type: REF (Index Lookup), Rows: ~50 (depending on distribution)
-- improved performance significantly for filtering.
