# Task Management Application

A full-stack task management application with Spring Boot backend and React frontend.

## Overview

This repository contains the Spring Boot backend for the task management application. For the frontend React application, check out and you can clone it using the following link:
[Frontend Repository](https://github.com/Varad12v5/Task-React-App.git)

## Technologies Used

### Backend
- Java 17
- Spring Boot 3.3.10
- PostgreSQL
- Docker
- Maven

### Frontend (React Application)
- React

## Getting Started

### Backend Setup

#### 1. Clone the repository
```bash
git clone https://github.com/Varad12v5/Task-Tracking-App-Spring-Boot.git
cd tasks
```

#### 2. Start the Database
The project uses PostgreSQL running in Docker. Start it using:
```bash
docker-compose up -d
```

This will start PostgreSQL on port 5432 with:
- Username: postgres
- Password: postgres
- Database: Task

#### 3. Build and Run the Application
```bash
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup

#### 1. Clone the frontend repository
```bash
git clone https://github.com/Varad12v5/Task-Tracking-App-React.git
cd Task-Tracking-App-React
```

#### 2. Install dependencies and start the application:
```bash
npm install
npm start
```

The frontend will start on `http://localhost:3000`

## API Endpoints

### Task Lists

#### Get All Task Lists
```http
GET /task-lists
```
Response:
```json
[
  {
    "id": "7e6ae92d-f108-4725-aa4a-f93697192bcc",
    "title": "My Task List",
    "description": "Description of the task list",
    "count": 0,
    "progress": 0.0,
    "tasks": []
  }
]
```

#### Create Task List
```http
POST /task-lists
```
Request body:
```json
{
  "title": "New Task List",
  "description": "Description of the new task list"
}
```
Response:
```json
{
  "id": "7e6ae92d-f108-4725-aa4a-f93697192bcc",
  "title": "New Task List",
  "description": "Description of the new task list",
  "count": 0,
  "progress": 0.0,
  "tasks": null
}
```

#### Get Task List by ID
```http
GET /task-lists/{task_list_id}
```
Response:
```json
{
  "id": "7e6ae92d-f108-4725-aa4a-f93697192bcc",
  "title": "My Task List",
  "description": "Description of the task list",
  "count": 2,
  "progress": 50.0,
  "tasks": [
    {
      "id": "1e6ae92d-f108-4725-aa4a-f93697192bcc",
      "title": "Task 1",
      "description": "Task 1 description",
      "status": "PENDING"
    }
  ]
}
```

#### Update Task List
```http
PUT /task-lists/{task_list_id}
```
Request body:
```json
{
  "title": "Updated Task List",
  "description": "Updated description"
}
```

### Tasks

#### Get All Tasks in a Task List
```http
GET /task-lists/{task_list_id}/tasks
```
Response:
```json
[
  {
    "id": "1e6ae92d-f108-4725-aa4a-f93697192bcc",
    "title": "Task 1",
    "description": "Task 1 description",
    "status": "PENDING"
  }
]
```

#### Create Task
```http
POST /task-lists/{task_list_id}/tasks
```
Request body:
```json
{
  "title": "New Task",
  "description": "New task description",
  "status": "PENDING"
}
```
Response:
```json
{
  "id": "1e6ae92d-f108-4725-aa4a-f93697192bcc",
  "title": "New Task",
  "description": "New task description",
  "status": "PENDING"
}
```

#### Get Task by ID
```http
GET /task-lists/{task_list_id}/tasks/{task_id}
```
Response:
```json
{
  "id": "1e6ae92d-f108-4725-aa4a-f93697192bcc",
  "title": "Task 1",
  "description": "Task 1 description",
  "status": "PENDING"
}
```

#### Update Task
```http
PUT /task-lists/{task_list_id}/tasks/{task_id}
```
Request body:
```json
{
  "title": "Updated Task",
  "description": "Updated description",
  "status": "COMPLETED"
}
```

#### Delete Task
```http
DELETE /task-lists/{task_list_id}/tasks/{task_id}
```

## Database Configuration

The application uses PostgreSQL with the following configuration (in `application.properties`):
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/Task
spring.datasource.username=postgres  [Your Username]
spring.datasource.password=vsb123   [Your Password]
```

## Docker Support

The project includes a `docker-compose.yml` for running PostgreSQL. This ensures consistent development environments across the team.

## Project Structure

- `controller/` - REST API controllers
- `service/` - Business logic implementation
- `domain/` - Entity and DTO classes
- `mappers/` - Object mapping classes
- `repo/` - Database repositories
