#FROM --platform=linux/amd64 maven:3.9.0-eclipse-temurin-19-alpine as build
#WORKDIR .
#COPY pom.xml .
#RUN mvn dependency:go-offline
#RUN mvn clean package
#
#FROM --platform=linux/amd64 eclipse-temurin:19-jdk
#EXPOSE 8080
#COPY --from=build ./target/api-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:21-jdk
EXPOSE 8080
COPY ./target/api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]