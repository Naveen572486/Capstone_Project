package stepdefinations;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.homePage;
import pages.searchResultsPage;
import utils.base;
import utils.screenshot;

public class Filter extends base {
    searchResultsPage SearchResultsPage;
    homePage homePage;
    ExtentReports extentReport = Hooks.extentReport;
	ExtentTest extentTest = Hooks.extentTest;

    @Given("User is on Flipkart Home Page")
    
    public void user_is_on_flipkart_home_page() {
    	extentTest = extentReport.createTest("Filter Product", "User is filtering a product");
        openBrowser();

        boolean testStatus = verifyTitle();
		if (testStatus)
			extentTest.pass("Enterd the Product Name in Search field and Clicked Enter.");
		else {
			String screenShot = screenshot.captureScreenshotBase64(driver, "Unable to search");
			extentTest.fail("Unable to search",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
		}   
        
    }

    @When("User searching for {string}")
    public void user_searches_for_product(String productName) {
    	try {
        homePage = new homePage(driver);
        homePage.searchProduct(productName);
        extentTest.pass("product is filtered successfully");
    	}catch(Exception e) {
    		String screenShot = screenshot.captureScreenshotBase64(driver, "Filtering_Failure");
			extentTest.fail("Failure on Filtering a product",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
    		e.printStackTrace();
    	}
    }

    @And("User applies brand filter as {string}")
    public void user_applies_brand_filter_as(String brand) {
    	try {
        SearchResultsPage = new searchResultsPage(driver);
        SearchResultsPage.applyBrandFilter(brand);
        extentTest.pass("product is filtered successfully by brand");
    	}catch(Exception e) {
    		String screenShot = screenshot.captureScreenshotBase64(driver, "product is filtered failed by brand");
			extentTest.fail("Filtering_Failure_HP",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
    		e.printStackTrace();
    	}
    }

    @Then("Filtered results by brand are displayed")
    public void filtered_results_by_brand_are_displayed() {
        boolean isFilteredByBrand = SearchResultsPage.isFilteredByBrand("HP");
        if (isFilteredByBrand) {
            System.out.println("Results are filtered by the brand: HP");
            extentTest.pass("HP Results are Displayed");
        } else {
            System.out.println("Brand filter not applied correctly.");
            String screenShot = screenshot.captureScreenshotBase64(driver, "HP Results are Failed");
			extentTest.fail(" HP Results are Failed",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
    		

        }
    }
}
