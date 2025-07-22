# certjavatesting

A Java Spring Boot microservice that:
- Loads certificates from `/tmp` into a local Java keystore (`keystore.jks`) on startup.
- Exposes a `/health` endpoint to check application health.

## Build

```
mvn clean package
```

## Run

```
mvn spring-boot:run
```

## Features

- **Certificate Loader**: On startup, scans `/tmp` for `.crt` or `.pem` files and loads them into `keystore.jks`.
- **Health Endpoint**: Visit [http://localhost:8080/health](http://localhost:8080/health) to check if the app is running.

## Requirements
- Java 17+
- Maven 

---

## Project Chat History & Plan

### Initial Requirements
1. Get the certificates from /tmp folder and load it into the key or Trust store.
2. Add a health file to check the application health activity whether app is working or not.

### Project Plan
- Scaffold a Maven-based Spring Boot project.
- Implement a component to scan `/tmp` for certificates and load them into a keystore/truststore.
- Add a REST controller for the health check endpoint.

### Implementation Steps
- Created `pom.xml` for a Spring Boot project.
- Added the main application class.
- Added a certificate loader component.
- Added a health check controller.
- Added a `README.md` and an empty `application.properties`.

### Chat Log
Your Spring Boot microservice project has been recreated with the following features:

1. **Certificate Loader**: On startup, it loads certificates from `/tmp` into a Java keystore (`keystore.jks`).
2. **Health Endpoint**: Exposes a `/health` endpoint to check if the application is running.

**Project Structure:**
- `pom.xml`: Maven configuration for Spring Boot, web, and security.
- `src/main/java/com/example/certservice/CertjavatestingApplication.java`: Main application class.
- `src/main/java/com/example/certservice/CertificateLoader.java`: Loads certificates from `/tmp` into a keystore.
- `src/main/java/com/example/certservice/HealthController.java`: REST controller for `/health`.
- `src/main/resources/application.properties`: Spring Boot config (empty, ready for extension).
- `README.md`: Build, run, and feature instructions.

You can now build and run your project with Maven. If you need further customization or want to add more endpoints, just let me know! 