# To Do List
This repo contains a springboot system which allows the end user to create an API and test it using the front end system provided. The user can create, update, read and delete records from the system.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To run the file you will need to install the latest version of Java & Maven at the link bellow.

```
https://www.java.com/en/download/
http://maven.apache.org/download.cgi
```

### Installing

Navigate to where you have downloaded the jar file then right click and open bash

![open bash screenshot](https://github.com/To-Do-List/JavaProject/blob/main/images/tut1.png)

Then type **java -jar fat.jar** replacing the fat with whatever you might have renamed it to

![run command screenshot](https://github.com/To-Do-List/JavaProject/blob/main/images/tut2.png)

Once running, 'Springboot' should appear in ascII text at the start and the system will be fully booted once you see the line ''To-Do List API Initialised''

![Entity usage Screenshot](https://github.com/To-Do-List/JavaProject/blob/main/images/tut3.png)

## Running the tests

The following section will explain the tests, what they are and how you can run them.

### Unit Tests 

Units tests are to check the functionality of code in a vaccum to see if they work on their own. These tests are set up in `...\src\test\com\qa\demo\rest` and can be run using JUnit with Maven.

### Integration Tests 

Integration tests are to check the functionality of code when used with other aspects. These tests are setup in `...\src\test\com\qa\demo\rest` directory. These use JUnit and Mockito with Maven to run.

### Website Tests

These tests are conducted using selenium and an appropriate driver for the web browser you are testing on. In my case I am using Google Chrome. The testing will check all intractable aspects of the webpage to verify it is working correctly. This can be found at : `...\src\test\com\qa\demo\selenium\pages\demosite`

## Deployment

Deployment to live system requires constant update for the fat.jar system with every update of the system. Otherwise deployment is the same as installation.

## Future Development

For future development you will need to install maven, java and IDE for Java(Spring Tool Suite or Intellij recommended). For website development I recommend an IDE for Websites(Visual Studio or Webstorm) 

```
https://www.java.com/en/download/
http://maven.apache.org/download.cgi
https://www.eclipse.org/
```

## Built With

* [Java](https://www.java.com/en/download/) - Base Language
* [Maven](https://maven.apache.org/) - Dependency Management
* [SpringBoot](https://spring.io/projects/spring-boot) - API System
* [SpringFox](https://springfox.github.io/springfox/) - API Endpoints
* [LomBok](https://projectlombok.org/) - Object Builder
* [Mockito](https://site.mockito.org/) - Mock Input Testing
* [JUnit](https://junit.org/junit5/) - Generic Code Testing
* [Selenium](https://www.selenium.dev/) - Website Functionality Testing
* [SonarQube](https://www.sonarqube.org/downloads/) - Static Code Analysis
* [HTML & CSS]() - Website Markup Language
* [Javascript]() - Website Functionality Language

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Project Management Board

The project was done in SCRUM methodology using jira to keep track of the tasks and bugs as it was coded.

![Jira Screenshot](https://github.com/FayazPanda/To-Do-List/blob/main/images/jira.png)

## Static Code Test

The projected was run through by SonarQube and as shown below, given a reliability of A with no bugs.

![SonarQube Screenshot](https://github.com/FayazPanda/To-Do-List/blob/main/images/sonarqube.png)

## Authors

* **Fayaz Sheikh** - *Project development* - [FayazPanda](https://github.com/FayazPanda)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

## Acknowledgments

* **Trainers(Alan D.,Jordan H.,Vinesh G. & Edward R.)** - All the trainers who have given me help when I was stuck at one point or another.
