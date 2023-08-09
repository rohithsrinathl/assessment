package com.assess.definitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
 
public class APIStepDef {	
	
	private static Response response;
	private static String jsonString;	
	private static final String BASE_URL = "https://reqres.in";
    
	@Given("the GET endpoint {string} and the request is triggered for get single user with id {string}")
	public void the_get_endpoint_and_the_request_is_triggered_for_get_single_user_with_id(String endpoint, String id) {
		RestAssured.baseURI = BASE_URL;	
		RequestSpecification request = RestAssured.given();
		response = request.get(endpoint + "/"+ id);	
		response.prettyPrint();
	}

	@When("the API return {string} status code in the response")
	public void the_api_return_status_code_in_the_response(String statusCode) {
		Assert.assertEquals(response.getStatusCode(), Integer.parseInt(statusCode));
	}

	@Then("the user details are validated for the sent id {string}")
	public void the_user_details_is_validated_for_the_sent_id(String id) {		
		jsonString = response.asString();
		int idFromResponse = JsonPath.from(jsonString).get("data.id");
		Assert.assertEquals(idFromResponse, Integer.parseInt(id));		
		Assert.assertNotNull(JsonPath.from(jsonString).get("data.email"));
		Assert.assertNotNull(JsonPath.from(jsonString).get("data.first_name"));
		Assert.assertNotNull(JsonPath.from(jsonString).get("data.last_name"));
		Assert.assertNotNull(JsonPath.from(jsonString).get("data.avatar"));
		Assert.assertNotNull(JsonPath.from(jsonString).get("support.url"));
		Assert.assertNotNull(JsonPath.from(jsonString).get("support.text"));
	}

	@Given("the POST endpoint {string} and request is triggered with the payload")
	public void the_post_endpoint_and_request_is_triggered_with_the_payload(String endpoint) {
		RestAssured.baseURI = BASE_URL;	
		RequestSpecification request = RestAssured.given();
		response = request.body("{ \"name\":\"morpheus\",\"job\":\"leader\"}").post(endpoint);	
		response.prettyPrint();
	}

	@Then("the created user details are validated")
	public void the_created_user_details_are_validated() {
		jsonString = response.asString();
		Assert.assertNotNull(JsonPath.from(jsonString).get("id"));
		Assert.assertNotNull(JsonPath.from(jsonString).get("createdAt"));
	}
       
}
