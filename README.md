# Employee Performance Management System - Setup Guide

## Overview
This is a Spring Boot application for managing employee performance, built with Java 17, PostgreSQL, and Maven. The system manages departments, employees, projects, and performance reviews with comprehensive REST APIs.

## Prerequisites
- **Java 17** or higher
- **PostgreSQL 12+** database server
- **Maven 3.9+** (or use included wrapper)
- **Git** for version control

## Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/mohsin-project/Employee-Performance-Management-System.git
cd Employee-Performance-Management-System
```

### 2. Database Setup
1. Install and start PostgreSQL
2. Create a database for the application:
   ```sql
   CREATE DATABASE epms_db;
   CREATE USER epms_user WITH PASSWORD 'your_password';
   GRANT ALL PRIVILEGES ON DATABASE epms_db TO epms_user;
   ```

### 3. Environment Configuration
1. Copy the environment template:
   ```bash
   cp .env_example .env
   ```

2. Edit `.env` file with your database credentials:
   ```properties
   DB_USER=epms_user
   DB_PASS=your_password
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=epms_db
   ```

### 4. Run the Application

**Option A: Using Maven Wrapper (Recommended)**
```bash
# On Unix/Linux/macOS
./mvnw spring-boot:run

# On Windows
mvnw.cmd spring-boot:run
```

**Option B: Using System Maven**
```bash
mvn spring-boot:run
```

### 5. Verify Installation
- Application will start on: `http://localhost:8080`
- Health check: `GET http://localhost:8080/public/health-check`
- API Documentation: `http://localhost:8080/swagger-ui.html`

## Application Features

### Core Entities
- **Departments**: Organizational units with budgets
- **Employees**: Staff members with hierarchical relationships
- **Projects**: Work assignments with employee assignments
- **Performance Reviews**: Employee evaluation records

### API Endpoints

#### Public Endpoints
- `GET /public/health-check` - Application health status

#### Department Management
- `GET /departments/all` - List all departments
- `GET /departments/{id}` - Get department by ID
- `POST /departments/add` - Create new department
- `PUT /departments/update/{id}` - Update department
- `DELETE /departments/delete/{id}` - Delete department

#### Employee Management
- `GET /employees/all` - List all employees
- `GET /employees/{id}` - Get employee by ID
- `POST /employees/add` - Create new employee
- `PUT /employees/update/{id}` - Update employee
- `PUT /employees/add-projects/{id}` - Assign projects to employee
- `PUT /employees/remove-projects/{id}` - Remove projects from employee
- `DELETE /employees/delete/{id}` - Delete employee

#### Project Management
- `GET /projects/all` - List all projects
- `GET /projects/{id}` - Get project by ID
- `POST /projects/add` - Create new project
- `PUT /projects/update/{id}` - Update project
- `PUT /projects/add-employees/{id}` - Assign employees to project
- `PUT /projects/remove-employees/{id}` - Remove employees from project
- `DELETE /projects/delete/{id}` - Delete project

#### Performance Review Management
- `GET /performance-reviews/all` - List all performance reviews
- `GET /performance-reviews/{id}` - Get review by ID
- `POST /performance-reviews/add` - Create new review
- `PUT /performance-reviews/update/{id}` - Update review
- `DELETE /performance-reviews/delete/{id}` - Delete review

## Sample Data
The application includes sample data that's automatically loaded:
- 3 Departments with budgets
- 5 Employees with manager relationships
- 3 Projects with employee assignments
- 7 Performance reviews

## Configuration Details

### Database Configuration
The application uses PostgreSQL with the following features:
- JPA/Hibernate for ORM
- Automatic schema creation (`create-drop` mode)
- Connection pooling with HikariCP
- SQL logging enabled for development

### Key Dependencies
- **Spring Boot 3.5.5** - Main framework
- **Spring Data JPA** - Database operations
- **PostgreSQL Driver** - Database connectivity
- **MapStruct** - Entity-DTO mapping
- **Lombok** - Code generation
- **SpringDoc OpenAPI** - API documentation
- **Java Dotenv** - Environment variable management

## Development Tips

### Running Tests
```bash
./mvnw test
```

### Building the Application
```bash
./mvnw clean package
```

### Running with Different Profiles
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

### IDE Setup
- Import as Maven project
- Enable annotation processing for Lombok and MapStruct
- Set Java version to 17

## Troubleshooting

### Common Issues

1. **Database Connection Failed**
    - Verify PostgreSQL is running
    - Check `.env` file credentials
    - Ensure database exists

2. **Port Already in Use**
    - Change port in `application.properties`: `server.port=8081`
    - Or kill process using port 8080

3. **Maven Build Issues**
    - Ensure Java 17 is installed: `java -version`
    - Clear Maven cache: `./mvnw clean`

4. **MapStruct Compilation Errors**
    - Run: `./mvnw clean compile`
    - Check annotation processor configuration

### Logs Location
Application logs are output to console by default. For file logging, add to `application.properties`:
```properties
logging.file.name=logs/epms.log
logging.level.com.example.epms=DEBUG
```

## API Usage Examples

### Create Department
```bash
curl -X POST http://localhost:8080/departments/add \
  -H "Content-Type: application/json" \
  -d '{
    "name": "IT Department",
    "budget": 50000.0
  }'
```

### Create Employee
```bash
curl -X POST http://localhost:8080/employees/add \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john.doe@company.com",
    "departmentId": 1,
    "dateOfJoining": "2024-01-15",
    "salary": 75000.00,
    "managerId": null
  }'
```

### Add Performance Review
```bash
curl -X POST http://localhost:8080/performance-reviews/add \
  -H "Content-Type: application/json" \
  -d '{
    "employeeId": 1,
    "reviewDate": "2024-12-01",
    "score": 95.0,
    "reviewComments": "Excellent performance this quarter"
  }'
```

## Production Deployment

### Environment Variables
For production, set these environment variables:
```bash
export DB_HOST=your-prod-db-host
export DB_PORT=5432
export DB_NAME=epms_production
export DB_USER=epms_prod_user
export DB_PASS=secure_password
export SPRING_PROFILES_ACTIVE=prod
```

### Application Properties for Production
Create `application-prod.properties`:
```properties
spring.jpa.hibernate.ddl-auto=validate
spring.sql.init.mode=never
spring.jpa.show-sql=false
logging.level.com.example.epms=INFO
```

## Support
For issues or questions:
1. Check the troubleshooting section above
2. Review application logs
3. Consult the API documentation at `/swagger-ui.html`
4. Create an issue in the project repository

## License
This project is licensed under the Apache License 2.0.