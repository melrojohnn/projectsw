# 🌌 StarWars Missions API

A RESTful API built with Spring Boot to manage characters and missions in the Star Wars universe. This project serves as a practical application of modern Java back-end development, featuring a versioned database, secure configuration, and a clean, scalable architecture.

## ✨ Features Implemented

-   **RESTful API:** Exposes endpoints for managing `Missions` and `Characters`.
-   **Database Integration:** Uses Spring Data JPA to connect to a PostgreSQL database.
-   **Database Versioning:** Employs **Flyway** to manage database schema changes and seed initial data, ensuring a consistent and reproducible database state.
-   **Secure Configuration:** Database credentials and other sensitive data are kept out of version control using a `.env` file, loaded on startup.
-   **Bidirectional Relationship Handling:** Correctly manages JSON serialization for related entities to prevent infinite loops.
-   **Clean Architecture:** Ready for future expansion with a clear separation of concerns (Controllers, Repositories, and soon, Services and DTOs).

## 🚀 Technologies Used

| Layer                | Technology                               |
| -------------------- | ---------------------------------------- |
| **Language**         | Java 17                                  |
| **Framework**        | Spring Boot                              |
| **Data Persistence** | Spring Data JPA / Hibernate              |
| **Database**         | PostgreSQL                               |
| **DB Versioning**    | Flyway                                   |
| **Dependencies**     | Lombok, dotenv-java                      |
| **Build Tool**       | Maven                                    |

## 🏁 Getting Started

### Prerequisites

-   Java 17 (or higher)
-   Apache Maven
-   A running PostgreSQL instance

### How to Run

1.  **Clone the repository:**
    ```sh
    git clone https://github.com/melrojohnn/projectsw.git
    cd projectsw
    ```

2.  **Create the environment file:**
    Create a file named `.env` in the root of the project and add your PostgreSQL database credentials:
    ```env
    DB_URL=jdbc:postgresql://localhost:5432/your_database_name
    DB_USERNAME=your_username
    DB_PASSWORD=your_password
    ```

3.  **Build the project:**
    Use the Maven wrapper to build the application. This will also download all necessary dependencies.
    ```sh
    ./mvnw clean install
    ```

4.  **Run the application:**
    Once the build is complete, you can start the application.
    ```sh
    java -jar target/projectsw-0.0.1-SNAPSHOT.jar
    ```
    The application will start on `http://localhost:8181`. Flyway will automatically run the database migrations on the first startup.

## API Endpoints

The following endpoints are currently available:

| Method | URL                | Description                               |
| ------ | ------------------ | ----------------------------------------- |
| `GET`  | `/missions`        | Retrieves a list of all missions.         |
| `GET`  | `/characters`      | Retrieves a list of all characters.       |

### Sample Response for `/missions`

```json
[
  {
    "id": 1,
    "title": "Steal Death Star Plans",
    "description": "Infiltrate the Empire base on Scarif to steal the plans for the Death Star.",
    "status": "ACTIVE",
    "members": [
      {
        "id": 3,
        "name": "Jyn Erso",
        "email": "jyn@rebellion.org",
        "age": 21,
        "homeland": "Vallt"
      }
    ]
  }
]
```

## 🎯 Future Roadmap

This project serves as a foundation. Future enhancements include:

-   **Service and DTO Layers:** Refactor the architecture to include a business logic layer (Services) and Data Transfer Objects (DTOs) for the API.
-   **CRUD Operations:** Implement `POST`, `PUT`, and `DELETE` endpoints for full resource management.
-   **Validation:** Add input validation and custom exception handling.
-   **Security:** Implement authentication and authorization using Spring Security (e.g., JWT).
-   **Testing:** Create a comprehensive suite of unit and integration tests.
-   **Containerization:** Dockerize the application and database for easy deployment.
-   **CI/CD:** Set up a continuous integration and deployment pipeline.
