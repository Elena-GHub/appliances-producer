## Appliances Producer Program

The "appliances-producer" application is a Spring Boot-based service that interacts with RabbitMQ to send messages related to household
appliances. Follow the steps below to launch the application and make a sample request.

## Prerequisites

Make sure you have Docker installed on your machine before getting started.

## 1. RabbitMQ Configuration

Run the following command in your terminal to create a Docker container with RabbitMQ:

```bash
docker run -d --name rabbit -p 5672:5672 -p 5673:5673 -p 15672:15672 -e RABBITMQ_DEFAULT_USER=someuser -e RABBITMQ_DEFAULT_PASS=somepassword rabbitmq:3.8.5-management-alpine
```

This command will create a container with the name "rabbit" and set the user credentials as "someuser" and the password as "somepassword".
Make sure to execute the command without line breaks.

## 2. Launch the Spring Boot Application

Follow these steps to run the "appliances-producer" application:
Clone this repository or download the source code.
Navigate to the project directory and run the following command:

```bash
./gradlew bootRun # if you are using Gradle
./mvnw spring-boot:run # if you are using Maven
```

This will start the Spring Boot application on the default port 8080.

## 3. Make a Request

Use cURL to send a POST request to the service. Execute the following command in your terminal:

```bash
curl --header "Content-Type: application/json" --request POST http://localhost:8080/appliances/washing-machine
```

This will send a sample request to the "appliances-producer" service. You will receive a message in the "appliances-consumer" application.
You can see how to set up this consumer application in the repository appliances-consumer.
