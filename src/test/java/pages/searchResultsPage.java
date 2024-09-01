package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.base;

public class searchResultsPage extends base{
	
	String devicename;
	
	public searchResultsPage(RemoteWebDriver driver) {
		 
	       this.driver = driver;
	    }

	 public void applyBrandFilter(String brand) {
	        WebElement brandFilter = driver.findElement(By.xpath("//*[text() = 'Brand']"));
	      click(brandFilter) ;
	    
	      WebElement brandname = driver.findElement(By.xpath("//*[text() = 'HP']"));
	      click(brandname);
	      
	    }

	 public boolean isFilteredByBrand(String brand) {
	        return driver.getTitle().contains(brand);
	    }
	 
	 public void selectFirstProduct() {
		
	        WebElement firstProduct = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div/div[2]/div[2]/div/div/div/a"));
	       click(firstProduct); 
	    }
// public void getDeviceName() {
//	 
//WebElement element = driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/h1/span"));
// devicename = element.getText();
//		 
//	 }
}

