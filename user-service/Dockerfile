FROM maven:3.8.4-openjdk-17 as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

#use the jdk image to run the service
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/user-service-0.0.1-SNAPSHOT.jar user-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-service.jar"]