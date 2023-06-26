FROM maven:3.9.0-eclipse-temurin-19 as build
WORKDIR .
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 mvn -f ./pom.xml clean package


FROM eclipse-temurin:19-jdk
EXPOSE 8080
COPY --from=build ./target/api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
