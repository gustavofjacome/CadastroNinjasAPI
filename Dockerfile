# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw .
RUN chmod +x mvnw

COPY pom.xml .
RUN ./mvnw dependency:go-offline -B

COPY src/ src/
RUN ./mvnw package -DskipTests -B

# Run stage
FROM eclipse-temurin:21-jre
WORKDIR /app

RUN groupadd -r appuser && useradd -r -g appuser appuser

COPY --from=build /app/target/*.jar app.jar

RUN chown -R appuser:appuser /app
USER appuser

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=5s --start-period=15s --retries=3 \
  CMD curl -f http://localhost:8080/web/ || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
