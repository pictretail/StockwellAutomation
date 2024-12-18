package org.stockwell.tests;


import java.io.File;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.stockwell.browser.Browser;
import org.stockwell.browser.WebDriverFactory;
import org.stockwell.keys.FilePath;
import org.stockwell.pages.Login;
import org.stockwell.reportsetup.UTReportFactory;
import org.stockwell.utilities.PropertyFile;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.Status;


@Listeners(org.stockwell.reportsetup.UTListeners.class)
public class TestBase {
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

	@Parameters( "browser")
	@BeforeMethod
	public void beforeMethod(String browsers) {
		try {
			browser.launch(browsers);
		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
	}

    @Parameters({ "environment", "UpdateTestRail" })
	@BeforeSuite
	public void beforeSuit(String environment, String testRail) {
		try {
			filePath.setEnvironment(environment);
			updateTestRail = testRail;
			HOST = InetAddress.getLocalHost().getHostName();
		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
	}

	@AfterMethod
	public void afterMethod() {
		try {
			browser.close();
		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
	}

	public static void captureScreenshot(String exc) {
		try {
			String linesofExc[] = exc.split("\\r?\\n");
			THROWABLE_EXCEPTION = linesofExc[0];
			String screenshot = org.stockwell.reportsetup.UTListeners.objReportName.getScreenshot(WebDriverFactory.getDriver());
			//String sysPath = FilePath.FILE + HOST + screenshot.split(Constants.DELIMITER_COLON)[1];
			//String sysPath = FilePath.FILE + HOST + screenshot;
			UTReportFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshot);
			UTReportFactory.getInstance().getExtent().log(Status.FAIL, "Failed due to "+THROWABLE_EXCEPTION);
			Assert.fail(exc);
		} catch (Exception e) {
			Assert.fail("Failed due to " + exc.toString() + " could not capture the screenshot due to " + e);
		}
	}

	public static void capturePassedScreenshot() {
		try {
			String screenshot = org.stockwell.reportsetup.UTListeners.objReportName.getScreenshot(WebDriverFactory.getDriver());
			UTReportFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshot);
		} catch (AssertionError exc) {
			TestBase.captureScreenshot(exc.toString());
		}
	}
	
	public static String captureScreenShotAsBase64() {
	        String base64String = "";
	        try {
	            TakesScreenshot screenshot = (TakesScreenshot)(WebDriverFactory.getDriver());
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
