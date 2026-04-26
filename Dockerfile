# ---- Stage 1: Build ----
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copiar archivos de configuración
COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./

# Descargar dependencias (cache layer)
RUN mvn dependency:go-offline -B

# Copiar código fuente
COPY src ./src

# Compilar y empaquetar (excluyendo tests)
RUN mvn package -DskipTests -Dquarkus.package.jar.type=uber-jar

# ---- Stage 2: Runtime ----
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Instalar dependencias necesarias para Playwright
RUN apk add --no-cache \
    chromium \
    nss \
    freetype \
    harfbuzz \
    ca-certificates \
    ttf-freefont \
    && rm -rf /var/cache/apk/*

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/*-runner.jar app.jar

# Exponer puerto
EXPOSE 8080

# Variables de entorno (se pueden sobrescribir en docker-compose)
ENV QUARKUS_DATASOURCE_DB_KIND=postgresql
ENV QUARKUS_DATASOURCE_USERNAME=postgres
ENV QUARKUS_DATASOURCE_PASSWORD=postgres
ENV QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://db:5432/db_srgs
ENV QUARKUS_REDIS_HOSTS=redis://redis:6379
ENV QUARKUS_HTTP_PORT=8080
ENV SCRAPER_HEADLESS=true
ENV APP_UPLOAD_DIR=/app/uploads

# Crear directorio de uploads
RUN mkdir -p /app/uploads

# Comando de inicio
ENTRYPOINT ["java", "-jar", "app.jar"]