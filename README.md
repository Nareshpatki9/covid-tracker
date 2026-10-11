# COVID-19 Tracker

A web application built using Java and Spring Boot to track COVID-19 statistics through REST APIs. The application provides a dashboard for viewing country-wise COVID-19 information, including total cases, deaths, and active cases.

## Features

* View COVID-19 statistics on a web dashboard.
* Retrieve COVID-19 data through REST APIs.
* Display country-wise statistics and trends using charts.
* Store and manage statistics using MySQL.
* Follow a layered architecture with controllers, services, repositories, DTOs, and entities.
* Handle API errors and exceptions.
* Include unit and controller tests.

## Technologies Used

* **Backend:** Java, Spring Boot
* **API:** REST API, Spring RestClient
* **Database:** MySQL, Spring Data JPA, Hibernate
* **Frontend:** HTML, CSS, JavaScript, Chart.js
* **Build Tool:** Maven
* **Testing:** JUnit, Spring Boot Test
* **Version Control:** Git and GitHub

## Project Structure

```text
covid-tracker/
├── src/
│   ├── main/
│   │   ├── java/com/covidtracker/
│   │   │   ├── client/
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── mapper/
│   │   │   ├── repository/
│   │   │   └── service/
│   │   └── resources/
│   │       ├── static/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Prerequisites

Install the following before running the application:

* Java JDK 21
* MySQL Server
* Git (optional, for cloning the repository)

## Setup and Installation

### 1. Clone the repository

```bash
git clone https://github.com/Nareshpatki9/covid-tracker.git
cd covid-tracker
```

### 2. Create the MySQL database

Open MySQL and execute:

```sql
CREATE DATABASE covid_tracker;
```

### 3. Configure database credentials

Configure your database connection in `src/main/resources/application.properties` using environment variables:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/covid_tracker?useSSL=false&serverTimezone=Asia/Kolkata&allowPublicKeyRetrieval=true}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:}
```

Set `DB_USERNAME` and `DB_PASSWORD` to match your local MySQL configuration. Do not commit real passwords or other secrets to GitHub.

### 4. Run the application

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, if Maven is installed:

```bash
mvn spring-boot:run
```

### 5. Open the application

Once the application starts, open:

http://localhost:8080

## Running Tests

On Windows:

```powershell
.\mvnw.cmd test
```

## External API

The application is configured to use the disease.sh COVID-19 API:

https://disease.sh/v3/covid-19

External API availability and data freshness may vary.

## Future Enhancements

* Add more country comparisons and filtering options.
* Improve dashboard responsiveness and accessibility.
* Add historical trend analysis.
* Improve loading, validation, and API failure messages.

## Author

**Naresh Patki**

GitHub: [Nareshpatki9](https://github.com/Nareshpatki9)

Project Repository: [COVID Tracker](https://github.com/Nareshpatki9/covid-tracker)
