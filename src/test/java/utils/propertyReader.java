package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class propertyReader {

	public static FileInputStream file;
	static Properties properties;
	static String propertiesPath = "./config/config.properties";

	public static Properties loadProperties() {
		try {
			file = new FileInputStream(propertiesPath);
			properties = new Properties();
			properties.load(file);
		} catch (FileNotFoundException e) {
			System.out.println("File path or file name is not correct");
		} catch (IOException e) {
			System.out.println("Input output Exception");
		}
		return properties;
	}
}
