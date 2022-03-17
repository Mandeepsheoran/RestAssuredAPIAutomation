package aero.sita.digitaltwins.tests;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.github.javafaker.Faker;

import aero.sita.digitaltwins.annotations.FrameworkAnnotations;
import aero.sita.digitaltwins.constants.FrameworkConstants;
import aero.sita.digitaltwins.enums.CategoryType;
import aero.sita.digitaltwins.pojo.FlightDetailsPOJO;
import aero.sita.digitaltwins.reports.ExtentLogger;
import aero.sita.digitaltwins.reports.ExtentManager;
import aero.sita.digitaltwins.reports.ExtentReport;
import aero.sita.digitaltwins.requestbuilder.RequestBuilder;
import aero.sita.digitaltwins.utils.ApiUtils;
import aero.sita.digitaltwins.utils.RandomDataUtils;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;

public class PostTests {


	@Test(description="Flight Landed")
	@FrameworkAnnotations(author="Mandeep", category="SMOKE", methodType="POST")
	public void postFlightLanded() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("Haarlemmermeer", RandomDataUtils.getFirstName())
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("fnumber", String.valueOf(RandomDataUtils.getID())
			//	.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			 //   .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate())));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/flight/flight/update/ams");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(200);
	}
}
