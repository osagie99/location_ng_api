FROM openjdk:11-jre-slim-buster
ADD target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar" ]