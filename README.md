# Recipe Service

A multi-user RESTful web service built using Java and the Spring Boot framework that allows authenticated users to manage a collection of recipes. This service provides a complete API for storing, retrieving, updating, and deleting recipe records, alongside dynamic search capabilities and resource-level authorization.

This project was developed as a structured backend engineering exercise to implement secure API design, business logic validation, and relational data persistence.

## Tech Stack

* **Language & Runtime:** Java 11
* **Framework:** Spring Boot (Spring Web, Spring Security)
* **Data Access:** Spring Data JPA, Hibernate
* **Data Validation:** Hibernate Validator (Bean Validation API)
* **Utilities:** Project Lombok
* **Build System:** Gradle

## Repository Structure

The complete source code can be found within the subproject directory:
* **Source Implementation:** `Recipes/task/src/`

## API Specifications

### Authentication & User Management
| Method | Endpoint | Description | Authentication |
| :--- | :--- | :--- | :--- |
| POST | `/api/register` | Registers a new user account with an email and password. | Anonymous |

### Recipe Management
| Method | Endpoint | Description | Authentication |
| :--- | :--- | :--- | :--- |
| POST | `/api/recipe/new` | Submits a new recipe record. Returns the generated unique ID. | HTTP Basic Auth |
| GET | `/api/recipe/{id}` | Retrieves the JSON data for a specific recipe by its ID. | HTTP Basic Auth |
| PUT | `/api/recipe/{id}` | Updates all fields of an existing recipe. Restricted to the author. | HTTP Basic Auth |
| DELETE | `/api/recipe/{id}` | Deletes a recipe from the system. Restricted to the author. | HTTP Basic Auth |
| GET | `/api/recipe/search` | Filters recipes using query parameters (`category` or `name`). | HTTP Basic Auth |

## Local Installation and Execution

### Prerequisites
* Java 11 Development Kit (JDK) or higher installed.
* An environment configured to run Gradle wrapper scripts.

### Build Instructions

1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/Marcus633/Recipes.git
   ```

2. Navigate to the task application directory:
 ```bash
   cd Recipes/task
 ```
3. Execute the Spring Boot run task using the Gradle wrapper:
 ```bash
   gradlew.bat bootRun
 ```

The application initializes by default on port `8080`. You can send HTTP requests to `http://localhost:8080` using tools like `curl` or Postman to interact with the service.

