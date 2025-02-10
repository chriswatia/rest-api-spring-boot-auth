FROM openjdk:21-jdk-slim

LABEL version="1.0"

EXPOSE 8080:8080

# Set the working directory
WORKDIR /app

# Copy the jar file to the working directory
COPY target/*.jar /app/app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]