package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.model.request.LoginRequest;
import com.api.model.request.SignUpRequest;
import com.api.model.response.LoginResponse;
import com.api.model.response.UserProfileResponse;

import io.restassured.response.Response;

public class GetProfileAPITest {
	

	@Test(description = "Verify if Get Profile API is working ")
	public void getProfileAPITest () {
		
		AuthService authService=new AuthService();
		
		LoginRequest loginRequest=new LoginRequest("shilpa123", "shilpa123");
		Response response=authService.login(loginRequest);
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		
		LoginResponse loginResponse=response.as(LoginResponse.class);
		
		int ID=loginResponse.getId();
		String token =loginResponse.getToken();
		System.out.println("Login Token : "+token);
		
		Assert.assertTrue(token!=null);
		Assert.assertEquals(ID,3540);
		UserProfileManagementService userProfileManagementService=new UserProfileManagementService();
		Response getProfileResponse=userProfileManagementService.getProfile(token);
		
		// this is one way of getting json field value without creating pojo class
		System.out.println(getProfileResponse.asPrettyString());
		Assert.assertEquals(getProfileResponse.jsonPath().getInt("id"),3540);
		System.out.println("ID is matched >> "+getProfileResponse.jsonPath().getString("id"));
		
		//get data by using pojo class UserProfileResponse
		
		UserProfileResponse userProfileResponse=getProfileResponse.as(UserProfileResponse.class);
		
		Assert.assertEquals(userProfileResponse.getUsername(),"shilpa123");
		
		

	}

}
