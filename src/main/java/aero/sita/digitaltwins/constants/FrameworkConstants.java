package aero.sita.digitaltwins.constants;

import lombok.Getter;

/**
 * Class to store all resource path required to GET/POST data.
 * Mar 14, 2022
 * @author Mandeep Sheoran
 * @version 1.0
 * @since 1.0
 */
public class FrameworkConstants {
	public static @Getter final String requestjsonfolderpath = System.getProperty("user.dir")+"/src/test/resources/json/";
	public static @Getter final String responsejsonfolderpath = System.getProperty("user.dir")+"/JsonOutput/";
	public static final String  configFilePath = System.getProperty("user.dir")+"/src/test/resources/config.properties";	
}
