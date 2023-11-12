FROM maven:3.9.5-amazoncorretto-17 AS maven

WORKDIR /usr/src/app
COPY . /usr/src/app

RUN mvn clean compile verify -DskipTests=true package

FROM openjdk:17-jdk-slim

ARG JAR_FILE=road-disruption-info-api.jar
WORKDIR /opt/app
COPY --from=maven /usr/src/app/target/${JAR_FILE} /opt/app/
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","road-disruption-info-api.jar"]