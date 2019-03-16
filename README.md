# Link shortener

Description  

Most of us are familiar with seeing URLs like bit.ly or t.co on our Twitter or Facebook feeds. These are examples of shortened URLs, which are a short alias or pointer to a longer page link. For example, I can send you the shortened URL http://bit.ly/SaaYw5 that will forward you to a very long Google URL with search results on how to iron a shirt.

Mandatory Requirements

Design and implement an API for short URL creation
Implement forwarding of short URLs to the original ones
There should be some form of persistent storage
The application should be distributed as one or more Docker images
Additional Requirements
Design and implement an API for gathering different statistics


Build: mvn clean install  

Run:  
 
 cd target  
 java -jar shortener-0.0.1-SNAPSHOT.jar

Open your web browser, and navigate to http://localhost:8080