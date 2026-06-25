<<<<<<< HEAD
# Paso 1: Construir el archivo JAR usando Maven
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080

=======
# Paso 1: Compilar la aplicación en la nube
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Paso 2: Ejecutar la aplicación
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/*.jar app.jar
EXPOSE 8080
>>>>>>> 9d4534863726a2b1cdff91f7c5ee4a8a90c00da0
ENTRYPOINT ["java", "-jar", "app.jar"]