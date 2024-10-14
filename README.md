# Functionality : Todo app backend using SpringBoot : Part 1
Simple spring boot app which represents the backend of Todo App.
This app contains endpoints for creation, update, retrieval and deletion of tasks.


# Technical details and Pre-requisites
- Written using core java and spring boot framework
- Uses H2 in memory database to store the tasks



# How to build locally
- Clone this branch (todo-app-part1) to your laptop with the below command:
  (`git clone --branch todo-app-part1 https://github.com/msoumik78/spring-boot-projects`)
- Ensure that you have latest JDK and Maven3.8.5 available
- Build the project using the command and this should create a zip file in the target folder:
  (`mvn clean package`)
- Use the below command to run the project
  (`java -jar target/spring-boot-todo-app-0.0.1-SNAPSHOT.jar`)


# How to create, update, fetch and delete tasks
- Execute the below command to create a sample task
  (`curl -w "%{http_code}"  -H 'Content-Type: application/json' -d  '{"taskName":"task 1","taskDescription":"task description 1", "taskCompletionPercentage": 10}' -X POST http://localhost:8085/api/1/tasks`)
- Execute the below command to fetch the names of all tasks
  (`curl -H "Accept: application/json" -X GET http://localhost:8085/api/1/tasks`)
- Execute the below command to fetch the details of a specific task
  (`curl -X GET http://localhost:8085/api/1/tasks/1`)




curl -H 'Content-Type: application/json' -w "%{http_code}"  -d  '{"taskName":"task 1a","taskDescription":"task description 1a", "taskCompletionPercentage": 10}' -X PUT http://localhost:8085/api/1/tasks/2
curl -X GET http://localhost:8085/api/1/tasks/1
