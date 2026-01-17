package com.api.tests;

import static io.restassured.RestAssured.given;

import java.lang.module.ModuleDescriptor.Builder;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.model.request.SignUpRequest;

import io.restassured.response.Response;

public class FortgotPasswordAPITest {
	
	

	@Test(description = "Verify if forgot password Up API is working ")
	public void forgotPasswordTest() {
		
		
		
		AuthService authService=new AuthService();
		Response response= authService.forgotPassword("yogesh556@mail.com");
		System.out.println(response.asPrettyString());
		 // Extract message
	    String actualMessage = response.jsonPath().getString("message");

	    // Assertion
	    Assert.assertEquals(
	            actualMessage,
	            "If your email exists in our system, you will receive reset instructions."
	    );
		

	}

}
