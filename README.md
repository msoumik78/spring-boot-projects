# Functionality : Todo app backend using SpringBoot : Part 2
Simple spring boot app which represents the backend of Todo App.
Has 2 services as follows:
- User service (for creation & user verificationj)
- Task service (for creation, update & retrieval of tasks per user)


# Technical details and Pre-requisites
- Written using core java and spring boot framework
- Uses Bcrypt library for hashing the password and stores the hashed password in DB
- Uses H2 in memory database to store the users & tasks



# How to build locally
- Clone this branch (todo-app-part2) to your laptop with the below command:
  (`git clone --branch todo-app-part2 https://github.com/msoumik78/spring-boot-projects`)
- Ensure that you have latest JDK and Maven3.8.5 available
- Build the project using the command and this should create a zip file in the target folder:
  (`mvn clean package`)
- Use the below command to run the project
  (`java -jar target/spring-boot-todo-app-0.0.1-SNAPSHOT.jar`)


# How to create user
- Execute the below command to create a sample task
  (`curl -w "%{http_code}"  -H 'Content-Type: application/json' -d  '{"userName":"msoumik","password":"password123", "fullName": "Soumik Mukherjee"}' -X POST http://localhost:8085/api/1/users`)
- Execute the below command to simulate login with correct credentials
  (`curl -w "%{http_code}"  -H 'Content-Type: application/json' -d  '{"userName":"msoumik","password":"password123"}' -X POST http://localhost:8085/api/1/user-credentials`)


# How to create and retrieve tasks per user
- Execute the below commands to create some sample tasks for an user (note that the task json contains an user)
  (`curl -H 'Content-Type: application/json' -w "%{http_code}"  -d  '{"taskName":"task 1","taskDescription":"task description 1", "taskCompletionPercentage": 10, "userId": 1}' -X POST http://localhost:8085/api/1/tasks`)
  (`curl -H 'Content-Type: application/json' -w "%{http_code}"  -d  '{"taskName":"task 2","taskDescription":"task description 2", "taskCompletionPercentage": 10, "userId": 1}' -X POST http://localhost:8085/api/1/tasks`)
  (`curl -H 'Content-Type: application/json' -w "%{http_code}"  -d  '{"taskName":"task 3","taskDescription":"task description 3", "taskCompletionPercentage": 10, "userId": 1}' -X POST http://localhost:8085/api/1/tasks`)
  (`curl -H 'Content-Type: application/json' -w "%{http_code}"  -d  '{"taskName":"task 4","taskDescription":"task description 4", "taskCompletionPercentage": 10, "userId": 1}' -X POST http://localhost:8085/api/1/tasks`)
- Execute the below command to retrieve all tasks for an user:
  (`curl -X GET http://localhost:8085/api/1/users/1/tasks`)
- Execute the below command to retrieve details of a specific task:
  (`curl -X GET http://localhost:8085/api/1/tasks`)




