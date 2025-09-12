# Star Wars Mission and Character Management API

This project is a robust RESTful API built with Java and Spring Boot to manage characters and missions within the Star Wars universe. It features a complete backend with a PostgreSQL database, Flyway for migrations, and a secure authentication system. It also includes a dynamic, multi-page frontend for easy data management.

## Features

- **Character Management**: Full CRUD (Create, Read, Update, Delete) operations for characters.
- **Mission Management**: Full CRUD operations for missions.
- **Dynamic Data Association**: Assign characters to missions and manage their roles.
- **Rich Data Model**: Utilizes Java Enums to represent complex data like factions, ranks, and mission statuses with user-friendly display names and descriptions.
- **Database Migrations**: Uses Flyway to manage the database schema evolution in a structured and version-controlled way.
- **Secure Authentication**: Foundation for a secure authentication system using Spring Security and JWT.
- **Dynamic Frontend**: A multi-page web interface built with HTML and vanilla JavaScript that consumes the backend API. It includes pages for listing, creating, editing, and viewing character profiles.
- **Robust Error Handling**: A global exception handler provides clear and consistent JSON error responses.

## Technologies Used

- **Backend**: Java 17, Spring Boot 3
- **Database**: PostgreSQL
- **Database Migration**: Flyway
- **Security**: Spring Security (with JWT foundation)
- **API**: RESTful architecture
- **Build Tool**: Maven
- **Frontend**: HTML, CSS, Vanilla JavaScript

## Setup and Running the Project

To get the project running locally, follow these steps:

### 1. Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- PostgreSQL installed and running
- A Git client

### 2. Clone the Repository

```bash
git clone <your-repository-url>
cd projectsw
```

### 3. Database Setup

You need to create the database and enable the `pgcrypto` extension for UUID generation.

```bash
# 1. Connect to PostgreSQL as a superuser
psql -U postgres

# 2. In the psql prompt, create the database
CREATE DATABASE projectsw_db;

# 3. Connect to the new database
\c projectsw_db

# 4. Enable the pgcrypto extension
CREATE EXTENSION "pgcrypto";

# 5. Exit psql
\q
```

### 4. Configure Environment Variables

The project uses a `.env` file for application database credentials and a `flyway.properties` file for the Maven plugin.

**a) Create `.env` file:**

Create a file named `.env` in the root of the project with the following content, replacing the placeholders with your PostgreSQL credentials:

```
DB_URL=jdbc:postgresql://localhost:5432/projectsw_db
DB_USERNAME=your_db_user
DB_PASSWORD=your_db_password
```

**b) Create `flyway.properties` file:**

Create a file named `flyway.properties` in the root of the project. This file is used by the Flyway Maven plugin. **This file is already in `.gitignore` and should not be committed.**

```properties
flyway.url=jdbc:postgresql://localhost:5432/projectsw_db
flyway.user=your_db_user
flyway.password=your_db_password
```

### 5. Build the Project

Use the Maven wrapper to build the project. This will also download all necessary dependencies.

```bash
./mvnw clean install
```

### 6. Run the Application

Once the build is successful, you can run the application:

```bash
java -jar target/projectsw-0.0.1-SNAPSHOT.jar
```

The application will start, and Flyway will automatically run all database migrations, creating the tables and seeding the initial data.

The API will be available at `http://localhost:8181`.

## Accessing the Frontend

- **Character List**: [http://localhost:8181/characters.html](http://localhost:8181/characters.html)
- **Create Character Form**: [http://localhost:8181/character-form.html](http://localhost:8181/character-form.html)
