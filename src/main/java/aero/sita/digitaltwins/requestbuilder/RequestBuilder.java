package aero.sita.digitaltwins.requestbuilder;

import static io.restassured.RestAssured.given;

import aero.sita.digitaltwins.enums.PropertiesType;
import aero.sita.digitaltwins.utils.PropertyUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

/**
 * Reusable to method to construct the request/response for Restassured call to the server.
 * Mar 14, 2022
 * @author Mandeep Sheoran
 * @version 1.0
 * @since 1.0
 * @see PropertiesType
 * @see PropertyUtils
 */
public class RequestBuilder { 

	private RequestBuilder() {};
	/**
	 * Reusable to method to construct the request for GET calls.
	 * @author Mandeep Sheoran
	 * @see PropertiesType
	 * @see PropertyUtils
	 */
	public static RequestSpecification buildRequestForGetCalls() {
		RestAssured.useRelaxedHTTPSValidation();
		return given()
				.baseUri(PropertyUtils.getValue((String.valueOf(PropertiesType.BASEURL)).toLowerCase()))
				.log()
				.all(); 
	}

	/**
	 * Reusable to method to construct the request for POST calls.
	 * @author Mandeep Sheoran
	 * @see PropertiesType
	 * @see PropertyUtils
	 */
	public static RequestSpecification buildRequestForPostCalls() {
		RestAssured.useRelaxedHTTPSValidation();
		return given()
				.baseUri(PropertyUtils.getValue((String.valueOf(PropertiesType.BASEURL)).toLowerCase()))
				.contentType(ContentType.JSON)
				.log()
				.all();
	}
}
