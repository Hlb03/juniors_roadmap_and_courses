FROM openjdk:17-alpine

COPY /target/from-start-to-success-v1.0.jar /fs2s/backend-v1.0.jar

ENTRYPOINT ["java", "-jar", "/fs2s/backend-v1.0.jar"]