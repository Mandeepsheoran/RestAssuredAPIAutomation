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

public class PostTests extends BaseTest {
	@Test(description="Unlinked flight")
	@FrameworkAnnotations(author="Mandeep", category="REGRESSION", methodType="POST")
	public void postFlightDetails() {
		FlightDetailsPOJO fltdtls = FlightDetailsPOJO
				.builder()
				.setId(RandomDataUtils.getID())
				.setFirstname(RandomDataUtils.getFirstName())
				.setLastname(RandomDataUtils.getLastName())
				.build();

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(fltdtls);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//		response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		Assertions.assertThat(response.getStatusCode())
		.isEqualTo(201)
		.isNotNull();
	}

	@Test(description="Flight Landed")
	@FrameworkAnnotations(author="Mandeep", category="SMOKE", methodType="POST")
	public void postFlightLanded() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("Haarlemmermeer", RandomDataUtils.getFirstName())
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("flight_number", String.valueOf(RandomDataUtils.getID())
				.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			    .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate())));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
	}
	
	@Test(description="Flight Arrived")
	@FrameworkAnnotations(author="Mandeep", category="SMOKE", methodType="POST")
	public void postFlightArrived() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("Haarlemmermeer", RandomDataUtils.getFirstName())
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("flight_number", String.valueOf(RandomDataUtils.getID())
				.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			    .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate()))
				.replace("LN", "AR")
				.replace("Landed", "Arrived"));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
	}
	
	@Test(description="Gate Updated")
	@FrameworkAnnotations(author="Mandeep", category="SMOKE", methodType="POST")
	public void postFlightGateUpdated() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("flight_status_actual", "gate_updated")
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("flight_number", String.valueOf(RandomDataUtils.getID())
				.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			    .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate()))
				.replace("LN", "SC")
				.replace("Landed", "Scheduled"));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
	}
	
	@Test(description="Flight Delayed")
	@FrameworkAnnotations(author="Mandeep", category="SMOKE", methodType="POST")
	public void postFlightDelayed() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("flight_status_actual", "flight_updated")
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("flight_number", String.valueOf(RandomDataUtils.getID())
				.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			    .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate()))
				.replace("LN", "DL")
				.replace("Landed", "Scheduled"));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
	}
	
	@Test(description="Flight Cancelled")
	@FrameworkAnnotations(author="Mandeep", category="SMOKE", methodType="POST")
	public void postFlightCancelled() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("flight_status_actual", "flight_updated")
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("flight_number", String.valueOf(RandomDataUtils.getID())
				.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			    .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate()))
				.replace("LN", "CX")
				.replace("Landed", "Cancelled"));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
	}
	
	@Test(description="Go to Gate")
	@FrameworkAnnotations(author="Mandeep", category="QUICKREGRESSION", methodType="POST")
	public void postFlightGoToGate() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("flight_status_actual", "flight_status_estimated")
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("flight_number", String.valueOf(RandomDataUtils.getID())
				.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			    .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate()))
				.replace("LN", "GG")
				.replace("Landed", "Go To Gate"));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
	}
	
	@Test(description="Stand Update")
	@FrameworkAnnotations(author="Mandeep", category="QUICKREGRESSION", methodType="POST")
	public void postFlightStandUpdate() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("flight_status_actual", "stand_updated")
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("flight_number", String.valueOf(RandomDataUtils.getID())
				.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			    .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate()))
				.replace("LN", "SC")
				.replace("Landed", "Scheduled"));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
	}
	
	@Test(description="Flight Departed - In Air")
	@FrameworkAnnotations(author="Mandeep", category="REGRESSION", methodType="POST")
	public void postFlightInAir() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("flight_status_actual", "flight_status_actual")
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("flight_number", String.valueOf(RandomDataUtils.getID())
				.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			    .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate()))
				.replace("LN", "IA")
				.replace("Landed", "In Air"));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
	}
	
	@Test(description="Flight Linked")
	@FrameworkAnnotations(author="Mandeep", category="REGRESSION", methodType="POST")
	public void postFlightLinked() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("flight_status_actual", "flight_linked")
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("flight_number", String.valueOf(RandomDataUtils.getID())
				.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			    .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate()))
				.replace("LN", "SC")
				.replace("Landed", "Scheduled"));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
	}
	
	@Test(description="Aircraft Updated")
	@FrameworkAnnotations(author="Mandeep", category="REGRESSION", methodType="POST")
	public void postFlightAircraftUpdated() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("flight_status_actual", "aircraft_updated")
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("flight_number", String.valueOf(RandomDataUtils.getID())
				.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			    .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate()))
				.replace("LN", "SC")
				.replace("Landed", "Scheduled"));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
	}
	
	@Test(description="AircraftType Updated")
	@FrameworkAnnotations(author="Mandeep", category="REGRESSION", methodType="POST")
	public void postFlightAircraftTypeUpdated() throws IOException {
		String request = ApiUtils.readFromExternalFile(FrameworkConstants.requestjsonfolderpath+"request.json")
				.replace("23f91b2b-eb1d-409f-a564-678950910207", String.valueOf(RandomDataUtils.getID()))
				.replace("flight_status_actual", "aircraftType_updated")
				.replace("CGBHN", RandomDataUtils.getLastName())
				.replace("flight_number", String.valueOf(RandomDataUtils.getID())
				.replace("2022-02-01T08:42:00-0500", LocalDateTime.now().toString())
			    .replace("2022-02-01T08:45:00-0500", LocalDateTime.now().minusMinutes(60).toString())
				.replace("44", String.valueOf(RandomDataUtils.getStand()))
				.replace("15B", String.valueOf(RandomDataUtils.getGate()))
				.replace("LN", "SC")
				.replace("Landed", "Scheduled"));

		RequestSpecification reqspecification =RequestBuilder.buildRequestForPostCalls()
				.body(request);
		ExtentLogger.logRequest(reqspecification);
		Response response = reqspecification
				.post("/posts");
		ExtentLogger.logResponse(response.asPrettyString());
		//response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
		ApiUtils.saveReturnedPostResponse(FrameworkConstants.responsejsonfolderpath+"response.json", response);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(201);
	}
}
