FROM jelastic/maven:3.9.5-openjdk-21 as Build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/taxservice-0.0.1-SNAPSHOT.jar taxservice.jar

# Expose the port the app runs on
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "taxservice.jar"]
