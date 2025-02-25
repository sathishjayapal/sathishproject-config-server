# Use an official OpenJDK runtime as a parent image
FROM bellsoft/liberica-openjre-debian:21 AS builder
# Define build arguments
ARG JAR_FILE=target/*.jar
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

CMD echo ${JAR_FILE} && echo "${GIT_URI}" && echo "${encrypt_key}" && echo "${username}" && echo "${pass}"
# Set the working directory in the container
WORKDIR /builder

# Copy the project's JAR file into the container at /app
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=tools -jar application.jar extract --layers --destination extracted

# This is the runtime container
FROM bellsoft/liberica-openjre-debian:21-cds
WORKDIR /application
COPY --from=builder /builder/extracted/dependencies/ ./
COPY --from=builder /builder/extracted/spring-boot-loader/ ./
COPY --from=builder /builder/extracted/snapshot-dependencies/ ./
COPY --from=builder /builder/extracted/application/ ./
COPY --from=builder /builder/application.jar application.jar

# Make port available to the world outside this container
EXPOSE ${APP_PORT}

# Run the JAR file
ENTRYPOINT ["java", "-XX:SharedArchiveFile=application.jsa", "-jar", "application.jar"]

