## Table of contents
* [Problem Overview](#problem-overview)
* [Solution Design](#solution-design)
* [Technologies Used](#technologies-used)
* [Specific Libraries Used](#specific-libraries-used)
* [Important Notes](#important-notes)
* [Running the Application](#running-the-application)

## Problem Overview
Designing an application that can generate invoices from SMS feeder files.

## Solution Design

	
## Technologies Used
Project is created with:
* Java 8 as the development language
* Gradle as the build automation tool and package manager
	
## Specific Libraries Used
Important Java Libraries used for the project:
* Lombok: Used to reduce boiler plate code associated with Java
* Apache Commons CSV: Used for Parsing CSV/Excel files
* Freemarker: Used for generating HTML files
	
## Important Notes
My solution has not provided any extensive error handling, nor have I created custom Exception classes for the application. I felt this was outside the scope of the problem domain.


## Running the Application
This project is simple Lorem ipsum dolor generator.
	
First Clone the Repository:
```
$ git clone https://github.com/ozoesono/invoicegenerator.git
```
Then go into the invoicegenerator folder and run:
```
$ ./gradlew jar
```
This will create a an InvoiceGenerator-1.0.jar file. Execute this file:
```
$ java -jar invoicegenerator/build/libs/InvoiceGenerator-1.0.jar "Tenant 1" "Tenant 2" "Tenant 3" "Tenant 4"
```
This will create four invoices, one for each Tenant. This can be seen in this folder:
```
invoicegenerator/src/main/out
```
T
