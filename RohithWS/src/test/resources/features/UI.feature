Feature: RequestResponse UI Validation
 
Scenario: HomePage 
   Given the user navigates to "https://reqres.in" in chrome
   When the user lands on the home page
   Then the user should see different request types and end points
   When the user clicks on "users-single-not-found"
   Then the user should see "/api/users/23" in the request
   And the user should see "404{}" in the response
   
   
Scenario: Support Page      
   Given the user navigates to "https://reqres.in" in chrome
   When the user clicks on the button "SUPPORT REQRES"
   Then the user navigates to support page
   Then the user should see "One-time payment ($)" and "Monthly support ($5/month)" options
   And the user should see the "UPGRADE" button