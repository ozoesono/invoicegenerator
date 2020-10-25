## Table of contents
* [Problem Overview](#problem-overview)
* [Solution Design](#solution-design)
* [Technologies Used](#technologies-used)
* [Specific Libraries Used](#specific-libraries-used)
* [Important Notes](#important-notes)
* [Running the Application](#running-the-application)
* [Example Output From Running Application](#example-output-from-running-application)
* [Improvements](#improvements)

## Problem Overview
Designing an application that can generate invoices from SMS feeder files.

## Solution Design
![alt text](https://i.ibb.co/qJm7rh7/Screenshot-2020-10-25-at-18-45-01.png)
	
## Technologies Used
Project is created with:
* Java 8 as the development language
* Gradle as the build automation tool and package manager
* HTML for creating the invoices
* CSS for basic styling of the invoices
	
## Specific Libraries Used
Important Java Libraries used for the project:
* Lombok: Used to reduce boiler plate code associated with Java
* Apache Commons CSV: Used for Parsing CSV/Excel files
* Freemarker: Used for generating HTML files
	
## Important Notes
My solution has not provided any extensive error handling, nor have I created custom Exception classes for the application. I felt this was outside the scope of the problem domain.


## Running the Application
1. Install Java 8 if you do not already have it installed.

2. Clone the Repository:
```
$ git clone https://github.com/ozoesono/invoicegenerator.git
```
3. Go into the "invoicegenerator" folder and run:
```
$ ./gradlew jar
```
This will create a an InvoiceGenerator-1.0.jar file. 

4. Execute this command:
```
$ java -jar build/libs/InvoiceGenerator-1.0.jar "Tenant 1" "Tenant 2" "Tenant 3" "Tenant 4"
```
This will create four HTML invoices, one for each Tenant. This can be seen in this folder:
```
invoicegenerator/src/main/out
```

## Example Output From Running Application
![alt text](https://i.ibb.co/brvJPMr/Screenshot-2020-10-25-at-19-45-17.png)

## Improvements
* The location of the output folder can be made to be configurable using for instance, Apache Commons Configuration. This will thus make the application more configurable as we would be able to externalise the output location to a properties file. This would also aid in testing, as it would make it easier to configure the output location for unit tests. 

I could have done this but didn't want to make the application more complicated for its intended purpose.

