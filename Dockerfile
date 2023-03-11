FROM adoptopenjdk/maven-openjdk11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sathishprojects_config_server.jar
ENTRYPOINT ["java","-jar", "/sathishprojects_config_server"]
