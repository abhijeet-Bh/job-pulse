# RESTful API Backend Project (JobPulse)

![Spring Boot Badge](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff&style=flat-square)
![Spring Security Badge](https://img.shields.io/badge/Spring%20Security-6DB33F?logo=springsecurity&logoColor=fff&style=flat-square)
![Spring Badge](https://img.shields.io/badge/Spring-6DB33F?logo=spring&logoColor=fff&style=flat-square)
![OpenJDK Badge](https://img.shields.io/badge/OpenJDK-000?logo=openjdk&logoColor=fff&style=flat-square)
![PostgreSQL Badge](https://img.shields.io/badge/PostgreSQL-4169E1?logo=postgresql&logoColor=fff&style=flat-square)
![Docker Badge](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=fff&style=flat-square)
---

## Overview

This project is a backend service built using `Spring Boot` and `Java`. It exposes RESTful API endpoints for managing
resources in a `PostgreSQL database`. The application is containerized using `Docker`, enabling easy deployment and
scaling.

For Security of endpoints and app, This project implements `spring-security`. All endpoints (if required) are secured to
be accessed by only authentic users.

### Project Dependencies

- **Java**: `opne jdk: v22.0`.
- **Spring Boot**: `v3.3.3`.
- **PostgreSQL**: `v16.0`.
- **Docker**: with `docker-compose v3.0`.
- **Maven**: `v4.0.0`.

### Features

- RESTful API endpoints for CRUD operations.
- User authentication and authorization.
- Integration with PostgreSQL for persistent storage.
- Dockerized application for easy deployment.

---

## Getting Started

### Installation

1. **Clone the repository:**

   ```shell
   git clone https://github.com/abhijeet-Bh/job-pulse.git
   cd job-pulse
   ```

2. **Build the application:**

   ```shell
   mvn clean install
   ```

### Running the Application

You can run the application using `Maven` or `Docker`.

#### Running with Maven:

1. **Run the Spring Boot application:**

   ```shell
   mvn spring-boot:run
   ```

   The application will start on `http://localhost:8080`.

2. **Connect to the PostgreSQL database:**

   Ensure PostgreSQL is running and configured as per `application.properties`.

#### Running with Docker:

1. **Build and run the Docker containers:**

   ```bash
   docker-compose up --build
   ```

   This command will start the Spring Boot application and PostgreSQL database in Docker containers.

---

## API Documentation

### Endpoints

Below are some example endpoints. Replace these with your actual API endpoints.

- **GET** `/job`: Retrieve all jobs.
- **GET** `/jobs/{jobId}`: Retrieve a single job by ID.
- **POST** `/jobs`: Create a new job.
- **PUT** `/jobs/{id}`: Update an existing job by ID.
- **DELETE** `/jobs/{id}`: Delete a job by ID.

### Example Request/Response

- **Request - `GET` `http://localhost:8080/job`:**
- **Response:**

  ```json
  {
    "success": true,
    "statusCode": 200,
    "data": [
        {
            "id": "95f25de1-a4a3-4b46-aecc-becc95f2456f",
            "title": "Software Engineer",
            "description": "Software Engineering Job, proficient in JAVA",
            "minSalary": "18 LPA",
            "maxSalary": "20 LPA",
            "location": "Hydrabad",
            "company": {
                "id": 1,
                "name": "Google Inc.",
                "description": "American Software Company.",
                "reviews": []
            }
        }
    ],
    "message": null
    }
  ```

---

## Database

### Schema

The database schema is managed by `Spring Data JPA`. Here’s an overview of the `jobs` tables:

- **Resources Table:**
    - `id` (Primary Key)
    - `title`
    - `description`
    - `minSalary`
    - `maxSalary`
    - `location`
    - `company`

Ensure the database configuration matches your `application.properties` or `application.yml` settings.

---

## Docker Setup

This project includes a `Dockerfile` and `docker-compose.yml` for easy setup.

### Dockerfile

The `Dockerfile` is used to build the Spring Boot application into a Docker image.

### Docker Compose

`docker-compose.yml` orchestrates the Spring Boot application and PostgreSQL database.

To run:

```bash
docker-compose up --build
```

This command will start both the application and the database, making the API accessible at `http://localhost:8080`.

## Contributing

Contributions are welcome! Please fork this repository, create a new branch, and submit a pull request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

This `README.md` should provide enough information for others to understand the project, set it up on their machines,
and contribute if they wish. Be sure to replace placeholder text (like URLs and specific details) with information
relevant to your actual project.