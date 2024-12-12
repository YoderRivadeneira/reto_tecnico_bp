
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY rabbit_bus-1.0-SNAPSHOT-plain.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]


