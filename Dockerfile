# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the project's JAR file into the container at /app
COPY target/sathishproject-config-server-0.0.1-SNAPSHOT.jar /app/app.jar

# Set environment variables
ENV username=sathish
ENV pass=pass

# Make port 8080 available to the world outside this container
EXPOSE 8888

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
