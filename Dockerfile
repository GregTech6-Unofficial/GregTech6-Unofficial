FROM openjdk:8-jre-alpine3.9

# copy the packaged jar file into our docker image
COPY ./build/libs/gt6u-snapshot.jar /gt6u-snapshot.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/gt6u-snapshot.jar"]