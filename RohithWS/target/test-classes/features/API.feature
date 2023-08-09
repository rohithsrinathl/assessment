@API
Feature: Request Response site APIs
 
Scenario: GetUserById 
   Given the GET endpoint "/api/users" and the request is triggered for get single user with id "2"
	 When the API return "200" status code in the response
   Then the user details are validated for the sent id "2"
   
Scenario: CreateUser 
   Given the POST endpoint "/api/users" and request is triggered with the payload
	 When the API return "201" status code in the response
   Then the created user details are validated


