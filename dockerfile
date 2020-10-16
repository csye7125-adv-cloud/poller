# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine
# Add a volume pointing to /tmp
VOLUME /tmp
# The application's jar file
ARG JAR_FILE=target/poller-0.0.1-SNAPSHOT.jar
# Add the application's jar to the container
ADD ${JAR_FILE} poller.jar
# Run the jar file
ENTRYPOINT java -jar poller.jar