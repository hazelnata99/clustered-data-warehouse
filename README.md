# clustered-data-warehouse
This repository holds the clustered-data-warehouse task for progress soft company.
# Task Description
Suppose you are part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to accept deals details from and persist them into DB. Data warehouse is a central repository of integrated data from one or more disparate sources. It is used for reporting and data analysis. Data warehouse is a subject-oriented, integrated, nonvolatile and time-variant collection of data in support of management's decision making process. Data warehouse is a copy of transaction data specifically structured for querying and analysis. It is a subject-oriented, integrated, nonvolatile and time-variant collection of data in support of management's decision making process. Data warehouse is a copy of transaction data specifically structured for querying and analysis.


Request Fields should contain:

  - Deal Unique Id;
  - From Currency ISO Code "Ordering Currency";
  - To Currency ISO Code;
  - Deal timestamp;
  - Deal Amount in ordering currency;

Requirements

  - System should not import same request twice.
  - Validate row structure.(e.g: Mising fields, Type format..etc.
  - No rollback allowed, what every rows imported should be saved in DB

## How to Set up
Requirements

    Java 17;
    Java IDE : IntelliJ (or Eclipse, Vscode, Netbeans);
    Database: MySql;
    Postman(For testing).
    Open the cloned project in intelliJ Idea;
    Sync all dependences on intellij.


### Setup Mysql

Step 1: On your terminal execute the following command: 
         docker exec -it my-mysql-db  mysql -u user -p

         
Step 2: Type "password".

Step 3: You should be able to log in to mysql container. And see the following:
          rinatabakeer@Rinatas-MacBook-Pro ~ % docker exec -it my-mysql-db  mysql -u user -p
          
          Enter password: 
          Welcome to the MySQL monitor.  Commands end with ; or \g.
          Your MySQL connection id is 144
          Server version: 8.1.0 MySQL Community Server - GPL
          
          Copyright (c) 2000, 2023, Oracle and/or its affiliates.
          
          Oracle is a registered trademark of Oracle Corporation and/or its
          affiliates. Other names may be trademarks of their respective
          owners.
          
          Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.
          
          mysql> 

Step 4: Create a Database (if needed):

If you haven't already created a database, you can create one using SQL commands. Using:
            CREATE DATABASE fx_deals;

Step 5: Use the Database:
            USE fx_deals;

Step 6: Create the deal table:

            CREATE TABLE deal (
                deal_id INT NOT NULL PRIMARY KEY,
                from_currency VARCHAR(4) NOT NULL,
                to_currrency VARCHAR(4) NOT NULL,
                amount decimal(10,3) NOT NULL,
                time timestamp
            );

This SQL command creates a table named "deal" with columns for deal_id, from_currency, to_currrency, and amount and time.


## Testing The API Endpoints

### Execution:
by running the following commands:
        docker system prune
        docker volume prune
        docker-compose up 

#### Accepting Deal details

Endpoint: 'http://localhost:8080/api/fx-deals/save';


      curl -X POST -H "Content-Type: application/json" -d '{ "dealId":119,"fromCurrency": "USD", "time": "2023-06-07T21:00:00.000Z", "amount" : 175.15}' http://localhost:8080/api/fx-deals/save

Response

      "message": "The fx deal has successfully been added..% "

#### Getting all deals details

Endpoint: '(http://localhost:8080/api/fx-deals/show)';

     curl -X GET http://localhost:8080/api/fx-deals/show

Response


        [{"dealId":0,"toCurrency":"JOD","fromCurrency":"USD","amount":175.150,"time":2023-10-03 14:30:00},
        {"dealId":100,"toCurrency":"JOD","fromCurrency":"AED","amount":11.150,"time":"2023-10-03 14:30:00"},
        {"dealId":101,"toCurrency":"JOD","fromCurrency":"NGN","amount":200.150,"time":2023-10-11 21:30:00},
        {"dealId":123,"toCurrency":"JOD","fromCurrency":"ZAR","amount":175.150,"time":2023-10-03 14:30:00}]%

