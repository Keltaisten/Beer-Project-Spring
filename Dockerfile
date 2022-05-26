FROM openjdk:17
WORKDIR /opt/app
COPY target/Beer-Project-Spring-0.0.1-SNAPSHOT.jar beerproject.jar
CMD ["java", "-jar", "beerproject.jar"]