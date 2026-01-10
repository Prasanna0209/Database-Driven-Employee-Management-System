-- Sample Data Insertion
-- Usage: Execute this script to populate the employees table

USE employee_db;

-- Insert a few manual records
INSERT INTO employees (name, email, department, designation, salary, date_of_joining) VALUES 
('Alice Johnson', 'alice@example.com', 'IT', 'Software Engineer', 75000.00, '2023-01-15'),
('Bob Smith', 'bob@example.com', 'HR', 'Recruiter', 55000.00, '2022-11-20'),
('Charlie Brown', 'charlie@example.com', 'Finance', 'Accountant', 65000.00, '2021-06-10');

-- Procedure to generate bulk data (1000+ records)
DELIMITER $$
DROP PROCEDURE IF EXISTS generate_employees$$
CREATE PROCEDURE generate_employees()
BEGIN
    DECLARE i INT DEFAULT 0;
    DECLARE dept VARCHAR(50);
    DECLARE desig VARCHAR(50);
    
    WHILE i < 1000 DO
        -- Random Department
        IF (i % 3 = 0) THEN SET dept = 'IT';
        ELSEIF (i % 3 = 1) THEN SET dept = 'HR';
        ELSE SET dept = 'Finance';
        END IF;
        
        -- Random Designation
        IF (i % 2 = 0) THEN SET desig = 'Junior';
        ELSE SET desig = 'Senior';
        END IF;

        INSERT INTO employees (name, email, department, designation, salary, date_of_joining)
        VALUES (
            CONCAT('Employee_', i), 
            CONCAT('emp', i, '@company.com'), 
            dept, 
            CONCAT(desig, ' Staff'), 
            FLOOR(50000 + (RAND() * 50000)), 
            DATE_ADD('2020-01-01', INTERVAL FLOOR(RAND() * 1000) DAY)
        );
        SET i = i + 1;
    END WHILE;
END$$
DELIMITER ;

CALL generate_employees();
