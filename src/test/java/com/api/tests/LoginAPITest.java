package com.api.tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.model.request.LoginRequest;
import com.api.model.response.LoginResponse;

import io.restassured.response.Response;

@Listeners(com.api.listeners.TestListener.class)
public class LoginAPITest {
	
	@Test(description = "Verify if login API is working ")
	public void LoginTest() {

		AuthService authService=new AuthService();
		//Response response=authService.login("{\"username\": \"yogeshr123\",\"password\": \"yogeshr123\"}");
		LoginRequest loginRequest=new LoginRequest("yogeshr123", "yogeshr123");
		Response response=authService.login(loginRequest);
		
		System.out.println(response.asPrettyString());
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		LoginResponse loginResponse=response.as(LoginResponse.class);
		
		int ID=loginResponse.getId();
		String token =loginResponse.getToken();
		System.out.println("Login Token : "+token);
		
		Assert.assertTrue(token!=null);
		Assert.assertEquals(ID,3549);
		Assert.assertEquals(loginResponse.getEmail(), "yogeshr123@mail.com");
		
		

	}

}
