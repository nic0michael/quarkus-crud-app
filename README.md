# quarkus-crud-app
## A. Project Creation Procedure
[http://rino.kozow.com/java/posts/build-quarkus-rest-crud-applications/](http://rino.kozow.com/java/posts/build-quarkus-rest-crud-applications/)

## B. To run this project
```
cd /opt/projects/quarkus-crud-app

# This prevents test errors: Maven skip tests (run test-quarkus)
./mvnw compile quarkus:dev: -DskipTests

```
## C. To test this project using Swagger
http://localhost:8080/q/swagger-ui/

## D. Creating CM_Template Table CRUD with Postman

### 1. GET Request to Find by cmTemplateName:
URL: http://localhost:8080/message-api/templates/{cmTemplateName}

Replace {cmTemplateName} with the actual value you want to search for.

### 2. POST Request to Create CmTemplate:
URL: http://localhost:8080/message-api/templates

Request Body:
```
{
  "creationDate": "2024-03-25T10:00:00",
  "changedBy": "John Doe",
  "cmTemplateName": "Template1",
  "cmTemplateCategory": "Category1",
  "cmTemplateContent": "Template Content",
  "cmCampaignName": "Campaign1",
  "cmTemplateOwnerName": "Owner1"
}
```

### 3. PUT Request to Update CmTemplate:
URL: http://localhost:8080/message-api/templates

Request Body:
```
{
  "id": 1,
  "creationDate": "2024-03-25T10:00:00",
  "changedBy": "Updated User",
  "cmTemplateName": "Updated Template Name",
  "cmTemplateCategory": "Updated Category",
  "cmTemplateContent": "Updated Template Content",
  "cmCampaignName": "Updated Campaign",
  "cmTemplateOwnerName": "Updated Owner"
}
```

### 4. DELETE Request to Delete CmTemplate by cmTemplateName:
URL: http://localhost:8080/message-api/templates/{cmTemplateName}

Replace {cmTemplateName} with the actual value you want to delete.

## E. Creating CM_SampleData Table CRUD with Postman

### 1. GET Request to Find by cmTemplateName:
URL: http://localhost:8080/message-api/sampledata/{cmTemplateName}

Replace {cmTemplateName} with the actual value you want to search for.

### 2. POST Request to Create CmSampleData:
URL: http://localhost:8080/message-api/sampledata

Request Body:
```
{
  "creationDate": "2024-03-25T10:00:00",
  "changedBy": "John Doe",
  "cmTemplateName": "MICA_BlackFriday2024",
  "cmDataContent": "Sample content"
}
```

### 3. PUT Request to Update CmSampleData:
URL: http://localhost:8080/message-api/sampledata

Request Body:
```
{
  "id": 1,
  "creationDate": "2024-03-25T10:00:00",
  "changedBy": "Updated User",
  "cmTemplateName": "Updated Template Name",
  "cmDataContent": "Updated Sample content"
}
```

### 4. DELETE Request to Delete CmSampleData by cmTemplateName:
URL: http://localhost:8080/message-api/sampledata/{cmTemplateName}

Replace {cmTemplateName} with the actual value you want to delete.

## Project Info
This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-crud-app-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- REST resources for Hibernate ORM with Panache ([guide](https://quarkus.io/guides/rest-data-panache)): Generate Jakarta REST resources for your Hibernate Panache entities and repositories
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- JDBC Driver - MySQL ([guide](https://quarkus.io/guides/datasource)): Connect to the MySQL database via JDBC
- JDBC Driver - Oracle ([guide](https://quarkus.io/guides/datasource)): Connect to the Oracle database via JDBC

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
