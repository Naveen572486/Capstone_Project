package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenshot {

	public static String captureScreenshotBase64(WebDriver driver, String errorName) {

		byte[] imageByte = null;
		try {
			TakesScreenshot scrShot = (TakesScreenshot) driver;
			File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
			String screenshotFolder = propertyReader.loadProperties().getProperty("ScreenshotPath");
			System.out.println("*********** Screenshot: " + screenshotFolder);

			String scrnShotName = screenshotFolder + errorName + ".png";
			File destFile = new File(scrnShotName);

			FileUtils.copyFile(srcFile, destFile);
			imageByte = IOUtils.toByteArray(new FileInputStream(scrnShotName));

		} catch (FileNotFoundException fnfe) {
			System.out.println("File path or file name is not corrrect");
		} catch (IOException e) {
			System.out.println("Input output Exceiption");
		}

		return Base64.getEncoder().encodeToString(imageByte);

	}

}
