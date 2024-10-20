# Introduction

API responsible for managing customers wish lists.

# Specifications

- Java 21
- Maven 4.0.0
- Spring Boot 3.3.4

# Configuration and Local Setup

1. **First things first:**
   Configure your IntelliJ IDEA to use the coding style provided in the
   file [java-google-style.xml](src%2Fmain%2Fresources%2Fintellij-style%2Fjava-google-style.xml).
   ![How To](src%2Fmain%2Fresources%2Fintellij-style%2Fhow-to.png)
2. **Configure your Intellij IDEA to automatically reformat code on save using coding style**
   [Automatically reformat code on save](https://www.jetbrains.com/help/idea/reformat-and-rearrange-code.html#reformat-on-save)

3. **Local Setup:**
   Before running the application locally, ensure you have MongoDB installed and the Docker and
   docker-compose plugins set up on your computer. If you don't have MongoDB, the docker folder
   might help.

4. To help with testing, you can import the postman model into the postman folder, or access swagger
   via the url http://localhost:8080/backend-wishlist/swagger-ui/index.html