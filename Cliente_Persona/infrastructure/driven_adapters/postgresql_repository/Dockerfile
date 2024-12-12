
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY postgresql_repository-1.0-SNAPSHOT-plain /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]