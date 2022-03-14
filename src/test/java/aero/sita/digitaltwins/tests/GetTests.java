package aero.sita.digitaltwins.tests;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import aero.sita.digitaltwins.annotations.FrameworkAnnotations;
import aero.sita.digitaltwins.enums.CategoryType;
import aero.sita.digitaltwins.reports.ExtentLogger;
import aero.sita.digitaltwins.reports.ExtentManager;
import aero.sita.digitaltwins.reports.ExtentReport;
import aero.sita.digitaltwins.requestbuilder.RequestBuilder;
import org.assertj.core.api.Assertions;

import io.restassured.response.Response;

public class GetTests extends BaseTest {	
	@Test(description="Flight Delayed")
	@FrameworkAnnotations(author="Mandeep",category= "QUICKREGRESSION" , methodType="GET")
	public void getAllFlightDetails() {
		Response response = RequestBuilder.buildRequestForGetCalls()
				.get("/posts");
		response.prettyPrint();
		ExtentLogger.logResponse(response.asPrettyString());
		Assertions.assertThat(response.getStatusCode())
		.as("Checking status code")
		.isEqualTo(200)
		.isNotEqualTo(201)
		.isNotNull();
		
		Assertions.assertThat(response.jsonPath().getList("$").size())
		.as("Checking the size of the result list")
		.isNotNull();
		//	.isEqualTo(15);
		
		Assertions.assertThat(response.jsonPath().getString("title").equals("postman testing"));
		Assertions.assertThat(response.header("Content-Type"))
		.isEqualTo("application/json; charset=utf-8");
	}

	@Test(description="Stand Allocated")
	@FrameworkAnnotations(author="Mandeep", category="REGRESSION", methodType="GET")
	public void getSingleFlightDetail() {
		Response response = RequestBuilder.buildRequestForGetCalls()
				.pathParam("id", 1)
				.get("/posts/{id}");
		//response.prettyPrint();
		ExtentLogger.logResponse(response.asPrettyString());
		Assertions.assertThat(response.getStatusCode())
		.as("Checking status code")
		.isEqualTo(200)
		.isNotEqualTo(201)
		.isNotNull();
		
	//	Assertions.assertThat(response.jsonPath().getString("firstname").equals("Rahl"));
		Assertions.assertThat(response.jsonPath().getList("email"))
		.contains("mandeepsheoran@gmail.com")
		.doesNotContain("mandeepsheoran1@gmail.com")
		.hasSizeLessThan(3)
		.isNotNull();
		
		Assertions.assertThat(response.header("Content-Type"))
		.isEqualTo("application/json; charset=utf-8");
	}
}
