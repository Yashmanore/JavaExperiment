# Experiment 11: Student Quick Manager (Spring Boot + Fetch API)

A minimal full-stack application demonstrating how a static HTML/CSS/JS frontend can communicate with a Spring Boot REST API using the browser's Fetch API.

## Project Structure

This is a standard Maven-based Spring Boot project structure:
## Setup and Run Instructions

### Prerequisites

* **Java Development Kit (JDK) 11+** installed.
* **Apache Maven** installed (or use your IDE's built-in tools).

### Steps

1.  **Clone or Create the Project:**
    Ensure all files are placed in their correct structure (as listed above).

2.  **Run the Spring Boot Application:**
    Navigate to the project's root directory (`spring-fetch-students/`) in your terminal and execute the Maven command:

    ```bash
    mvn spring-boot:run
    ```
    The application will start, typically on port 8080.

3.  **Access the Application:**
    Open your web browser and navigate to:
    ```
    http://localhost:8080/
    ```
    The `index.html` file is served automatically.

## API Endpoints

The frontend communicates with the following REST endpoints defined in `StudentRestController.java`:

| HTTP Method | URL | Description |
| :--- | :--- | :--- |
| **GET** | `/api/students` | Retrieves the list of all students. |
| **POST** | `/api/students` | Creates a new student (requires JSON body). |
| **DELETE** | `/api/students/{id}` | Deletes a student by their unique ID. |

## Extra Features / Extensions (Not Implemented)

The current implementation uses an in-memory store (`Map`), meaning all data is lost when the server restarts.

1.  **Persistence Upgrade:** Replace the `Map<Long, StudentDto>` in `StudentRestController` with a proper **Spring Data JPA** Entity (`Student.java`) and Repository (`StudentRepository.java`) to use an embedded database like H2 or a PostgreSQL database.
2.  **Edit Functionality:** Implement a `PUT /api/students/{id}` endpoint and add corresponding UI to allow users to modify existing student details.
3.  **Advanced Error Handling:** Update the backend to return structured JSON error bodies (e.g., containing field-level validation errors) and enhance `app.js` to parse and display these errors gracefully.