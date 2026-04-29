# ---- Stage 1: Build ----
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn package -DskipTests -Dquarkus.package.jar.type=uber-jar

# ---- Stage 2: Runtime ----
FROM mcr.microsoft.com/playwright/java:v1.45.0-jammy

WORKDIR /app

COPY --from=build /app/target/*-runner.jar /app/app.jar

EXPOSE 8080

ENV QUARKUS_HTTP_PORT=8080
ENV APP_UPLOAD_DIR=/app/uploads

RUN mkdir -p /app/uploads

ENTRYPOINT ["java", "-jar", "app.jar"]