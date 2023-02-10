FROM openjdk:12
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} dynamic-report-back-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/dynamic-report-back-0.0.1-SNAPSHOT.jar"]
