FROM openjdk:8-jre-slim

COPY build/libs/* /

# set the startup command to execute the jar
CMD ["java", "-jar", "/build/libs/gt6u-snapshot.jar"]