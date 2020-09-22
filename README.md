# simple-springboot
A simple springboot RestAPI server with java8 (for demo, not production grade)

- RestAPI server
- Springboot v2.3.4
- actuator 
- jwt
- swagger (http://127.0.0.1:8080/swagger-ui.html)
- log4j2
- H2 database
- unit test
- docker


### How to run

1. authentication for a bearer token 

POST  http://127.0.0.1:8080/authentication

{"username":"user","password":"password"}


2. swagger 

http://127.0.0.1:8080/swagger-ui.html




### Thank you

https://medium.com/swlh/spring-boot-security-jwt-hello-world-example-b479e457664c

https://www.javainuse.com/spring/boot-jwt
