INSERT INTO department(id, name, budget) VALUES
(1, 'Department 1', 10000),
(2, 'Department 2', 20000),
(3, 'Department 3', 30000);

INSERT INTO employee(id, name, email, department_id, date_of_joining, salary, manager_id) VALUES
(1, 'Employee 1', 'manager1@test.com', 1, '2025-09-01', 1000, null),
(2, 'Employee 2', 'employee2@test.com', 1, '2025-09-01', 1000, 1),
(3, 'Employee 3', 'employee3@test.com', 2, '2025-09-01', 1000, 1),
(4, 'Employee 4', 'employee4@test.com', 3, '2025-09-01', 1000, 2),
(5, 'Employee 5', 'employee5@test.com', 3, '2025-09-01', 1000, 3);

INSERT INTO project(id, name, start_date, end_date, department_id) VALUES
(1, 'Project 1', '2025-09-10', '2025-09-15', 1),
(2, 'Project 2', '2025-09-10', '2025-09-15', 1),
(3, 'Project 3', '2025-09-10', '2025-09-15', 2);

INSERT INTO employee_project(employee_id, project_id, assigned_date, role) VALUES
(1, 1, '2025-09-12', 'DEVELOPER'),
(1, 2, '2025-09-12', 'TESTER'),
(2, 2, '2025-09-12', 'ADMIN'),
(2, 3, '2025-09-12', 'ADMIN'),
(3, 3, '2025-09-12', 'DEVELOPER'),
(4, 3, '2025-09-12', 'DEVELOPER');


INSERT INTO performance_review(employee_id, review_date, score, review_comments) VALUES
(1, '2025-09-10', 98, 'Good!'),
(1, '2025-09-11', 99, 'Very Good!'),
(1, '2025-09-12', 100, 'Excellent!'),
(2, '2025-09-10', 95, 'Good!'),
(2, '2025-09-11', 100, 'Excellent!'),
(3, '2025-09-10', 93, 'Good!'),
(5, '2025-09-10', 85, 'Good!');