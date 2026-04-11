# Use base image
FROM eclipse-temurin:17-jdk-focal AS builder

# Set working directory
WORKDIR /app

# Copy jar file into container
COPY target/app.jar app.jar

# Expose port (Spring Boot default)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
