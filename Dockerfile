# Etapa 1: Build
FROM gradle:8.14.3-jdk21 AS build

WORKDIR /app


COPY build.gradle settings.gradle ./
COPY gradle ./gradle
COPY gradlew ./


RUN ./gradlew dependencies --no-daemon


COPY src ./src


RUN ./gradlew bootJar --no-daemon


FROM eclipse-temurin:21-jre-alpine

WORKDIR /app


COPY --from=build /app/build/libs/*.jar app.jar

# Exponer el puerto
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
