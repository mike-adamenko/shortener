# Link shortener

#### Task
_Description_  

Most of us are familiar with seeing URLs like bit.ly or t.co on our Twitter or Facebook feeds. These are examples of shortened URLs, which are a short alias or pointer to a longer page link. For example, I can send you the shortened URL http://bit.ly/SaaYw5 that will forward you to a very long Google URL with search results on how to iron a shirt.

_Mandatory Requirements_

Design and implement an API for short URL creation
Implement forwarding of short URLs to the original ones
There should be some form of persistent storage
The application should be distributed as one or more Docker images  

_Additional Requirements_

Design and implement an API for gathering different statistics

#### Basic architecture

Back-end is implemented as a spring boot application.  
Front-end is a react app.  
Persistent storage is implemented as in-memory H2 database. 

#### REST API Documentation

Availiable at `http://localhost:8080/swagger-ui.html`

### Run as a spring boot application

- Build: 

    `mvn clean install`  

- Run:    

    ` java -jar target/shortener-0.0.1-SNAPSHOT.jar`

### Run as a docker container

_Before proceeding with the following steps (which use Docker’s CLI tools), 
make sure Docker is properly running by typing `docker ps`. If you get an error 
message, something may not be set up correctly. Using a Mac? 
Add `$(boot2docker shellinit 2> /dev/null)` to the bottom of your `.bash_profile` 
(or similar env-setting configuration file) and refresh your shell to ensure proper 
environment variables are configured._  

- You can PULL an image :  

    `docker pull sunvitebsk/shortener`
    
    OR  BUILD an image:  
 
  `mvn clean install dockerfile:build`  
 
 - Run:  
 
     `docker run -p 8080:8080 -t sunvitebsk/shortener:latest`
 

Open your web browser, and navigate to `http://localhost:8080`

Database console availiable at `http://localhost:8080/h2-console`  
Make sure that you use `jdbc:h2:mem:testdb` as JDBC URL.