# carDatabase
The carDatabase demo project

## information
The carDatabase is a demo project to show, add, edit and delete records of cars. The application is written in Java and uses the Spring Web MVC framework. 
The result of the code is a WAR file that can be deployed to an apache tomcat server. The webapp connects to a SQL database like mySQL or mariaDB to store it's records.

## build from source
The source code of this project can be downloaded/cloned and build with maven, e.g.
```shell
mvn compile package
```

## installation
The carDatabase is meant to run in a docker environment, so the only thing you have to do is 
```shell
docker-compose up
```

**NOTE:** The first time it will take approximately two minutes to start the application. This is because at the first start of the mariaDB container the entrypoint script gets executed. *The application won't function properly before this step is completed.* 

One minute into the first start you will see the message:
> /usr/local/bin/docker-entrypoint.sh: running /docker-entrypoint-initdb.d/00_cardb.sql
	
This means mariaDB is importing the default dataset right now. Wait another minute for it to complete.

The application is ready to use when it shows the following line for the **second** time on the console:
> [Note] mysqld: ready for connections.

## usage
The application will be accessable via http://localhost:8080 . From there you can start to work with the application.

## manual installation
You can build WAR file with maven and deploy it to apache tomcat yourself. The WAR file contains a `Spring-Datasource.xml` that you can find when opening the WAR file for example with 7-Zip and navigate to the path `WEB-INF/classes/database/`. In this file you can set the url and credentials for the database server.

