FROM eclipse-temurin:11-jre-focal
ADD target/*.jar sathishprojects_config_server.jar
RUN sh -c 'touch /sathishprojects_config_server.jar'
EXPOSE 8888
ENTRYPOINT [ "sh", "-c", "java -jar /sathishprojects_config_server.jar --pass=sa --username=sathish" ]
