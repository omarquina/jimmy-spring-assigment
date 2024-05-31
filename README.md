## Kali Assign
## Developer Orlando Marquina 
## marquina_o@yahoo.es


# Documentaion

The application is implemented as an API to manage car reservations. 

When a user wants to reserve a car indicating a date and duration, the application search an available car based in certain rules.

# Features implemented

## Car

### Create

* The Id generated start the String Id with a C followed by 4 andom numbers
* Not accept blank make, or model

### Delete
* specify the car id at the URL

### Update
* specify the car id at the URL.
* in the body, set the *make* and/or *model* values.
* Could specify only one param, and only that vlaue will be setted.

### List
* List all cars


## Reservation 

### Reserve a Car

* In Body: specify *startDate* and *duration* params, 
* The application returns a reservation with a car picked by the app, if there are cars available based in business rules based in the startTime.
* Param *startDate* is the format "MM-dd-yyyy HH:mm", date and time in 24 hours, this permit to specify next day hour easy.
* *period* must be 1 of 2.

# App Execution

* I defined a Dockerfile iwth stages
* I use the same Dockerfile for local development 
* And for deployment
* docker-compose define two services, to easy start both containers when needed


## For Local Development

* This install all dependencies in current directory of project, mounted as volume
* Ports of applications are configured in the file *devops/.env*


```
cd devops
docker-compose up rides-dev
``` 

## For local deploy all dependencies in container 

```
cd devops
docker-compose up rides-dev
``` 

# Swagger UI

```
http://localhost:8081/swagger-ui/index.html
```

# Architecture

* The application is layered
** *dir application*: stores Use cases implementations
** *dir domain*: stores entities, and business rules
** *dir infraestructure*: stores controllers and repositories implmentations


## Test

### Domain dir
* Implements all test of application and domain, isolated from infraestructure

## Infrastructure dir

* (Maybe could not be implemmented by time) Test controller
