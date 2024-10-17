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
- Execute the below command to simulate login with correct credentials (this will print all headers including the Authorization headers in response which contains the JWT. Copy that.)
  (`curl -v  -H 'Content-Type: application/json' -d  '{"userName":"msoumik","password":"password123"}' -X POST http://localhost:8085/api/1/user-credentials`)


# How to create and retrieve tasks per user (need to copy the jwt obtained during login for calling the tasks related endpoints )
- Execute the below commands to create some sample tasks for an user (note that the task json contains an user)
  (`curl -H 'Content-Type: application/json' -w "%{http_code}" -H "x-jwt-user:eyJraWQiOiIxMjMiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2MyaWQuY29tIiwidXNlck5hbWUiOiJtc291bWlrIiwiZXhwIjoxNzI5MTM1OTI3LCJ1c2VySWQiOjF9.3J6fIvnIbLpVk68PB_w3Lm_bUHOlE9vDSqlOSiKl1elqY3wayiZoZHL9LqiW6T_xSMKkaxf6TCgfHHaaJwCmoIvUjK3ZZvNZkxNwUcPAGnzqeJXnPOxk8ryGcoaCtzDr_lVOlslO1xm8kbaxffRwLmf6cucfLrKyfznWciyfQTHCyt7nO5HQqSZe7ogfde43Xv24m3ov-PXywgB-sh6dI875Zv1rn3LQvatVD5p2S9yCXSO8IEwBbBCXo12gSPs8IYGsdg6cgfAlkm2akMGBal3nZNd13ZrWlF00hGcR7HmmPoZfecAQ6Yl1Q14nIHZmYaUf3U2A5Lr8M22AlRBp-g" -d  '{"taskName":"task 1","taskDescription":"task description 1", "taskCompletionPercentage": 10, "userId": 1}' -X POST http://localhost:8085/api/1/tasks`)




