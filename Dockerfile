# Build Stage
FROM eclipse-temurin:21-alpine AS build

# Set working directory
WORKDIR /app

# Copy maven files first
COPY .mvn/ .mvn
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

# Fix line endings and set execute permissions
RUN chmod +x ./mvnw
RUN dos2unix ./mvnw

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Runtime Stage
FROM eclipse-temurin:21-alpine

# Create non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Create upload directory and set permissions
RUN mkdir -p /app/uploads && chown -R appuser:appgroup /app

# Copy the Spring Boot JAR from the build stage
COPY --from=build /app/target/*.jar /app/app.jar

# Switch to the non-root user
USER appuser

# Expose the port your application will run on
# EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]