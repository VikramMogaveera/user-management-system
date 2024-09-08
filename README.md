Instruction to Run the project with docker
1. Build the docker images
   Navigate to each microservices(user-service and user-journal-service) and build the docker images

      docker build -t user-service:latest .

      docker build -t user-journal-service:latest .

3. Run Docker compose

   From the root directory where your docker-compose.yml file is located, run:

      docker-compose up --build


Instructions to run the project without docker
1. Start kafka server

   bin/zookeeper-server-start.sh config/zookeeper.properties

   bin/kafka-server-start.sh config/server.properties

2. Start mysql server

3. Build common-entities shared module

      mvn clean install

4. Run user-service and user-journal-service

5. Test the api's using Rest client

6.  Access the API documentation at /swagger-ui/index.html
