# springfx_userdara (Spring-Boot JavaFX Challenge)
springfx_userdara is a challenge project to test running JavaFX Application with spring-boot

## Start the project
There is two way to start application:

1. From Code: Just run one of these java app:
    * src/main/java/com/seifi/springfx_userdara/JavaFxApplication.java
    * src/main/java/com/seifi/springfx_userdara/SpringFxApp.java
2. From Commandline:
    * run: .\mvn clean package
    * cd target/
    * run: .\<path/to/java11>\java.exe -cp "original-springfx_userdara-0.0.1.jar;springfx_userdara-0.0.1.jar;springfx_userdara-0.0.1.jar.original" com.seifi.springfx_userdara.UserDataJavaFxApplication

## List of technologies / frameworks
Here is the list of the technologies and frameworks that used in this project:

* springframework
* jdbc
* jpa/Hibernate (spring-boot-starter-data-jpa)
* javafx (openjfx, javafx-controls, javafx-fxml)
* sqlite-jdbc
* unit-test
    * mockito
    * junit
    * spring-boot-starter-test
