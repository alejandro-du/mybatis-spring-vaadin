# MyBatis + Spring Boot + Vaadin example

This example application shows how to create a web UI for PostgreSQL databases.

## Executing the application

````
git clone https://github.com/alejandro-du/mybatis-spring-vaadin.git
````

Edit the `.../resources/application.properties` file to configure the connection to your PostgreSQL database.

````
cd mybatis-spring-vaadin
mvn install
cd target
java -jar mybatis-spring-vaadin-0.0.1-SNAPSHOT.jar
````

Read the full guide at https://vaadin.com/blog/-/blogs/building-a-web-ui-for-postgresql-databases
