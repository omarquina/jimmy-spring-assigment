FROM maven:3.9.6-eclipse-temurin-21-alpine AS develop
EXPOSE ${PRIVATE_PORT} 
# Set the working directory in the container
WORKDIR /app
CMD ["./mvnw","spring-boot:run"]
# Use an official Maven image as the base image
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
EXPOSE ${PRIVATE_PORT} 
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
#FROM build AS complete
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package -DskipTests
#CMD ["./mvnw", "spring-boot:run"]
# Use an official OpenJDK image as the base image
FROM openjdk:21-ea-slim
# Set the working directory in the container
#WORKDIR /app
# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/rides-0.0.1-SNAPSHOT.jar /app/app.jar
#COPY ./target/safety-0.0.1-SNAPSHOT.jar /app/app.jar # if mvn build was done locally.

# Set the command to run the application
CMD ["java", "-jar", "/app/app.jar"]
