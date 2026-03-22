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
