package aero.sita.digitaltwins.utils;

import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.response.Response;
import lombok.SneakyThrows;

/**
 * Class to provide untility methods to handle external file integration.
 * Mar 14, 2022
 * @author Mandeep Sheoran
 * @version 1.0
 * @since 1.0
 * @see Files
 */
public final class ApiUtils {
	
	private ApiUtils() {};	
	/**
	 * Method to provide implementation of reading content from external files.
	 * @author Mandeep Sheoran
	 */
	@SneakyThrows
	public static String readFromExternalFile(String filepath) {
		return new String(Files.readAllBytes(Paths.get(filepath)));
	}
	
	/**
	 * Method to provide implementation for storing response in external files.
	 * @author Mandeep Sheoran
	 */
	@SneakyThrows
	public static void saveReturnedPostResponse(String filepath, Response response) {
		Files.write(Paths.get(filepath), response.asByteArray());
	}
}
