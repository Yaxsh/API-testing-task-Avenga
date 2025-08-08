# 📘 API Automation Testing

This project automates the testing of a RESTful API using **Java**, **Rest Assured**, **TestNG**, and **Jackson**, with reporting powered by **Allure**.

---

## 📦 Dependencies

| Tool / Library     | Purpose                            |
|--------------------|------------------------------------|
| **Rest Assured**   | For writing and executing HTTP API tests |
| **TestNG**         | Test framework for managing and grouping test cases |
| **Jackson**        | JSON serialization/deserialization (POJO <-> JSON) |
| **Allure**         | Generates beautiful test execution reports |
| **Maven**          | Build and dependency management |

**Project instructions**

1. Clone the repository:
   git clone https://github.com/Yaxsh/API-testing-task-Avenga.git
   cd API-testing-task-Avenga
2. Build the project:
   mvn clean compile
3. Run tests:
   mvn test
4. Generate Allure report (required Allure CLI):
   allure serve target/allure-results
