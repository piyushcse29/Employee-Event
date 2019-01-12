# Employee-Event
Employee- &amp; Event-Service Coding Challenge


# How to run the application

1) This repository containes two microservices 1) Employee service 2) Event service. Clone or download the source code and build the project either using eclipse IDE or maven build tool.


2) Download and run the docker images for MySQL and Kafka using below commands:

docker run -it -p 3306:3306 --name mysqldb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=employeedb -d mysql:latest

docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=`docker-machine ip \`docker-machine active\`` --env ADVERTISED_PORT=9092 spotify/kafka

Note:

* Swagger documention can be access using below urls:
http://localhost:8000/swagger-ui.html (For Employee service)
http://localhost:8001/swagger-ui.html (For Event service)



* Mysql default username and password are configured as root/root
* Employee services are basic authenication enable and default username and password is user/user@1
* Kafka topic is autocreated by the application with name (employee-message)
