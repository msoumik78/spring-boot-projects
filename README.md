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
- Execute the below commands to create some sample tasks for an user (replace the jwt below with the freshly created jwt that you had obtained while validated credentials)
 (`curl -H 'Content-Type: application/json' -w "%{http_code}" -H "x-jwt-user:eyJraWQiOiIxMjMiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJodHRwczovL2MyaWQuY29tIiwidXNlck5hbWUiOiJtc291bWlrIiwiZXhwIjoxNzI5MzI3MTIwLCJ1c2VySWQiOjF9.iRfD7svxRSggCw_HhiJDpJvU-LrTcPvlO6jtWA8cc0tXJa5L4dKg2eRr701h1yfyfzCzIiruYfLNRCjhGdN3AT5qAZW-P9faVZvf_YoQGprShAqVdmwVk9_OfqeEKW4VYvJNpcn818sNdbMPyu-UBJVnHU3WJbZkKmDY0d-rfslWdU-vz2pQqaJVInx8rXm0lYUkr9UryDYJrPacbIqN7kwIhAXEXtZxMYav6AZgwSsdFoHgXm3Jj3EprheiR2D-juJKjw6S66EHUZy1D13-OTIZxD6BmSAWoHCCAbZkd-Wiz9PI2AI5gYW4_wdb0U29ZKN3JYlb-fjUIb8fF5s2OQ"  -d  '{"taskName":"task 1","taskDescription":"task description 1", "taskCompletionPercentage": 10, "userId": 1}' -X POST http://localhost:8085/api/1/tasks`)




