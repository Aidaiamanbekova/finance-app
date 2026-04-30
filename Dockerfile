# ==================== STAGE 1: BUILD THE APPLICATION ====================

# 1. Use a pre‑made image that contains Maven and Java 17
FROM maven:3.8.4-openjdk-17-slim AS build

# 2. Set the working directory inside the container to /app
WORKDIR /app

# 3. Copy only the pom.xml file first (to cache dependencies)
COPY pom.xml .

# 4. Download all dependencies (this step is cached unless pom.xml changes)
RUN mvn dependency:go-offline

# 5. Copy the rest of your source code into the container
COPY src ./src

# 6. Package your application into a JAR file (skip tests to save time)
RUN mvn clean package -DskipTests

# ==================== STAGE 2: CREATE THE FINAL RUNTIME IMAGE ====================

# 7. Use a smaller image with only Java 17 (no Maven, no build tools)
FROM openjdk:17-jdk-alpine

# 8. Set working directory again
WORKDIR /app

# 9. Copy the JAR file from the first stage into this new image
COPY --from=build /app/target/*.jar app.jar

# 10. Tell Docker that the container will listen on port 8080 (for documentation only)
EXPOSE 8080

# 11. Define the command to run when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]