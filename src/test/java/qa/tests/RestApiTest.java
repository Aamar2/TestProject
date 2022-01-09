package qa.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonObject;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;

public class RestApiTest {
	
	public String token;
	public int userId;
	HashMap<String,String> map;
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "http://restapi.adequateshop.com";
		Random rand = new Random();
		int rand_int1 = rand.nextInt(10000);
		int rand_int2 = rand.nextInt(10000);
		map = new HashMap<String,String>();
		map.put("name", "amar"+ String.valueOf(rand_int1));
		map.put("email", "amar"+String.valueOf(rand_int1)+".reddy@yui.com");
		map.put("location", "INDIA");
	}
	
	@Test(priority=1)
	public void userRegistration () {
		
		String user_Details = "{\r\n" +
				" \"name\": \"Developer12\",\r\n" +
				" \"email\": \"amar89@gmail.com\",\r\n" +
				" \"password\": \"123456\",\r\n" +
				" }";
		
		Response res =  given().contentType(ContentType.JSON).when().body(user_Details).post("/api/authaccount/registration").then().statusCode(200).extract().response();
	}
	
	@Test(priority=2)
	public void userLogin () {
		
		String user_Details = "{\r\n" +
				" \"email\": \"amar89@gmail.com\",\r\n" +
				" \"password\": \"123456\",\r\n" +
				" }";
		
		Response res =  given().contentType(ContentType.JSON).when().body(user_Details).post("/api/authaccount/login").then().statusCode(200).extract().response();
		JSONObject jo = new JSONObject(res.asString());
		String tokens = jo.getJSONObject("data").getString("Token");
		token = tokens;

		
	}
	
	@Test(priority=3)
	public void get_Users() {
		Response response = RestAssured.given().header("Authorization" ,"Bearer " + token)
				.queryParam("page", "1").when().get("/api/users").then().statusCode(200).extract().response();
		System.out.println(response.statusCode());
	}
	
	@Test(priority=4)
	public void createUser_Post() {
	/*	HashMap<String,String> map = new HashMap<String,String>();
		map.put("name", "amar98");
		map.put("email", "amar98.reddy@yui.com");
		map.put("location", "INDIA"); */
		Response response = RestAssured.given().header("Authorization" ,"Bearer " + token).contentType(ContentType.JSON)
				.when().body(map).post("/api/users").then().extract().response();
		Assert.assertEquals(201, response.statusCode());
		JSONObject jo = new JSONObject(response.asString());
		this.userId = jo.getInt("id");
	}
	
	@Test(priority=5)
	public void getUser_GET() {
		
		Response response = RestAssured.given().header("Authorization" ,"Bearer " + token)
				.when().get("/api/users/"+ String.valueOf(this.userId)).then().extract().response();
		Assert.assertEquals(200, response.statusCode());
		JSONObject jo = new JSONObject(response.asString());
		System.out.println(jo);
		Assert.assertEquals(map.get("name"), jo.getString("name"));
		Assert.assertEquals(map.get("email"), jo.getString("email"));
		Assert.assertEquals(map.get("location"), jo.getString("location"));
	}
	
	@Test(priority=6)
	public void updateUser_Put() {
		
		JSONObject jsonObject = new JSONObject();
		//jsonObject.put("id", this.userId);
		jsonObject.put("name", "testUser23");
		jsonObject.put("email", "testUsery23@yui.com");
		jsonObject.put("location", "USA");
		
		Response response = RestAssured.given().header("Authorization" ,"Bearer " + token).contentType(ContentType.JSON)
				.when().body(jsonObject).put("/api/users/"+ String.valueOf(this.userId)).then().extract().response();
		System.out.println(response.asString());
	}
	
	@Test(priority=7)
	public void deleteUser_Delete() {
		
		Response response = RestAssured.given().header("Authorization" ,"Bearer " + token).contentType(ContentType.JSON)
				.when().delete("/api/users/"+ String.valueOf(this.userId)).then().extract().response();
		System.out.println(response.asString());
		Assert.assertEquals(200, response.statusCode());
		JSONObject jo = new JSONObject(response.asString());
		System.out.println(jo);

	}
	
	@Test(priority=8)
	public void getUser_GET_afterDelete() {
		
		Response response = RestAssured.given().header("Authorization" ,"Bearer " + token)
				.log().all().when().get("/api/users/"+ String.valueOf(this.userId)).then().extract().response();
		Assert.assertEquals(404, response.statusCode());
		
	}

}
