# U-Fund: The Mia Foundation (Animal shelter)
# Modify this document to expand any and all sections that are applicable for a better understanding from your users/testers/collaborators (remove this comment and other instructions areas for your FINAL release)

An online U-Fund system built in Java 17=> and Angular 16.2.3
  
## Team

- Garrett Geyer
- Michael DiBiase
- Carla Lopez
- Rachel Adkins
- Cheyenne Zhang

## Prerequisites

- Java 11=>17 (Make sure to have correct JAVA_HOME setup in your environment)
- Maven 3.8
- Angular 16.2.3


## How to run it

1. Clone the repository and go to the root directory.
2. Execute `mvn compile exec:java`
3. Open in your browser `http://localhost:8080/`
4.  _add any other steps required or examples of how to use/run_

## Known bugs and disclaimers
(It may be the case that your implementation is not perfect.)

Document any known bug or nuisance.
If any shortcomings, make clear what these are and where they are located.

Our team had issues with the curl commands to test our functions from the terminal or command prompt. During development we referred to the slide show and materials found in the SWEN website as well as other outside documentation to try and understand the command. For all team members the curl commands did not work as expected as we had issues with proper syntax in the terminal. To overcome this we decided to use the Postman application, this software allows us to by pass the command line. Postman directly interacts with our api and uses GET, POST etc. and the proper url to show what the system outputs and the codes associated with it. This made testing far easier and better streamlined for the team!

## How to test it

The Maven build script provides hooks for run unit tests and generate code coverage
reports in HTML.

To run tests on all tiers together do this:

1. Execute `mvn clean test jacoco:report`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/index.html`

To run tests on a single tier do this:

1. Execute `mvn clean test-compile surefire:test@tier jacoco:report@tier` where `tier` is one of `controller`, `model`, `persistence`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/{controller, model, persistence}/index.html`

To run tests on all the tiers in isolation do this:

1. Execute `mvn exec:exec@tests-and-coverage`
2. To view the Controller tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
3. To view the Model tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
4. To view the Persistence tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`

*(Consider using `mvn clean verify` to attest you have reached the target threshold for coverage)
  
  
## How to generate the Design documentation PDF

1. Access the `PROJECT_DOCS_HOME/` directory
2. Execute `mvn exec:exec@docs`
3. The generated PDF will be in `PROJECT_DOCS_HOME/` directory


## How to setup/run/test program 
1. Tester, first obtain the Acceptance Test plan
2. IP address of target machine running the app
3. Execute ________
4. ...
5. ...

## License

MIT License

See LICENSE for details.

## Release 1.1
In this release we delivered the correct our RESTAPI and fixed the HTPP status response for our methods

## Release 1.2
In this release we delivered our RestAPI, fixed the HTTP status response of our POST METHOD to create a need in which we considered the cases 
when empty fields are given in the need object. Furthermore, we addded a CRUD DELETE method that deletes a need from the cupboard by name
