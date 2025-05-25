# sathishproject-config-server

Config server is for externalizing configuration properties for microservices. It allows you to manage and serve configuration properties from a central location, making it easier to maintain and update configurations across multiple services.
There are lot of projects which are using config server. Some of them are listed below.
* mytracker
* mytracker-graphql
* tech-reciper-boot3
* runner

My intention is to use this for all the projects that I'am currently working on. I will be using this for my future projects as well. I will be using this for my personal projects as well.
The k8s deployment is done in another config project in mytracker. The reason I did not want to tie to this is to keep this a pure image just
focussed on the config server. 

### Reference Documentation

##Docker documentation

* docker images
* docker build -t travelhelper0h/sathishconfigserver .
* docker push travelhelper0h/sathishconfigserver
* docker run --rm -e username='someusername' -e pass='somepass' -p 8888:8888 travelhelper0h/sathishproject-config-server

### Guides

The docker file image needs these arguments
* JAR_FILE 
* APP_PORT
* GIT_URI
* encrypt_key
* username
* pass

Without these arguments the image will not work.