package pages;

import java.awt.print.Printable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.base;

public class productPage extends base{
	
	public productPage(RemoteWebDriver driver) {
		 
	       this.driver = driver;
	    }
	 public void addToCart() {
		 switchToWindow(1);
		 By addToCartButtonLocator = By.xpath("//*[@class='QqFHMw cNEU5Q J9Kkbj _7Pd1Fp']");
	
			  WebElement addToCartButton = driver.findElement(addToCartButtonLocator);
			  WebElement element = driver.findElement(By.xpath("//*[@class = 'P5ntM6']"));		  
			  JavascriptExecutor js = (JavascriptExecutor) driver;
			  js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				js.executeScript("arguments[0].scrollIntoView(true);", element);
				int retries = 0;
				while (retries < 3) {
				    try {
				        
				    	js.executeScript("arguments[0].click();", addToCartButton); 
				        break;
				    } catch (StaleElementReferenceException e) {
				 
				    	addToCartButton = driver.findElement(addToCartButtonLocator);
				        retries++;
				    }
				    driver.navigate().back(); 
	
	    }
	
	 }
}

