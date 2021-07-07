# UP42-Feature

## JDK
This application has been compiled and tested with `adoptopen-jdk-11`. 

## Design Spec
The api design spec can be found in the [openapi folder](openapi)

## Tests
The focus has been on writing thick layer of Unit tests, accompanied
by a thin layer of integration tests.
```{bash}
cd <pathToApplication>

./gradlew tests
```
## Run
```{bash}
cd <pathToApplication>

./gradlew run
```
## Swagger
To play around with the endpoints, try running the openapi spec with
the swagger-ui once the app is up and running.

1. ```cd <pathToApplication>```
2. ```docker run -p 84:8080 -e SWAGGER_JSON=/foo/openapi-definition.yaml  -v <pathToApplication>/openapi/:/foo swaggerapi/swagger-ui```
3. Browse to localhost:84
4. Play around on the UI 