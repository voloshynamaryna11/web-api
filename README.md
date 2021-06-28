**BTC Web-Api** 
=====================

***1. Project goal***
-----------------------------------
The goal of the project is to create a restful web-api designed to view the bitcoin rate in uan, with basic authentication and authorization.
 
***2. Project structure:***
-----------------------------------
* all sources are in the "genesis.school.webapi" package
* all controllers are responsible for handling requests are in the package "controller"
* the "file_repository" package contains the class responsible for the implementation of interaction with the file that contains all users` info.
* the custom exception needed to identify the application failure are in the "exception" package
* the "model" package is responsible for recreating and describing entities whose data is stored in the file and is used for transferring data between the layers of app 
* the "util" package contains class responsible for hashing users' passwords "HashUtil"
* the "mapper" package is responsible for useful mapping objects that contains user info into another datatypes
* all custom annotations needed to validate input information are in the "annotation" package
* the "resources" folder contains 'users' file for storing users` info

***3. Implementation details:***
-----------------------------------
The project implements:
* custom authorization using HttpSession
* custom authentication,
* hashing passwords using the SHA-512 algorithm

The project technologies:
* Spring Boot module 2.5.2
* Json-simple library for parsing JSON
* Validation-api library for validation input data
* Commons-validator library for ready-made solution for email validation
* Commons-codec library to get implementation of Base64 encoder for password hashing 
* Opencsv library for work with csv file

***4. Functionality:***
-----------------------------------
* registration ("/user/create")
* login ("/user/login")
* get btc rate in uah ("/btcRate")

***5. Launch and using guide:***
-----------------------------------
* Clone this project 
* Run main method of WebApiApplication class
* Register first user using Postman or another http-client:
  * use "/user/create" uri and post method
  * enter user information into body of your request in json format with next params:
  
  {
    "firstName" : "",
    "lastName" : "",
    "email" : "",
    "password" : "", 
    "repeatPassword" : ""
  }
  
* Login:
  * use "/user/login" uri and post method
  * enter user information into body of your request in json format with next params:
  
  {
    "email" : "",
    "password" : ""
  }
  
* Check btc rate:
  * use "/btcRate" uri and get method  

***6. Author:***
-----------------------------------
[Maryna Voloshyna](https://github.com/voloshynamaryna11)