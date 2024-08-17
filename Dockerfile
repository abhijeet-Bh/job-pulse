# the base image
FROM amazoncorretto:22-alpine3.16

COPY target/*.jar jobsite.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "jobsite.jar"]
