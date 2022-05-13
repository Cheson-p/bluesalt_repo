# Bluesalt Repo
## This repo os for mini bluesalt task
**Note: The services Customer, Billing and Billing Worker in this directory are separate services**  
To run this services :
1: Clone this repo  
2: Go to root direcory of each projects and build them ( Ensure you have maven installed ) i.e in /customer-service and run mvn clean install -Dmaven.test.skip=true, repeat this for all other services  
3: There is a Dockerfile in each projects, so we can create docker images from them.   
3.i   Go to /customer-service and run docker build -t customer_service.  
3.ii  Go to /billing_service and run docker build -t billing_service .  
3.iii Go to /billing_worker_service and run docker build -t billing_worker_service .  
4: Change to the root directory of these projects ( /BlueSalt ) and run docker-compose up
   Note:  Ensure there is customer database and billing database created inside postgres database, if not there, once you run the docker-compose, create the databases accordingly and rerun docker-compose up  
5: Verify everything is setup by checking the below :  
5.i: Confirm sample record is available in customer table inside customer database (service name is same as database name)  
5.ii: Get the customerId from table and use it as part of REST api request to be made to customer service for fundAccount operation ( to view api doc http://localhost:20001/swagger-ui/index.html )  
5.iii: Check all microservices logs to validate they all consume and produce event as expected.

