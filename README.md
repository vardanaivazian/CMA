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

### Manager (ADMIN credentials)

* Add new user

EndPoint: -> [POST]  `/api/v1/manager/user`

body

```
{
 "firstName": "Gor",
 "lastName": "Vardanyan",
 "email": "hresh@gayl.com",
 "password": "hreshnerigayl",
 "passwordConfirm": "hreshnerigayl",
 "role": {"id":"1", "name":"ROLE_ADMIN"}
 }
 ```

* Add new empty table (empty means not assigned no one)

EndPoint: -> [POST] `/api/v1/manager/table`
```
{}
```

* Assign tables to waiter

EndPoint: -> [POST] `/api/v1/manager/assign-tables`
```
{
"tableIds": [1,2],
"userId": 12
}
```

* Create product

EndPoint: -> [POST] `/api/v1/manager/product`
```
{
	"name": "Tsiran"
}
```



### Waiter (USER credentials)

* Get Tables assigned to him

EndPoint: -> [GET]  `/api/v1/waiter/tables`



* Create order

EndPoint: -> [POST]  `/api/v1/waiter/order`

```
{
	"tableId": 3,
	"status": "OPEN"
}
```



* Update order

EndPoint: -> [PUT]  `/api/v1/waiter/order`

```
{
    "id": 2,
	"tableId": 2,
	"status": "CLOSED"
}
```


* Create product in order

EndPoint: -> [POST]  `api/v1/waiter/product-in-order`

```
{
	"orderId": 1,
	"productId": 1,
	"amount": 2
}
```

* Update product in order

EndPoint: -> [PUT]  `api/v1/waiter/product-in-order`

```
{
	"id": 1,
	"orderId": 1,
	"productId": 3,
	"amount": 18
}
```


##Running the tests
  *no tests
  
  
##### Datasource configurations

Make sure you have data base with such name and MySQL running locally.
And change connection configurations showed bellow.

environment: src/main/resources/application.properties<br />

- spring.datasource.url=***
- spring.datasource.username=***
- spring.datasource.password=***

------------------------------------------------------------------------
`Note:   after running SQL script from resources you must have 2 users with role ADMIN and USER`

admin login:        admin@mail.com
admin password:     password

user login:        user@mail.com
user password:     password