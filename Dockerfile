FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn package -DskipTests

FROM openjdk:11-jdk-slim

EXPOSE 8080

COPY --from=build /app/target/fcm-test.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
