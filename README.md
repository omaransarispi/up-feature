# UP42-Feature

## Overview 
A springboot powered restful API that exposes representation of images as features along with the meta data. 

### JDK version
This application has been compiled and tested with `adoptopen-jdk-11`. 
> Depending on your OS you can switch your JVM version by using tools like jenv (mac os) or sdkman (ubuntu)
## Design Spec
The api design spec can be found in the [openapi folder](openapi)

## Tests
The focus has been on writing thick layer of Unit tests, accompanied
by a thin layer of integration tests.
```{bash}
cd <pathToApplication> && ./gradlew tests
```
## Running the App
### Local Deployment
```{bash}
cd <pathToApplication> && ./gradlew bootRun
```

### Using openapi spec to interact with the endpoints
To play around with the endpoints, try running the openapi spec with
the swagger-ui once the app is up and running.

1. ```cd <pathToApplication>```
2. ``` sudo docker run -p 84:8080 -e SWAGGER_JSON=/foo/openapi-definition.yaml  -v <pathToApp>/openapi/:/foo swaggerapi/swagger-ui```
3. Browse to localhost:84
4. Play around on the UI 

#Improvements
1. Quicklook could redirect to a CDN/Server dedicated to serving images
2. Contract tests with tools such as pact runner integrated into the pipeline would provide an additional layer of security
3. CORS origins header is very liberal at the moment. Should be made more specific before
deploying to production
4. Linter such as spotless to enforce codestyle and optimize imports
5. Structured API error messages
6. Not relying on nulls for error handling. Would prefer to introduce monads (such as Some/Option)
