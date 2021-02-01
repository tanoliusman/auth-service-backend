# Auth-Service-App


[Spring Boot](http://projects.spring.io/spring-boot/) Auth Service App.

## Requirements

For building and running the application you need:

- [JDK 1.8+](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3+](https://maven.apache.org)
- [MySql](https://www.mysql.com/)
## Running the application locally

####Setup
1. Update application.properties as per need to connect with mysql database

####Run the application
There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.auth.AuthServiceApplication.java` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

###Verifying Api's 
Once the application executed successfully, you can access it like http://localhost:8080/api/<your-api>

###Api Definitions 
You can access api documentation with swagger, if project is deployed locally then you can access below url

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

####Deployment on Kubernetes Minikube

## Prerequisite

- Docker with kubernetes enabled
- Kubernetes command-line tool(kubectl)

## Start application

- Create secrets and start mysql database

```sh
kubectl create -f deployment/secrets.yaml
kubectl create -f deployment/mysql-deployment.yaml
```

- Build application and deploy in kubernetes

```sh
mvn clean package
mvn -DskipTests fabric8:build fabric8:resource fabric8:deploy
```