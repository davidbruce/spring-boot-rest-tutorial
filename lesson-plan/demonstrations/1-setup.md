# Setting up a Spring Boot Project
**Time Estimate: 20 Minutes**

Explain at a high level that web applications are split between a frontend and a backend.
Continue that a backend is generally where the majority of data processing takes place and
is where the application communicates with the database.

Segue into how Java is a popular choice for building the backend of applications thanks to tools
like Spring Boot. Explain that it is an industry-standard framework with an opinionated approach
that guides the developer towards best practices.

The easiest way to create a Spring Boot project is using the Spring Initializr.
This is a website that will generate the directory structure and `pom.xml` file needed
to start developing the application. Open https://start.spring.io/ and add the following dependencies:
* Spring Web
* Spring Data JPA
* H2 Database

Then change the Java Version to 17.

**Optional:**
* Change the metadata of the project. This will be reflected in the `pom.xml`.
* Press *Explore* and show the students the directory structure generated.

It would be worth noting to the students that Maven is a package manager for Java. It uses the `pom.xml`
to download the libraries the application needs.

Below is what your Spring Initialzr should like before generating:
![Spring Initialzr with Dependencies](../images/Spring%20Initialzr.png)

# Importing the Project
After downloading the `.zip` file generated, unzip and copy it to your development directory.
Then show the students how to load the project into IntelliJ by simply opening the root directory
of the unzipped folder.

The project will automatically start indexing and pulling down Maven Dependencies. Once that is
complete right-click the DemoApplication class file under `src` and press run.

![Running the App from the IntelliJ Context Menu](../images/Context%20Menu.png)

The IntelliJ Run pane will open and show the application booting up and eventually stating:
```
com.example.demo.DemoApplication         : Started DemoApplication in X.XX seconds (JVM running for X.XXX)
```

Make sure all students have gotten the application running before continuing. 
[2. Entities](2-entities.md)