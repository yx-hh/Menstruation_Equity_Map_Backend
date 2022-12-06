# Mestruation Equity Map - Backend

## About

### Backgroud

This is a project sponsored by the UCI Women's Center to create a website with an interactive map to help users find the nearest restrooms with menstrual products.

### Features

User location targeting: get the user’s location and find the closet building with available menstrual supplies and present the building location sort by distance.

Show information about menstrual products and building locations on an interactive map: embed an interactive map into our website to help users figure out the location of products they want.

Shortage report: users can use our website to report the shortage of products so that facilities can know they need to refill products.

## Tech Stack

SpringBoot, MySQL, AWS, Google Map API, SpringBoot Email

## Environment

Java 8

Maven 3.8.6

SpringBoot 2.2.2.BUILD-SNAPSHOT

## Build & Run Application

### Run Application in IntelliJ IDEA or Eclipse

Run `MenstruationEquityMapApplication.java` in the /MenstruationEquityMapApplication/src/main/java/edu/uci folder.

### Run Application in Terminal

1. Enter the folde with pom.xml file

  ```shell
cd Menstruation_Equity_Map_Backend/MenstruationEquityMapApplication
  ```

2. Execute command

  ```shell
 mvn package -DskipTests
  ```

3. Find the `MenstruationEquityMapApplication-1.jar` file under target folder and run the jar file with command

  ```shell
nohup java -jar MenstruationEquityMapApplication-1.jar --spring.profiles.active=prod >/dev/null 2>&1 &
  ```

## Backend API

The MenstruationEquityMapApplication/src/main/java/edu/uci/controller folder contains all API methods.

| API Methods       | Description                                                  | Parameters                                                   | Response                                            |
| ----------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | --------------------------------------------------- |
| `searchProduct`   | Search nearest building with menstrual products.             | `user_longitude` : a `double` value represents user's longitude. <br />`user_latitude` : a `double` value represents user's latitude.<br /> `radius` : a `double` value represents the search radius. | A list of buildings information in JSON format.     |
| `reportMissing`   | Report a restroom missing products.                          | `restroom_id` : an `Integer` vlaue represents the restroom id number. | A meesage to show whether the report is successful. |
| `reportRefilling` | Report a restroom has been refilled.                         | `restroom_id` : an `Integer` vlaue represents the restroom id number. | A meesage to show whether the report is successful. |
| `getAllBuildings` | Return all buildings and restrooms information, this is for users to select restroom when they need to generate QR code for a specific restroom. |                                                              | A list of buildings information in JSON format.     |
| `getQrCode`       | Generate QR code image.                                      | `building_name` : a `string` represents the name of the building. <br />`floor_name`: a `string` represents the name of the floor. <br />`restroom_num` : a `string` represents the room number of a restroom.<br />`restroom_id` : a `string` represents the restroom id number. |                                                     |

## Main Packages

All packages and folders will be introduced here briefly.

The MenstruationEquityMapApplication/src/main/java/edu/uci folders contains all backend code.

The `MenstruationEquityMapApplication.java` file is the main program.

### aspectJ

Under package AspectJ with file ControllerInterceptor which used to intercepter all requests to backend application and record the request parameter and backend parameter

### common

Under package common with files GmailHelper, GoogleMapHelper, and QRCodeUtil which define functions related to sending email by Gmail, search address's latitude and longitude by google geo API and generate QR code.

### config

Under package config with files CorsConfig and Swagger which defines functions related allow cors request and API define

### controller

The MenstruationEquityMapApplication/src/main/java/edu/uci/controller folder contains all API methods.

### objects

All object classes are defined in the MenstruationEquityMapApplication/src/main/java/edu/uci/objects folder.

### repository

We use JPA to connect to  and query the database. All JPA repository classes are in the MenstruationEquityMapApplication/src/main/java/edu/uci/repository folder.

We have two tables in the databse, `building` and `restroom` .

- BuildingRepository: SQL queries for the `building` table.
- RestroomRepository: SQL queries for the `restroom` table.

If you need to add a new query, add it here.

### service

The MenstruationEquityMapApplication/src/main/java/edu/uci/service folder contains all services.

There are two interfaces in this folder: `BuildingService` and `RestroomService`. These two interfaces defined what services are provided for API methods.

We have two classes to implement the above two interfaces. There are in the implementation folder: `BuildingServiceImplement` and `RestroomServiceImplement`.

If you need to add a new service, first declare it in an interface, then implement it in a class.

### test

The MenstruationEquityMapApplication/src/test/java/edu/uci/controller folder contains unit tests for API methods. The expected result data are hardcoded inside, if you update data in the database, you might also need to change the expected result data to make the unit tests work correctly.

### resources

`application-dev.yml`: application configuration for development environment.

`application-prod.yml`: applicaiton configuration for product environment.

`logback.xml` : define the rule for generating the log files. The current rule is to generate a new log file daily and when the log file is over 2GB trigger to generate a new log file. Keep the log with maximum of 60 days

### sql_script

The sql_script folder contains SQL scripts for creating the `building` table and the `restroom` table.

### pom.xml

The file of the Maven project is used to manage: source code, configuration files, project dependencies, etc.

## AWS Deployment

1. Install Java 8 and maven 3.8.6

2. Upload the jar file generated as the step in `Run Application in Terminal` into aws sever with `scp`

3. Under folder with the jar file and run the following command


```shell
nohup java -jar MenstruationEquityMapApplication-1.jar --spring.profiles.active=prod >/dev/null 2>&1 &
```

## Others

For other information about front-end, please go to this link: https://github.com/zichengshan/Menstruation_Equity_Map

## Credits

List of contributers:

- [Yuxin Huang](https://www.linkedin.com/in/yx-hh/)

- [Ruiyan Ma](https://www.linkedin.com/in/ruiyan-ma/)

## Sponsored By

UCI Womxn's Center

