FROM openjdk:8-jre-alpine3.9

# copy the packaged jar file into our docker image
COPY ./build/libs/*.jar /gregtech_1.7.10-snapshot-unofficial.jar

# set the startup command to execute the jar
CMD ["java", "-jar", "/gregtech_1.7.10-snapshot-unofficial.jar"]