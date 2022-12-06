# Mestruation Equity Map - Backend

## Start Application

需要补充

Run `MenstruationEquityMapApplication.java` in the /MenstruationEquityMapApplication/src/main/java/edu/uci folder.

## Change Configuration

需要修改和补充

All configurations are defined in the `application-dev.yml` and the `application-prod.yml` file in the MenstruationEquityMapApplication/src/main/resources folder.

## Aspectj ??

## Backend API

All API methods are in the MenstruationEquityMapApplication/src/main/java/edu/uci/controller folder.

`StudentController.java` : API for student users.

- `searchProduct` : search nearest building with menstrual products.
- `reportMissing` : report a restroom missing products.

`StuffController.java` : API for stuff users who manage this project.

- `reportRefilling` : report a restroom has been refilled.
- `getAllBuildings` : return all buildings and restrooms information, this is for users to select restroom when they need to generate QR code for a specific restroom.
- `getQrCode` : generate QR code image.

Swagger UI link: 需要补充

## Objects

All object classes are defined in the MenstruationEquityMapApplication/src/main/java/edu/uci/objects folder.

- Building
- Floor
- Restroom

## Repository

We use JPA to connect to  and query the database. All JPA repository classes are in the MenstruationEquityMapApplication/src/main/java/edu/uci/repository folder.

We have two tables in the databse, `building` and `restroom` .

- BuildingRepository: SQL query for the `building` table.
- RestroomRepository: SQL query for the `restroom` table.

If you need to add a new query, add it here.

## Service

All service classes are in the MenstruationEquityMapApplication/src/main/java/edu/uci/service folder.

We have two interfaces to define what service we provide for API methods.

- `BuildingService`
-  `RestroomService`

Methods in these two interface are used by API methods in the src/main/java/edu/uci/controller folder.

We have two classes to implement the above two interfaces. There are in the implementation folder.

- `BuildingServiceImplement`
- `RestroomServiceImplement`

If you need to add a new service, first declare it in an interface, then implement it in a class.

## Test

Unit tests for API methods are in the MenstruationEquityMapApplication/src/test/java/edu/uci/controller folder. The expected result data are hardcoded inside, if you update data in the database, you might also need to change the expected result data to make the unit tests work correctly.

## SQL Script

The sql_script folder contains SQL scripts for creating the `building` table and the `restroom` table. 

