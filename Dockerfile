# Etapa 1: Build
FROM gradle:8.11-jdk21 AS build

WORKDIR /app

# Copiar archivos de configuración de Gradle
COPY build.gradle settings.gradle ./

# Descargar dependencias (se cachea si no cambian)
RUN gradle dependencies --no-daemon || true

# Copiar código fuente
COPY src ./src

# Compilar la aplicación
RUN gradle bootJar --no-daemon

# Etapa 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar el JAR compilado desde la etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]