package com.api.tests;

import static io.restassured.RestAssured.given;

import java.lang.module.ModuleDescriptor.Builder;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.model.request.SignUpRequest;

import io.restassured.response.Response;

public class AccountCreationTest {
	
	

	@Test(description = "Verify if Sign Up API is working ")
	public void createAccountTest() {
		
		SignUpRequest signUpRequest=new SignUpRequest.Builder()
				.username("yogeshr123")
				.firstName("yogesh")
				.lastName("R")
				.email("yogeshr123@mail.com")
				.mobileNumber("9999999991")
				.password("yogeshr123")
				.build();
		
		AuthService authService=new AuthService();
		Response response= authService.signUp(signUpRequest);
		
		response.getBody();
		
		System.out.println(response.asPrettyString());
		
		Assert.assertEquals(response.asPrettyString(), "User registered successfully!");
		

	}

}
