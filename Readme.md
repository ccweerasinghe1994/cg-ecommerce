# CG NEXUS ECOMMERCE API

### Step-by-step guide to run the Spring Boot application:

1. **Build the application**
```bash
./mvnw clean install
```

2. **Run Docker Compose for MySQL**
```bash
docker-compose up -d
```

3. **Run the application**
```bash
./mvnw spring-boot:run
```

### Alternative run methods:

1. **Using Java directly** (after building):
```bash
java -jar target/cg-ecommerce-0.0.1-SNAPSHOT.jar
```

2. **Debug mode**:
```bash
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
```

### Environment Variables (optional):
```bash
export APP_NAME=cg-ecommerce
export PORT=8080
export CONTEXT_PATH=/api/v1
export DATASOURCE_URL=jdbc:mysql://localhost:3306/cgecommercedb
export DATASOURCE_USERNAME=cgecommerceuser
export DATASOURCE_PASSWORD=cgecommercepassword
```

### Verify the application:
```bash
curl http://localhost:8080/api/v1/public/categories
```

### Stop the application:
- Press `Ctrl+C` to stop the application
- To stop MySQL: `docker-compose down`

The application will be available at: 

http://localhost:8080/api/v1