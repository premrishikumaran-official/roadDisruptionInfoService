# Road Disruption information using TFL unified API service

Java Spring boot microservice that provide Road Disruption information using TFL unified API service


## Requirements
Task is to design and implement a system that provides the following user experiences:

    Example: Valid Road

    To get the status of the A2 (a major road in East London) you would make an HTTP GET request to:

    https://api.tfl.gov.uk/Road/A2?app_id=your_app_id&app_key=your_developer_key

    (where ‘your_app_id’ and ‘your_developer_key’ are the values sent to you by TfL when you register as a developer).

    This call will return a ‘200’ response code along with the following JSON:

 ```json
    {
    "$type": "Tfl.Api.Presentation.Entities.RoadCorridor, Tfl.Api.Presentation.Entities",
    "id": "a2",
    "displayName": "A2",
    "statusSeverity": "Good",
    "statusSeverityDescription": "No Exceptional Delays",
    "bounds": "[[-0.0857,51.44091],[0.17118,51.49438]]",
    "envelope": "[[-0.0857,51.44091],[-0.0857,51.49438],[0.17118,51.49438],[0.17118,51.44091],[-0.0857,51.44091]]",
    "url": "/Road/a2"
    }
   ```
    

    Example: Non-Existent Road

    If you made an HTTP, GET request call with an invalid road ID to:

    https://api.tfl.gov.uk/Road/A233?app_id=your_app_id&app_key=your_developer_key

    Your call will return a ‘404’ response code the following response: -
 ```json
 
    {
    "$type": "Tfl.Api.Presentation.Entities.ApiError, Tfl.Api.Presentation.Entities",
    "timestampUtc": "2017-11-21T14:37:39.7206118Z",
    "exceptionType": "EntityNotFoundException",
    "httpStatusCode": 404,
    "httpStatus": "NotFound",
    "relativeUri": "/Road/A233",
    "message": "The following road id is not recognised: A233"
    }
    
```


## Tech stack

    Java 17

    Spring boot 3.1.4

    Feign Client

 Testing Framework used

    Mockit

    Junit 5
 
    Rest Assured 
   
    Wiremock


## How to Start

Option 1 (Intellij IDE) (Require Java 17)

    Clone the repo to your local ide from git hub provided

    run below maven commands to build and bootstrap the api
  
        mvn clean compile test verify package
        
        Running prod profile
        mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=prod infoServiceProvider.appKey=<replace-dev-app-key>"

    For Testing
       via command line

       1, curl http://localhost:8080/v1/road/a2/severity-status 

Option 2 (Docker) 

    Preferred way to start the application is **DOCKER** if you have docker desktop installed

    make sure your docker desktop is running

    Update application-prod.yml infoServiceProvider.appKey with 'your <tfl dev key>'

    In the root folder of the project, run  `docker-compose up --build` to pull the 3rd party required images and start all the services

    For Starting api service

    `docker-compose up `

    #### For 'cleanup' -careful it will remove all unsed images

    `docker-compose down` in a different terminal

    `docker rmi $(docker images -a -q) `

    `docker image prune -a`



## Postman script
import file [postman](postman/Road-disruption-info-service.postman-collection.json) to postman app
    
    3 Test scanerios provided 

Valid Road

curl --location --request GET 'http://localhost:8080/v1/road/A2/severity-status'
```json
  {
    "displayName": "A2",
    "statusSeverity": "Good",
    "statusSeverityDescription": "No Exceptional Delays"
  }

```

Invalid Road 

curl --location --request GET 'http://localhost:8080/v1/road/A2XY/severity-status'
```json
  {
    "status": 404,
    "message": "The following road id is not recognised: A2XY"
  }
```


## Integration Test
    RoadStatusControllerIT.java -Integration test can be run via ide .
    
    Select and "run  RoadStatusControllerIT" on ide

## Junit Test
    RoadServiceImplTest.java Junit Test can be run via ide .
  
    Select and "run  RoadServiceImplTest" on ide
