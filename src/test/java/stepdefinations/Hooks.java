package stepdefinations;

import javax.naming.ldap.ExtendedRequest;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import utils.Reporter;

public class Hooks {

	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extentReport;
	public static ExtentTest extentTest;
	WebDriver driver;
	
	
	@BeforeAll
	public static void setUp() {
		extentReport = Reporter.generateExtentReport("Flipkart_Test_Execution_Report.html");
	}
	
	@AfterAll
	public static void tearDown() {
		extentReport.flush();
	}
	
}

