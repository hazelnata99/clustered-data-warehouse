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
    Go to POM.xml the update Project to update all the maven dependencies;
    Maven Build the project and run;


## Testing The API Endpoints

#### Accepting Deal details

Endpoint: 'http://localhost:8080/api/fx-deals/save';


      curl -X POST -H "Content-Type: application/json" -d '{ "dealId":119,"fromCurrency": "USD", "time": "2023-06-07T21:00:00.000Z", "amount" : 175.15}' http://localhost:8080/api/fx-deals/save

Response

      "message": "The fx deal has successfully been added..% "

#### Getting all deals details

Endpoint: '(http://localhost:8080/api/fx-deals/show)';

     curl -X GET http://localhost:8080/api/fx-deals/show

Response


        [{"dealId":0,"toCurrency":"JOD","fromCurrency":"USD","amount":175.150,"time":null},{"dealId":100,"toCurrency":"JOD","fromCurrency":"USD","amount":175.150,"time":null},{"dealId":101,"toCurrency":"JOD","fromCurrency":"USD","amount":175.150,"time":null},{"dealId":123,"toCurrency":"JOD","fromCurrency":"USD","amount":175.150,"time":null}]%

