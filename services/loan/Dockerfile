#Start with a base image containing Java runtime
FROM eclipse-temurin:21-jdk

# Add the application's jar to the image
COPY target/loan-0.0.1-SNAPSHOT.jar loan-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "loan-0.0.1-SNAPSHOT.jar"]