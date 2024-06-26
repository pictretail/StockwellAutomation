package org.stockwell.reportsetup;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.stockwell.generic.DateAndTime;
import org.stockwell.keys.Constants;
import org.stockwell.keys.FilePath;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtReport {

	public String rootFolder = FilePath.PATH + Constants.REPORTS;
	private String presentSubFolderName = Constants.EMPTY_STRING;
	private String presentRootFolderPath;
	private DateAndTime objDate;

	public static String reportFullPath;

	ExtentSparkReporter objSparkReporter;
	ExtentReports objExtentReport;
	ExtentTest objExtentTest;

	private String reportMainTitleName = Constants.EMPTY_STRING;
	private String reportBrowserTitleName = Constants.EMPTY_STRING;

	public ExtReport(String reportMainTitleName, String reportBrowserTitleName) {
		objDate = new DateAndTime();
		Path reportFolder = Paths.get(this.rootFolder);
		Path path = Paths
				.get(this.rootFolder + objDate.getDateAndTime(Constants.REGEX_DDMMYYYY, Constants.TIME_ZONE_INDIA));
		try {
			if (!Files.exists(reportFolder)) {
				Files.createDirectory(reportFolder);
			}
			if (!Files.exists(path)) {
				Files.createDirectory(path);
			}
			this.presentRootFolderPath = path.toString();
			this.reportMainTitleName = reportMainTitleName;
			this.reportBrowserTitleName = reportBrowserTitleName;
		} catch (IOException exc) {
			exc.printStackTrace();
			Assert.fail(exc.toString());
		}

	}

	public ExtentReports getReporter() {
		try {
			reportFullPath = createReportSubFolder() + Constants.REPORT_NAME;
			objSparkReporter = new ExtentSparkReporter(reportFullPath);
			objSparkReporter.config().setReportName(this.reportMainTitleName);
			objSparkReporter.config().setDocumentTitle(this.reportBrowserTitleName);
			objExtentReport = new ExtentReports();
			objExtentReport.attachReporter(objSparkReporter);
		} catch (Exception exc) {
			exc.printStackTrace();
			Assert.fail(exc.toString());
		}

		return objExtentReport;
	}

	public String getPresentRootFolderPath() {
		return presentRootFolderPath;
	}

	public String getPresentSubFolderPath() {
		return presentSubFolderName;
	}

	private String createReportSubFolder() {
		presentSubFolderName = presentRootFolderPath + "\\"
				+ objDate.getDateAndTime(Constants.REGEX_HHMMSS, Constants.TIME_ZONE_INDIA);
		try {
			Files.createDirectories(Paths.get(presentSubFolderName));
		} catch (IOException exc) {
			Assert.fail(exc.toString());
		}
		return presentSubFolderName;
	}

	public String getScreenshot(WebDriver driver) {
		String timeStamp = objDate.getDateAndTime(Constants.TIME_STAMP, Constants.TIME_ZONE_INDIA);
		String destinationFile = null;
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			destinationFile = getPresentRootFolderPath() + "\\" + timeStamp + ".png";
			FileUtils.copyFile(source, new File(destinationFile));
		} catch (Exception exc) {
			Assert.fail(exc.toString());
		}
		return destinationFile;
	}
}
