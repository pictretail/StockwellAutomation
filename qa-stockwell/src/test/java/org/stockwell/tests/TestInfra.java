package org.stockwell.tests;



import org.stockwell.browser.Browser;
import org.stockwell.browser.Factory;
import org.stockwell.files.PropertyFile;
import org.stockwell.keys.Constants;
import org.stockwell.keys.FilePath;
import org.stockwell.pages.Login;
import org.stockwell.reportsetup.ExtFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
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

	public static String updateTestRail = "";



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
			String sysPath = FilePath.FILE + HOST + screenshot.split(Constants.DELIMITER_COLON)[1];
			ExtFactory.getInstance().getExtent().addScreenCaptureFromPath(sysPath);
			ExtFactory.getInstance().getExtent().log(Status.FAIL, "Failed due to " + linesofExc[0]);
			Assert.fail(exc);
		} catch (Exception e) {
			Assert.fail("Failed due to " + exc.toString() + " could not capture the screenshot due to " + e);
		}
	}
}
