package aero.sita.digitaltwins.reports;

import com.aventstack.extentreports.ExtentTest;

/**
 * Class to implement ThreadLocal to handle multi threading cases.
 * Mar 14, 2022
 * @author Mandeep Sheoran
 * @version 1.0
 * @since 1.0
 * @see ThreadLocal
 */
public final class ExtentManager {

	private ExtentManager() {};
	private static ThreadLocal<ExtentTest> extenttest = new ThreadLocal<>();

	/**
	 * Setter method for Extent Report using ThreadLocal.
	 * @author Mandeep Sheoran
	 */
	static ExtentTest getExtenttest() {
		return extenttest.get();
	}

	/**
	 * Getter method for Extent Report using ThreadLocal.
	 * @author Mandeep Sheoran
	 */
	static void setExtent(ExtentTest extest) {
		extenttest.set(extest);
	}
}
