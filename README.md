# Overview
Web based JAVA Application on cafe management software (CMA).


# Restful API

CMA is Spring Boot rest ful Api

## Features

- Restful routing
- Swagger: API Documentation 
- Base Authentication (Spring security)
- ORM for database

### Technologies

- Java 8
- Spring Framework
- Swagger
- Lombok
- Maven
- MySQL(Hibernate)

## Routes List:

### Manager

* Add new user (ADMIN credentials)

EndPoint: -> [POST]  `/api/v1/manager/user`

body

```
{
 "firstName": "Gor",
 "lastName": "Vardanyan",
 "email": "hresh@gayl.com",
 "password": "hreshnerigayl",
 "passwordConfirm": "hreshnerigayl",
 "role": {"id":"1", "name":"USER"}
 }
 ```

### Waiter

* Get Tables assigned to him (ADMIN credentials)

EndPoint: -> [GET]  `/api/v1/waiter/tables`


##Running the tests
  *no tests
  
  
##### Datasource configurations

Make sure you have data base with such name and MySQL running locally.
And change connection configurations showed bellow.

environment: src/main/resources/application.properties<br />

- spring.datasource.url=***
- spring.datasource.username=***
- spring.datasource.password=***