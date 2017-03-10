# HOW TO RUN PROJECT
## Database: Mysql 6.3CE
* Create database name: eis
* Fix:
spring.datasource.username and spring.datasource.password in application.properties

## Run: Application.java by Application
## Default username: 
* admin01@gmail.com - password: 123456 - role: ROLE_ADMIN, ROLE_MEMBER
* admin02@gmail.com - password: 123456 - role: ROLE_ADMIN, ROLE_MEMBER
* member@gmail.com - password: 123456 - role: ROLE_MEMBER

# a. Description
E-Invoice is built for the purpose of monthly invoice management in a family. It helps us to have an overall report of monthly expenses so that user could make plan to save money. User could set an alert for a limited amount of money consumed every month. Each invoice will have following information:
* Type of invoice: Electric, internet, telephone, water, ...
* Amount of money.
* VAT
* Charged period (monthly).
* Invoice No.
* Customer code (optional).

# b. Functional Specification
## Users:
* Register account.
* CRUD services.
* CRUD invoice consumed by a service.
* View expenses report: Monthly, Yearly, or a period of time.
* View charts: Bar chart, Pie chart (students should think about the details).
* Set the monthly limited expenses for notification (via email).

## Admin:
* Activate/Deactivate a user account.
* Configure the trigger point of time for running scheduled job to send email for monthly notification to each user if their monthly expenses exceed the pre-set limited amount of money.

## System constrains:
* Check duplication of monthly invoice when adding an invoice.
* Able to send email for monthly notification to every user at trigger time (using POP3).

# Technical Requirements
* Framework: Spring MVC, Hibernate. (Bonus if: Spring Security, Applying Spring boot)
* Client side: AngularJS, Boostrap, HTML5/CSS3
* Database system: MySQL or MongoDB
* All CRUD operation should have JUnit tests.
* Sample data should be prepared before presentation
* Pagination on returned result in search function.
* Source control: GitHub
### Ref: https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples
