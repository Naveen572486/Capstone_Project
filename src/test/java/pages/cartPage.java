package pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import utils.base;

public class cartPage extends base {

	public cartPage(RemoteWebDriver driver) {

		this.driver = driver;
	}

	searchResultsPage SearchResultsPage;

	public void viewCart() {
		WebElement cartButton = driver.findElement(By.linkText("Cart"));
		cartButton.click();
	}

	public boolean isProductInCart() {
		String title = driver.getTitle();
		System.out.println(title);
		if (title.contains("https://www.flipkart.com/viewcart")) {
			return true;
		} else {

			return false;
		}
	}

	public String getNoOfItemsInCart() {
		String text = driver.findElement(By.xpath("//*[@class = 'HRZecL']/div/div[1]")).getText();
		Pattern pattern = Pattern.compile("\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(text);
        String items = "";
        if (matcher.find()) {
            // Extract text from the first captured group
            items = matcher.group(1);

            // Print the result
            System.out.println("Text between parentheses: " + items);
        } else {
            System.out.println("No text found within parentheses.");
        }

        // Print the final result
        System.out.println("No Of Items In a Cart - " + items);

        // Return the extracted text
        return items;
	
	}

}
