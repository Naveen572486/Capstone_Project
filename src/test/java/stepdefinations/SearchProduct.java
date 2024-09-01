package stepdefinations;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import org.openqa.selenium.By;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.cartPage;
import pages.homePage;
import pages.productPage;
import pages.searchResultsPage;
import utils.base;
import utils.screenshot;

public class SearchProduct extends base {

	homePage homePage;
	searchResultsPage SearchResultsPage;
	cartPage cartPage;
	productPage productPage;
	ExtentReports extentReport = Hooks.extentReport;
	ExtentTest extentTest = Hooks.extentTest;

	@Given("User is on Flipkart Home Pages")
	public void user_is_on_flipkart_home_page() {
		openBrowser();
		extentTest = extentReport.createTest("Search Product", "Opening Flipkart Page");

		boolean testStatus = verifyTitle();
		if (testStatus)
			extentTest.pass("Launching Flipkart is success");
		else {
			String screenShot = screenshot.captureScreenshotBase64(driver, "Flipkart_Launch_Failure");
			extentTest.fail("Failure on launching flipkart",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
		}
	}

	@When("User searches for {string}")
	public void user_searches_for_product(String productName) {
		try {
			homePage = new homePage(driver);
			homePage.searchProduct(productName);
			extentTest.pass("Enterd the Product Name in Search field and Clicked Enter.");
		}catch(Exception e){
			String screenShot = screenshot.captureScreenshotBase64(driver, "Unable to search");
			extentTest.fail("Unable to Search.",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
			e.printStackTrace();
		}
	
	}

	@Then("Search results are displayed")
	public void search_results_are_displayed() {
		String pageTitle = driver.getTitle();
		if (pageTitle.contains("Laptop")) {
			extentTest.pass("Search results are displayed.");

		} else {
			String screenShot = screenshot.captureScreenshotBase64(driver, "Flipkart_Search_results_are_not_displayed.");
			extentTest.fail("Flipkart_Search_results_are_not_displayed.",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
		}
	}

	@When("User selects the first product from the search results")
	public void user_selects_the_first_product_from_the_search_results() {
		try {
		SearchResultsPage = new searchResultsPage(driver);
		SearchResultsPage.selectFirstProduct();
		productPage = new productPage(driver);
		extentTest.pass("clicked on first product.");
		}
		catch(Exception e) {
			String screenShot = screenshot.captureScreenshotBase64(driver, "Clicking on first product failed");
			extentTest.fail("Clicking on first product failed.",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
			e.printStackTrace();
			
		}
	}
	
	
}
