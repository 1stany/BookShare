# Fase 1: Build (usa Maven con Java 17)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Fase 2: Esecuzione (usa Java 17 leggero)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Prende il file .jar generato nella fase precedente e lo copia
COPY --from=build /app/target/*.jar app.jar

# Espone la porta 8080 (quella standard di Spring)
EXPOSE 8080

# Comando per avviare l'applicazione
ENTRYPOINT ["java", "-jar", "app.jar"]