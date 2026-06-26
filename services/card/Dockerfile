#Start with a base image containing Java runtime
FROM eclipse-temurin:21-jdk

# Add the application's jar to the image
COPY target/card-0.0.1-SNAPSHOT.jar card-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "card-0.0.1-SNAPSHOT.jar"]