This project is designed for every party. No more counting who owes you money. This service will do it for you.

version 0.0.1

Init the project.

Create main controller, connect DB, Security, JPA, create main tests.

___________________________
How to deploy this project?

We are using MySQL. Firstly, you should create new connection.
You can change default properties in application config file.
Set your database and user/password.
That is all.
JPA will automatically create all necessary tables, don't worry about it :)

Secondly, go to resources/datasource/... 
There you can find SQL-update files. Use these files.
You should create new user. For example:
    login - test123
    password - qwerty

Thereafter you should open your IDE and:
    1. Open the project;
    2. Check main class - PartyListApplication;
    3. Select "Debug" or "Run"
    or terminal (gradle):
        gradle bootRun

And run in terminal:
    gradle bootRun

____________________________

We use:
Backend: Java 8, Gradle, Spring
Frontend: JS, Bootstrap 4
Database: MySQL


Version history:

version 0.0.1
Create main controller, connect DB, Security, JPA, main test
