This project is developed for each party. No more counting who owes you money. The service will do it for you.

version 0.0.1
init the project.
Create main controller, connect DB, Security, JPA, create main tests.


___________________________
How to deploy this project?

First - we used MYSQL database, for this reason will create new connection.
You may change default our properties in application config file.
Set your database and user/password.
That is all.
JPA auto created the new tables. Not experience.

Second - resources/datasource/... 
here are the sql-update files. Use these files.
You create new user:
    login - 2
    password - 2

Run with IDE:
    1. open the project in IDE;
    2. Check main class - PartyListApplication;
    3. Select "Debug" or "Run"
    or terminal (gradle):
        gradle bootRun

Run terminal:
    gradle bootRun

____________________________

Our team use:
backend: Java 8, Gradle, Spring
frontend: Js, Bootstrap 4
database: MySQL


Version history:

version 0.0.1
Create main controller, connect DB, Security, JPA, main test
