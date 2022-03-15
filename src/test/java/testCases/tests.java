package testCases;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.payload;

public class tests {

	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://petstore.swagger.io/v2")
			.setContentType(ContentType.JSON).build();

	ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON)
			.build();

	@Test(priority=1)
	public void userLogin() {

		RestAssured.baseURI = "https://petstore.swagger.io/v2";

		String response = given().log().all().queryParam("username", "Peter").queryParam("password", "Peter123").when()
				.get("/user/login").then().log().all().assertThat().statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(response);
		String actualMessage = js.get("message");
		System.out.println(actualMessage);
		String expectedMessage = "logged in user session";

		Assert.assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test (priority=2)
	public void createUser() {

		RequestSpecification res = given().spec(req).body(payload.createUser("123", "JaneCanes", "Jane", "Canes"));
		Response response = res.when().post("/user").then().spec(resspec).extract().response();

		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		String message = js.get("message");
		System.out.println("this is the CreateUser message: " + message);
	}

	@Test (priority=3)
	public void updateUser() {

		
		RequestSpecification res = given().spec(req)
				.body(payload.createUser("123", "PeterPan", "Peter", "Pan"));
		Response response = res.when().put("/user/JaneCanes").then().spec(resspec).extract().response();
		
		String responseString = response.asString();
		JsonPath js = new JsonPath(responseString);
		String message = js.get("message");
		System.out.println("this is the updateUser message: " + message);
	}

}
