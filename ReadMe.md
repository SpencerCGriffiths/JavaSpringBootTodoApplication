Java Spring Boot Application

This application utilised https://start.spring.io/ in order to create the dependencies
CSS and styling is handled with https://getbootstrap.com/
Annotations to support routing etc. are supported by Lombok https://projectlombok.org/
The html is enriched with thyme leaf https://www.thymeleaf.org/
The database is MySql.

The original project was designed to be supported by MySql Workbench : https://www.mysql.com/products/workbench/

Alternatively with docker I used CloudBeaver as a containerised web application: https://dbeaver.com/

Steps for Cloud Beaver: (initially ensure docker is installed)

## Step 1: Spin up the MySQL Database
Run this command to start the MySQL engine. Replace the placeholders with your preferred credentials.

```bash
docker run -d --name mysql-db \
  -e MYSQL_ROOT_PASSWORD=admin \
  -e MYSQL_DATABASE=app_db \
  -e MYSQL_USER=dev_user \
  -e MYSQL_PASSWORD=dev_pass \
  -p 3306:3306 \
  mysql:latest
  ````
## Step 2: Create the cloudBeaver interface

```bash
docker run -d --name cloudbeaver \
  -p 8080:8978 \
  -v ~/cloudbeaver-data:/opt/cloudbeaver/workspace \
  dbeaver/cloudbeaver:latest
```
## Step 3: Setup cloud beaver
Open your browser and go to: http://localhost:8080
Follow the initial setup wizard (you can keep the defaults).

## Step 4: To connect to your MySQL DB
Click New Connection -> MySQL.
Host: Use host.docker.internal (if your DB is running on your local machine) or the name of your DB container if they share a network.
Port: 3306
Credentials = these are the two fields in the initial command:
-e MYSQL_USER=dev_user \
-e MYSQL_PASSWORD=dev_pass \

## Step 5: Run Java Application

## Step 6: ...


Areas for improvement on this project: 

1) Testing -> 
Implement test specs including mocking where appropriate

2) Exception Handling -> 
Explore Global Exception Handler (@RestControllerAdvice)
Define the validation rules for each of the exception classes i.e. IllegalArgumentException
Use this in conjunction with @ExceptionHandler

3) Create Custom repository methods to search for task by ID

4) Figure out @Controller vs @RestController ->
Try to ensure that the whole page does not refresh but it only calls one small part of the list to refresh
Similar to Single Page Applications. 

5) Integrate Apache Camel into this project ->
Unsure how this would work. Possibly use Apache to read the db and return a Json output of all tasks

6) Utilise postman as appose to the UI 

7) Work on seperating a front end from a back end and how they communicate
If not possible in this project implement in an Order/Inventory service.
Order Service takes the order -> Inventory Service, check inventory and returns to the order

8) DataJpaTests / Integration testing

Additional Learning notes: 
When looking at mutating objects look at alternatives to .copy() that I should learn
This can be done in multiple ways:
1) Lombok Builder -> adding @Builder to the Task class
2) Manual Copy constructor -> Amend the Task constructor
3) BeanUtils -> Use BeanUtils.copyProperties
These should all be further explored.