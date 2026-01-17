package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.model.request.LoginRequest;
import com.api.model.request.UpdateProfileRequest;
import com.api.model.response.LoginResponse;
import com.api.model.response.UserProfileResponse;

import io.restassured.response.Response;

public class UpdateProfileTest {

	
	
	@Test(description = "Verify if Update Profile API is working ")
	public void UpdateProfileAPITest () {
		
		AuthService authService=new AuthService();
		
		Response response=authService.login(new LoginRequest("yogeshr123", "yogeshr123"));
		
		LoginResponse loginResponse=response.as(LoginResponse.class);
		
		String updatedName="Viren";
		UpdateProfileRequest profileRequest=new UpdateProfileRequest.Builder()
				.firstName(updatedName)
				.lastName("R")
				.email("yogeshr123@mail.com")
				.mobileNumber("9999999991")
				.build();
		
		
		
		UserProfileManagementService userProfileManagementService=new UserProfileManagementService();
		response=userProfileManagementService.updateProfile(loginResponse.getToken(), profileRequest);
		System.out.println(response.asPrettyString());
		
		UserProfileResponse userProfileResponse=response.as(UserProfileResponse.class);
		
		
		
		Assert.assertEquals(userProfileResponse.getFirstName(), updatedName);
		
		System.out.println("FirstName of the user is updated to "+userProfileResponse.getFirstName());
		
		
	
	
}
	
}
