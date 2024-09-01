package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Reporter {
    private static ExtentReports extentReport;

    public static ExtentReports generateExtentReport(String reportName) {
        extentReport = new ExtentReports();
 
        // Get the current timestamp for the report name
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        TimeZone istTimeZone = TimeZone.getTimeZone("Asia/Kolkata"); // IST timezone
        dateFormat.setTimeZone(istTimeZone);
        String timestamp = dateFormat.format(new Date());

        String reportFolder = propertyReader.loadProperties().getProperty("ExtentReportPath");
        
        String reportFilePath = reportFolder  + reportName + "_" + timestamp + ".html";
        System.out.println("*********** Extent Report: " + reportFilePath);

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);

        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setReportName("Flipkart Test Execution Status Report");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

        extentReport.attachReporter(sparkReporter);

        return extentReport;
    }

    
}

