# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Define build arguments
ARG JAR_FILE
ARG APP_PORT
ARG GIT_URI
ARG encrypt_key
ARG username
ARG pass

# Set environment variables
ENV JAR_FILE=${JAR_FILE}
ENV APP_PORT=${APP_PORT}
ENV GIT_URI=${GIT_URI}
ENV encrypt_key=${encrypt_key}
ENV username=${username}
ENV pass=${pass}

# Set the working directory in the container
WORKDIR /app

# Copy the project's JAR file into the container at /app
COPY target/${JAR_FILE} /app/app.jar

# Make port available to the world outside this container
EXPOSE ${APP_PORT}

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

