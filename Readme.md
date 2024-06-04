# Authentication Assignment 

### Tech stack used:
Java 21 <br />
postgreSQL Database, Junit, Maven, Hibernate , Spring Data JPA <br/>
Java Spring Boot <br />
Liquibase <br/>


### Assignment Application

Users And Group Relation project. <br/>
In User Save API we can also save new groups as well as existing group by passing ID  <br/>
Added Curl requests below for the APIs call <br/>
Have to pass postgreSQL username,password for running the application OR Can enable H2 database by uncommenting the code in
application-dev.properties file


## Requests

### Test data Information
I have used liquibase to create entity and test data
users.csv file contains users data <br />
groups.csv file contains all groups <br />
user_groups.csv file contains user groups data <br />
.


### User Save Request
``
curl --location 'http://localhost:8080/api/user' \
--form 'userName="username3"' \
--form 'email="asd3@gmail.com"' \
--form 'password="pw"' \
--form 'groupsDtoList[0].name="Test user requester2"' \
--form 'groupsDtoList[0].description="requester title2"'
``

### Fetch All Users
``
curl --location 'http://localhost:8080/api/user'
``

### Fetch User By ID
``
curl --location 'http://localhost:8080/api/user/1'
``

### Group Save Request
``
curl --location 'http://localhost:8080/api/group' \
--form 'name="Group 1"' \
--form 'description="1245"'
``

### Fetch all Groups
``curl --location 'http://localhost:8080/api/group'
``

#### Startup with Profile settings
DEV  <br />
Default profile : DEV
##### Build jar
``
mvn clean install
``
##### Default profile (DEV)
``
mvn spring-boot:run
``

or

``
java -jar -Dspring.profiles.active=prod target/assignment-0.0.1-SNAPSHOT.jar
``

### Improvements
However we can add multiple request like user Group assignment API
and also add test cases 


