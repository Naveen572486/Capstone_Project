package stepdefinations;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.cartPage;
import pages.homePage;
import pages.productPage;
import pages.searchResultsPage;
import utils.base;
import utils.screenshot;

public class TotalProductsInACart extends base {
	
	homePage homePage;
	cartPage cartPage;
    productPage productPage;
    searchResultsPage SearchResultsPage;
    ExtentReports extentReport = Hooks.extentReport;
	ExtentTest extentTest = Hooks.extentTest;
    
 @Given("User is on flipkart home page")
    public void user_is_on_flipkart_home_page() {
	 extentTest = extentReport.createTest("Total Products", "Opening Flipkart Page");
        openBrowser();
        
		boolean testStatus = verifyTitle();
		if (testStatus)
			extentTest.pass("Launching Flipkart sucessfully");
		else {
			String screenShot = screenshot.captureScreenshotBase64(driver, "Flipkart_Launch_Failure");
			extentTest.fail("Failure on launching flipkart",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
		}
    }
 @When("User searchs for {string}")
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
	 @When("User select the first product from the search result")
	    public void user_selects_the_first_product_from_the_search_results() {
		 try {
	        SearchResultsPage = new searchResultsPage(driver);
	        SearchResultsPage.selectFirstProduct();
	        productPage = new productPage(driver);
	        extentTest.pass("Successfully selected the first product from the searchresults.");
		 }catch(Exception e) {
			 String screenShot = screenshot.captureScreenshotBase64(driver, "Flipkart_Launch_Failure");
				extentTest.fail("Failure on Selecting the first product from search results",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
			 e.printStackTrace();
		 }
	    }

	    @When("User adding the product to the cart")
	    public void user_adds_the_product_to_the_cart() {
	    	try {
	        productPage.addToCart();
	        extentTest.pass("Successfully Product is added to Cart.");
	    	}catch(Exception e) {
	    		String screenShot = screenshot.captureScreenshotBase64(driver, "Flipkart_AddTo_Cart_Failure");
				extentTest.fail("Failure on adding the product to th cart",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
	    		e.printStackTrace();
	    	}
	    }

	    @Then("Product should successfully added to the cart")
	    public void product_should_be_successfully_added_to_the_cart() {
	    	try {
	        cartPage = new cartPage(driver);
	        cartPage.viewCart();
	        cartPage.isProductInCart();
	        extentTest.pass("Successfully Product is added to Cart.");
	    	}catch(Exception e) {
	    		String screenShot = screenshot.captureScreenshotBase64(driver, "Flipkart_AddTo_Cart_Failure");
				extentTest.fail("Failure on adding the product to the cart",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
	    		e.printStackTrace();
	    	}
	    }

	    @When("User view the cart")
	    public void user_views_the_cart() {
	    	try {
	        cartPage = new cartPage(driver);
	        cartPage.viewCart();
	        extentTest.pass("Successfully viewed the cart.");
	    	}catch(Exception e) {
	    		String screenShot = screenshot.captureScreenshotBase64(driver, "Flipkart_viewCart_Failure");
				extentTest.fail("Failure on viewing the cart",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
	    		e.printStackTrace();
	    	}
	    }

	    @And("Cart will display the added product")
	    public void cart_should_display_the_added_product() {
	    	try {
	        boolean isProductInCart = cartPage.isProductInCart();
	        Assert.assertTrue(isProductInCart, "Cart does not display the added product.");
	        extentTest.pass("Successfully verified that the cart displays the added product.");
	    	}catch(Exception e) {
	    		String screenShot = screenshot.captureScreenshotBase64(driver, "Flipkart_VrifyProductInCart_Failure");
				extentTest.fail("Failure in verifying that the cart displays the added product",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
	    		e.printStackTrace();
	    	}
	    }
	    @Then("Display Number of Products in Cart")
	    public void how_many_products_are_there_in_a_cart() {
	    	try {
	    		cartPage.getNoOfItemsInCart();
	    		extentTest.pass("Total no of Items in the Cart are "+ cartPage.getNoOfItemsInCart());
	    	}catch(Exception e) {
	    		String screenShot = screenshot.captureScreenshotBase64(driver, "Flipkart_TotalProducts_Failure");
				extentTest.fail("Cart is Empty",
						MediaEntityBuilder.createScreenCaptureFromBase64String(screenShot).build());
	    	e.printStackTrace();
	    }
	    }
}
