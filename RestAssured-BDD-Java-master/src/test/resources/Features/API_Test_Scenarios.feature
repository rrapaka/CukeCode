#This feature file contains few test scenarios to verify Rest API services
#Author: Ramesh Rapaka

Feature: To verify rates for a given country on a specific dates.

 @API_Test	
Scenario: GET valid response code for the latest exchange rates for a given currency
  Given I Set GET Rate api endpoint "https://api.ratesapi.io/api/latest"
  When I Set base param request country code as "CHF" 
	And Send GET HTTP request
  Then I receive valid HTTP response code 200 for "GET." 

 @API_Test	
Scenario: GET valid response and assert response for the latest exchange rates for a give currency
  Given I Set GET Rate api endpoint "https://api.ratesapi.io/api/latest"
  When I Set base param request country code as "USD" 
	And Send GET HTTP request
  Then I receive valid HTTP response code 200 for "GET." 
	And Response BODY "GET" is non-empty

 @API_Test	
Scenario: GET invalid response code for the latest exchange rates for an incorrect URI
  Given I Set GET Rate api endpoint "https://api.ratesapi.io/api"
  When I Set base param request country code as "USD" 
	And Send GET HTTP request
  Then I receive invalid HTTP response code which is not equals to "200"
  
 @API_Test	
Scenario: GET valid response for the latest exchange rates for a given currency on a specific date
  Given I Set GET Rate api endpoint "https://api.ratesapi.io/api"
  And to a specific date "2019-01-11"
  When I Set base param request country code as "USD"  	
	And Send GET HTTP request
  Then I receive valid HTTP response code 200 for "GET." 

 @API_Test	
Scenario: GET valid response and assert response for the latest exchange rates for a given currency on a specific date
  Given I Set GET Rate api endpoint "https://api.ratesapi.io/api"
  And to a specific date "2019-01-12"
  When I Set base param request country code as "CHF"  	
	And Send GET HTTP request
  Then I receive valid HTTP response code 200 for "GET." 
	And Response BODY "GET" is non-empty
	
 @API_Test	
Scenario: GET valid response and assert response for the latest exchange rates for a given currency on a future date
  Given I Set GET Rate api endpoint "https://api.ratesapi.io/api"
  And to a specific date "2022-01-12"
  When I Set base param request country code as "CHF"  	
	And Send GET HTTP request
  Then I receive valid HTTP response code 200 for "GET." 
	And Response BODY "GET" is non-empty
	And Response Body should match the current date
