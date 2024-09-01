package utils;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.junit.AfterClass;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class base {
	//public static RemoteWebDriver driver;
	public static RemoteWebDriver driver;
	public static FileInputStream file;
	public static Properties prop;
	public static WebDriverWait wait;
	public String userDir = System.getProperty("./config/confog.properties");

	public void openBrowser() {
		prop = propertyReader.loadProperties();
		String browserName = prop.getProperty("browser");

		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "edge":
			driver = new EdgeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		default:
			System.err.println("Unsupported browser: " + browserName);
			break;
		}

		if (driver != null)

		{
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			driver.get(prop.getProperty("url"));
		}

	}
	
	public boolean verifyTitle() {
		
		String expResult = "https://www.flipkart.com/";
		String actResult = driver.getCurrentUrl();
		
		return expResult.equals(actResult);
	}


	@AfterClass
	public void quit() {
		driver.quit();
		// https://www.flipkart.com/
	}

	public void click(WebElement ele) {

		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(ele));

			ele.click();

		} catch (StaleElementReferenceException e) {

			throw new RuntimeException();

		}
	}

	public void append(WebElement ele, String data) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
		ele.sendKeys(data);
	}

	public void switchToWindow(int index) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			List<String> allhandles = new ArrayList<String>(allWindows);
			String exWindow = allhandles.get(index);
			driver.switchTo().window(exWindow);
			System.out.println("The Window With index: " + index + " switched successfully");
		} catch (NoSuchWindowException e) {
			System.err.println("The Window With index: " + index + " not found");
		}
	}
}
