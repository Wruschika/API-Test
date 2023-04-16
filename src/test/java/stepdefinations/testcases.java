package stepdefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testcases {

private final String BASE_URL = "https://gorest.co.in";
private final String token = "f22715ae85220457779b9a82f3a3fb4e833f789a92d814146ce9421807a8911f";
private final String authToken ="Bearer "+token;
public static int id;
//private final String data = 
	private Response response;
	RequestSpecification requestspecification;
	private RequestSpecification request;
	@Given("Add user API and Payload")
	public void add_user_api_and_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		dataDriven d=new dataDriven();
		ArrayList<String> data=d.getData("Add");
		
		
		HashMap<String, Object>  jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", data.get(1));
		jsonAsMap.put("gender", data.get(2));
		jsonAsMap.put("email", data.get(3));
		jsonAsMap.put("status", data.get(4));
		
RestAssured.baseURI= BASE_URL;
//String token ="f22715ae85220457779b9a82f3a3fb4e833f789a92d814146ce9421807a8911f";
//String authToken ="Bearer "+token;

request = RestAssured.given().log().all().headers("Content-Type","application/json","Accept","application/json","Authorization",authToken)
.body(jsonAsMap);

	}
	@When("user calls {string} with post request")
	public void user_calls_with_post_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
		response = request.when().post("public/v2/users");
		
	}
	@Then("API call success with status code {int}")
	public void api_call_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		int actualresponsecode = response.then().log().all().extract().statusCode();
	    Assert.assertEquals(response.getStatusCode(),201);
	    id = response.then().extract().path("id");
	    System.out.println(id);
	}

	
	@Given("Get user API")
	public void get_user_api() {
	    // Write code here that turns the phrase above into concrete actions
		RestAssured.baseURI= BASE_URL;
		//String token ="f22715ae85220457779b9a82f3a3fb4e833f789a92d814146ce9421807a8911f";
		//String authToken ="Bearer "+token;

		request = RestAssured.given().log().all().headers("Content-Type","application/json","Accept","application/json","Authorization",authToken);
	}
	@When("user calls {string} with get request")
	public void user_calls_with_get_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
		response = request.when().get("public/v2/users");
	}

	@Then("API call success with status code equal {int}")
	public void api_call_success_with_status_code_equal(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		response.then().log().all().extract().statusCode();
	    Assert.assertEquals(response.getStatusCode(),200);
	   
	
	}
	@Given("update user API and id")
	public void update_user_api_and_id()throws IOException {
	   
		    // Write code here that turns the phrase above into concrete actions
			dataDriven d=new dataDriven();
			ArrayList<String> data=d.getData("Update");
			
			
			HashMap<String, Object>  jsonAsMap = new HashMap<>();
			jsonAsMap.put("name", data.get(1));
			jsonAsMap.put("gender", data.get(2));
			jsonAsMap.put("email", data.get(3));
			jsonAsMap.put("status", data.get(4));
			
	RestAssured.baseURI= BASE_URL;

	request = RestAssured.given().pathParam("userID",id).log().all().headers("Content-Type","application/json","Accept","application/json","Authorization",authToken)
	.body(jsonAsMap);
	}
	@When("user calls {string} with put request")
	public void user_calls_with_put_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
		response = request.when().put("/public/v2/users/{userID}");
	}
	@Then("API call success with status code as {int}")
	public void api_call_success_with_status_code_as(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		int actualresponsecode = response.then().log().all().extract().statusCode();
	    Assert.assertEquals(response.getStatusCode(),200);
	   
	}

	@Given("delete user API and id")
	public void delete_user_api_and_id() {
		
	    // Write code here that turns the phrase above into concrete actions
		
		RestAssured.baseURI= BASE_URL;
		//String token ="f22715ae85220457779b9a82f3a3fb4e833f789a92d814146ce9421807a8911f";
		//String authToken ="Bearer "+token;

		request = RestAssured.given().pathParam("userID",id).log().all().headers("Content-Type","application/json","Accept","application/json","Authorization",authToken);
	}
	@When("user calls {string} with delete request")
	public void user_calls_with_delete_request(String string) {
	    // Write code here that turns the phrase above into concrete actions
		response = request.when().delete("/public/v2/users/{userID}");
	}
	@Then("API call success with status code is {int}")
	public void api_call_success_with_status_code_is(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
		response.then().log().all().extract().statusCode();
	    Assert.assertEquals(response.getStatusCode(),204);
	}
	

}

