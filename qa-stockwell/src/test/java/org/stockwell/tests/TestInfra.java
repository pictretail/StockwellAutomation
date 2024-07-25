package org.stockwell.tests;



import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.stockwell.browser.Browser;
import org.stockwell.browser.Factory;
import org.stockwell.keys.Constants;
import org.stockwell.keys.FilePath;
import org.stockwell.pages.Login;
import org.stockwell.reportsetup.ExtFactory;
import org.stockwell.utilities.PropertyFile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.Status;


@Listeners(org.stockwell.reportsetup.Listeners.class)
public class TestInfra {
	public Browser browser = new Browser();
	public FilePath filePath = new FilePath();
	public Login login = new Login();
	public PropertyFile propertyFile = new PropertyFile();
	public static String HOST = "";
	public static String THROWABLE_EXCEPTION = "";
	public static boolean THROWED_EXCEPTION = false;
	public long startTimeMilli;
	public long startTimeSeconds;
	public static String updateTestRail = "";

	@BeforeClass(alwaysRun = true)
    public void beforeClass() {
        startTimeMilli = System.currentTimeMillis();
        startTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(startTimeMilli);
    }
 
    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.err.println("Duration: " + this.getClass().getName()
                + " - "+(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) - startTimeSeconds));
    }

	@Parameters({ "driver", "browser" })
	@BeforeMethod
	public void beforeMethod(String drivers, String browsers) {
		try {
			browser.launch(drivers, browsers);
		} catch (Exception exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
	}

	@AfterMethod
	public void afterMethod() {
		try {
			browser.close();
		} catch (Exception exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
	}

	public static void failWithScreenShot(String exc) {
		try {
			String linesofExc[] = exc.split("\\r?\\n");
			THROWABLE_EXCEPTION = linesofExc[0];
			String screenshot = org.stockwell.reportsetup.Listeners.objReportName.getScreenshot(Factory.getDriver());
			//String sysPath = FilePath.FILE + HOST + screenshot.split(Constants.DELIMITER_COLON)[1];
			//String sysPath = FilePath.FILE + HOST + screenshot;
			ExtFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshot);
			ExtFactory.getInstance().getExtent().log(Status.FAIL, "Failed due to "+THROWABLE_EXCEPTION);
			Assert.fail(exc);
		} catch (Exception e) {
			Assert.fail("Failed due to " + exc.toString() + " could not capture the screenshot due to " + e);
		}
	}

	public static void capturePassedScreenshot() {
		try {
			String screenshot = org.stockwell.reportsetup.Listeners.objReportName.getScreenshot(Factory.getDriver());
			//String sysPath = FilePath.FILE + HOST + screenshot.split(Constants.DELIMITER_COLON)[1];
			//ExtFactory.getInstance().getExtent().addScreenCaptureFromPath(sysPath);
			ExtFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshot);
		} catch (AssertionError exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
	}
	public static String captureScreenShotAsBase64() {
	        String base64String = "";
	        try {
	            TakesScreenshot screenshot = (TakesScreenshot)(Factory.getDriver());
	            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
 
	            // Convert the file to Base64
	            Path path = Paths.get(srcFile.getPath());
	            byte[] fileContent = Files.readAllBytes(path);
	            base64String = Base64.getEncoder().encodeToString(fileContent);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return base64String;
	    }
}
