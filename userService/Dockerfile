FROM maven:latest as build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:10-jre-slim
COPY --from=build /home/app/target/userService-0.0.1-SNAPSHOT.jar /usr/local/lib/userService.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/usr/local/lib/userService.jar"]