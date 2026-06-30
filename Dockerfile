# Stage 1: Build
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B
COPY src/ src/
RUN ./mvnw package -DskipTests -B && mv target/*.jar target/app.jar

# Stage 2: Extract layers (Spring Boot layer tools for faster pulls)
FROM eclipse-temurin:21-jdk-alpine AS extract
WORKDIR /builder
COPY --from=build /app/target/app.jar application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

# Stage 3: Runtime — all secrets come in at runtime via environment variables
FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S appgrp && adduser -S appuser -G appgrp
WORKDIR /application
COPY --from=extract /builder/extracted/dependencies/ ./
COPY --from=extract /builder/extracted/spring-boot-loader/ ./
COPY --from=extract /builder/extracted/snapshot-dependencies/ ./
COPY --from=extract /builder/extracted/application/ ./

USER appuser
EXPOSE 8888

# Runtime env vars — supply these in docker-compose or Portainer stack env
# ENV GIT_URI, encrypt_key, SPRING_SECURITY_USER_NAME, SPRING_SECURITY_USER_PASSWORD

ENTRYPOINT ["java", "-jar", "application.jar"]
