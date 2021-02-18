FROM gradle:4.7.0-jdk8-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8-jre-slim

EXPOSE 8080

# copy the packaged jar file into our docker image
COPY --from=build /home/gradle/src/build/libs/*.jar /gt6u-snapshot.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/gt6u-snapshot.jar"]