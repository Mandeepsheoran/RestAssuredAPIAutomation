package aero.sita.digitaltwins.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import aero.sita.digitaltwins.annotations.FrameworkAnnotations;
import aero.sita.digitaltwins.elk.SendResultToElastic;
import aero.sita.digitaltwins.enums.CategoryType;
import aero.sita.digitaltwins.reports.ExtentLogger;
import aero.sita.digitaltwins.reports.ExtentReport;

/**
 * Testng Listener class to derive the status of test execution as well as inititae/flush extent report.
 * Mar 14, 2022
 * @author Mandeep Sheoran
 * @version 1.0
 * @since 1.0
 * @see ExtentReport
 * @see ExtentLogger
 */
public class TestListener implements ITestListener, ISuiteListener {	
	/**
	 * Method to create Extent report object to initialize the report creation.
	 * @author Mandeep Sheoran
	 * @see ExtentReport
	 */
	@Override
	public void onStart(ISuite suite) {
		ExtentReport.initReport();
	}

	/**
	 * Method to execute Extent Report flush operation to create the report.
	 * @author Mandeep Sheoran
	 * @see ExtentReport
	 */
	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.tearDownReport();
	}

	/**
	 * Create Extent report to log the data in Extent HTML report and pass the test case description.
	 * @author Mandeep Sheoran
	 * @see ExtentReport
	 */
	@Override
	public  void onTestStart(ITestResult result) {
		String description =result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class).description();
		ExtentReport.createTest(description);
		ExtentReport.createTest(result.getName());
	}

	/**
	 * Log the data in Extent Report for passed test and pass the annotated values from test case.
	 * @author Mandeep Sheoran
	 * @see ExtentReport
	 * @see SendResultToElastic
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentLogger.pass(result.getName()+ " is passed");
		String[] authors =result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class).author();
		ExtentReport.addAuthors(authors);
		String[] categories =result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class).category();
		ExtentReport.addCategory(categories);
		String method =result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class).methodType();
		ExtentReport.addMethod(method);
		SendResultToElastic.sendstatustoelastic(result.getMethod().getDescription(), "PASS");
	}

	/**
	 * Log the data in Extent Report for failed test and pass the annotated values from test case.
	 * @author Mandeep Sheoran
	 * @see ExtentReport
	 * @see SendResultToElastic
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail(String.valueOf(result.getThrowable()));
		String[] authors =result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class).author();
		ExtentReport.addAuthors(authors);
		String[] categories =result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class).category();
		ExtentReport.addCategory(categories);
		String method =result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotations.class).methodType();
		ExtentReport.addMethod(method);
		SendResultToElastic.sendstatustoelastic(result.getMethod().getDescription(), "FAIL");
	}
}
