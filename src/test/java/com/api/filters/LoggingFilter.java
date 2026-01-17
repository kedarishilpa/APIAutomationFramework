package com.api.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class LoggingFilter implements Filter {

	private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		logRequest(requestSpec);

		Response response = ctx.next(requestSpec, responseSpec); // Request is going to be executed
		logResponse(response);
		return response; // taken to the test for the assertion further validations
	}

	public void logRequest(FilterableRequestSpecification requestSpec) {

		logger.info("BASE URI: " + requestSpec.getBaseUri());
		logger.info("Request Headers: " + requestSpec.getHeaders());
		logger.info("Request Payload: " + requestSpec.getBody());
	}

	public void logResponse(Response response) {
		logger.info("StatusCode: " + response.getStatusCode());
		logger.info("Response Headers: " + response.headers());
		logger.info("Response Body: " + response.getBody().prettyPrint());

	}

}
