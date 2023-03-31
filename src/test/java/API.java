import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.equalTo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import io.restassured.path.json.JsonPath;

public class API {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		dataDriven d=new dataDriven();
		ArrayList<String> data=d.getData("Add");
		
		
		HashMap<String, Object>  jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", data.get(1));
		jsonAsMap.put("gender", data.get(2));
		jsonAsMap.put("email", data.get(3));
		jsonAsMap.put("status", data.get(4));
		
RestAssured.baseURI="https://gorest.co.in";
String token ="f22715ae85220457779b9a82f3a3fb4e833f789a92d814146ce9421807a8911f";
String authToken ="Bearer "+token;
String response=given().log().all().headers("Content-Type","application/json","Accept","application/json","Authorization",authToken)
.body(jsonAsMap).when().post("public/v2/users")
.then().log().all().assertThat().statusCode(201).extract().response().asString();

JsonPath js=new JsonPath(response);
String id=js.getString("id");


	}

}
