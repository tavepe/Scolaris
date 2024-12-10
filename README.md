# Scolaris Application

Scolaris is a Spring Boot application designed to manage school-related data such as classes, users, tests, and grades. The application provides RESTful APIs for creating, updating, retrieving, and deleting these entities. It's my first public repository and will be used as a base for the front-end projects I'll do, so expect changes in the future.
## Technologies
Java

Spring Boot

Maven

MySQL
## Getting Started
### Prerequisites
Java 11 or higher

Maven 3.6.0 or higher

MySQL 5.7 or higher
 
### Installation

#### Clone the repository:

```bash
git clone https://github.com/tavepe/scolaris.git
```

#### Navigate to the project directory:

```bash
cd scolaris
```

#### Set up the database:

1. Install MySQL and start the MySQL server.
2. Create a new database:

```sql
CREATE DATABASE Scolaris;
```

3. Update the database configuration in `src/main/resources/application.properties` with your MySQL username and password:

```ini
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/Scolaris
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

#### Build the project using Maven:

```bash
mvn clean install
```

#### Navigate to the project package:

```bash
cd target
```

#### Run the application:

```bash
java -jar .\Scolaris-1.0.0.jar
```

The application will start on [http://localhost:8080](http://localhost:8080)


## API Endpoints

### User Management

POST /createUser - Create a new user

GET /listUsers - List all users

GET /getUser/{id} - Retrieve user by ID

GET /listStudents - List all student users

GET /listTeachers - List all teacher users

PUT /updateUser/{id} - Update an existing user

DELETE /deleteUser/{id} - Delete a user

### Class Management

POST /createClass - Create a new class

GET /listClasses - List all classes

GET /getClass/{id} - Retrieve a class by ID

GET /listClassesByTeacher/{id} - List all classes by teacher ID

PUT /updateClass/{id} - Update an existing class

DELETE /deleteClass/{id} - Delete a class

### Test Management

POST /createTest - Create a new test

GET /getTest/{id} - Retrieve a test by ID

GET /listTestsByClass/{id} - List all tests by class ID

### Grade Management
POST /createGrade - Create a new grade

GET /getGrade/{studentId}/{testId} - Retrieve a grade by student and test ID

## License

This project is licensed under the MIT License.  <hr></hr>

## Other Languages

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/tavepe/Scolaris/blob/main/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/tavepe/Scolaris/blob/main/README.pt-br.md)
