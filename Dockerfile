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
FROM eclipse-temurin:21-jre

WORKDIR /app

# Instalar dependencias necesarias para Playwright (sin chromium manual)
RUN apt-get update && apt-get install -y \
    wget \
    ca-certificates \
    fonts-freefont-ttf \
    libglib2.0-0 \
    libnss3 \
    libatk1.0-0 \
    libatk-bridge2.0-0 \
    libcups2 \
    libdrm2 \
    libxkbcommon0 \
    libxcomposite1 \
    libxdamage1 \
    libxfixes3 \
    libxrandr2 \
    libgbm1 \
    libasound2t64 \
    libpangocairo-1.0-0 \
    libpango-1.0-0 \
    libcairo2 \
    && rm -rf /var/lib/apt/lists/*

# 👇 IMPORTANTE: carpeta donde Playwright guarda browsers
ENV PLAYWRIGHT_BROWSERS_PATH=/ms-playwright

# Crear carpeta (permisos)
RUN mkdir -p /ms-playwright

# Copiar JAR
COPY --from=build /app/target/*-runner.jar /app/app.jar

EXPOSE 8080

ENV QUARKUS_HTTP_PORT=8080
ENV APP_UPLOAD_DIR=/app/uploads

RUN mkdir -p /app/uploads

# Usuario no root
RUN useradd -m app
RUN chown -R app:app /app /ms-playwright
USER app

# 👇 Aquí Playwright descargará Chromium automáticamente en runtime
ENTRYPOINT ["java", "-jar", "app.jar"]