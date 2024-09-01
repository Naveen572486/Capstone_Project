package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.base;

public class homePage extends base {

	  public homePage(RemoteWebDriver driver) {
	       this.driver = driver;
	    }
		By search = By.name("q");

	 public boolean searchProduct(String productName) {
		 WebElement searchBox = driver.findElement(search);	        
		 append(searchBox, productName);
		searchBox.sendKeys(Keys.ENTER);
	        
		 boolean searchResult = true;
		  try {
			  wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("BUOuZu")));
		  }catch(TimeoutException te) {
			  searchResult = false;
			  utils.screenshot.captureScreenshotBase64(driver, "Search Result Failure");
			   
		  }
		  
		  return searchResult;
		  }
	}




