FROM openjdk:21-ea-slim

WORKDIR /app

CMD ["java", "-jar", "/app/app.jar"]