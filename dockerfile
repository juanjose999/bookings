# Utiliza una imagen base con OpenJDK 17 y Gradle 7.4.0

FROM gradle:7.4.0-jdk17 AS build

# Establece el directorio de trabajo

WORKDIR /app

# Copia los archivos de tu proyecto al directorio de trabajo

COPY . .

# Construye tu aplicación con Gradle

RUN gradle build --no-daemon

# Cambia a una imagen más ligera de OpenJDK 17 para la ejecución

FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo

WORKDIR /app

# Copia el archivo JAR de tu aplicación al directorio de trabajo

COPY --from=build /api/build/libs/api-0.0.1-SNAPSHOT.jar .

COPY --from=build /api/src/main/resources/application.properties .

# Exponer el puerto que utilizará la aplicación

EXPOSE 8082

# Define el comando de inicio de la aplicación

CMD ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]