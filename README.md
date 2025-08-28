# 🌌 StarWars Missions API

A RESTful API built with Spring Boot to manage characters and missions in the Star Wars universe. This project serves as a practical application of modern Java back-end development, featuring a versioned database, secure configuration, and a clean, scalable architecture.

## ✨ Features Implemented

-   **Service Layer Architecture:** Business logic is encapsulated in Service classes, separating concerns from the web layer.
-   **Data Transfer Object (DTO) Pattern:** The API uses specific DTOs for requests (`CharacterCreateDTO`) and responses (`CharacterResponseDTO`), providing a clean and secure public contract.
-   **Public UUIDs:** All API endpoints use non-sequential `UUID`s for resource identification, enhancing security and preventing data scraping.
-   **Type-Safe Data Modeling:** Uses Java `Enums` for concepts like `MissionStatus`, `MissionDifficulty`, and `Faction` to ensure data integrity.
-   **Business Logic Validation:** The service layer validates incoming data (e.g., ensuring a character's `rank` is valid for their `faction`).
-   **Database Versioning:** Employs **Flyway** to manage database schema changes and seed initial data.
-   **Secure Configuration:** Database credentials are kept out of version control using a `.env` file.

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
-   A running PostgreSQL instance with the `pgcrypto` extension enabled.

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
    ```sh
    ./mvnw clean install
    ```

4.  **Run the application:**
    ```sh
    java -jar target/projectsw-0.0.1-SNAPSHOT.jar
    ```
    The application will start on `http://localhost:8181`. Flyway will automatically run the database migrations on the first startup.

## API Endpoints

All endpoints use `UUID`s as public identifiers.

### Characters (`/character`)

| Method | URL                | Description                               |
| ------ | ------------------ | ----------------------------------------- |
| `POST` | `/create`          | Creates a new character.                  |
| `GET`  | `/all`             | Retrieves a list of all characters.       |
| `GET`  | `/list/{id}`       | Retrieves a single character by its UUID. |
| `PUT`  | `/update/{id}`     | Updates a character by its UUID.          |
| `DELETE`| `/delete/{id}`    | Deletes a character by its UUID.          |

### Missions (`/mission`)

| Method | URL                | Description                               |
| ------ | ------------------ | ----------------------------------------- |
| `POST` | `/create`          | Creates a new mission (status defaults to `PENDING`). |
| `GET`  | `/all`             | Retrieves a list of all missions.         |
| `GET`  | `/list/{id}`       | Retrieves a single mission by its UUID.   |
| `PUT`  | `/update/{id}`     | Updates a mission by its UUID.            |
| `DELETE`| `/delete/{id}`    | Deletes a mission by its UUID.            |

### Sample Request Body for `POST /character/create`

```json
{
  "name": "Poe Dameron",
  "email": "poe@resistance.org",
  "age": 32,
  "faction": "REBEL_ALLIANCE",
  "rank": "COMMANDER",
  "homeland": "Yavin 4",
  "missionId": "a4b1c2d3-e4f5-6789-0123-456789abcdef" // Optional UUID of the mission
}
```

## 🎯 Future Roadmap

This project serves as a foundation. Future enhancements include:

-   **Input Validation:** Implement robust validation on DTOs using annotations (`@NotNull`, `@Email`, `@Size`, etc.).
-   **Custom Exception Handling:** Create a `GlobalExceptionHandler` to provide clear and consistent error responses for the API.
-   **Security:** Implement authentication and authorization using Spring Security (e.g., JWT).
-   **Testing:** Create a comprehensive suite of unit and integration tests.
-   **Containerization:** Dockerize the application and database for easy deployment.
-   **CI/CD:** Set up a continuous integration and deployment pipeline.
