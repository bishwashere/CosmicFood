FROM gradle:jdk10 as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:10-jre-slim
COPY --from=builder /home/gradle/src/build/libs/food-service-0.0.1-SNAPSHOT.jar food-service.jar
ENTRYPOINT ["java","-jar","/food-service.jar"]