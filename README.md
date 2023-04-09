# About

I created this project to keep my basic core Java skills fresh and to use new technologies and test them from time to time.

This project receives currency data from an external source using an API key (https://api.getgeoapi.com/v2/currency) daily (Spring schedule was used) and stores them in the database.

Accounts will be added for each user soon.

# Used Tech

• Backend : Java-Spring Boot
• Database : MySQL and H2 database (as a test database)
• Frontend : -
• Other : Lombok

# Repository

• Private access repository: https://bitbucket.org/josephpersonal/core/src/main/

# Design Pattern

· Data Access Object (DAO) Design Pattern was used
· The pattern focuses on decoupling the service layer from the data access layer. With the DAO design pattern,

- The model is transferred from one layer to the other (e.g. CurrencyRate class).
- The interfaces provide a flexible design (e.g. CurrencyRateService interface).
- The interface implementation is a concrete implementation of the persistence logic (e.g. CurrencyRateServiceImpl class).
