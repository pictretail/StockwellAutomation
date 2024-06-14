
package at.framework.reportsetup;


import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import at.framework.browser.Factory;
import at.smartshop.keys.Constants;
import at.smartshop.keys.FilePath;
import at.smartshop.tests.TestInfra;

public class Listeners implements ITestListener {

	static ExtentReports objReport;
	public static ExtReport objReportName;
	ExtentTest test;
	
	public void onTestFailure(ITestResult result) {

		// get screenshot
		try {
			String screenshot = null;
			String linesofExc[] = result.getThrowable().toString().split("\\r?\\n");
			if (!linesofExc[0].contains(TestInfra.THROWABLE_EXCEPTION)) {
				ExtFactory.getInstance().getExtent().log(Status.FAIL, linesofExc[0]);
				screenshot = objReportName.getScreenshot(Factory.getDriver());
				String sysPath = FilePath.FILE + TestInfra.HOST + screenshot.split(Constants.DELIMITER_COLON)[1];
				ExtFactory.getInstance().getExtent().addScreenCaptureFromPath(sysPath);
			}
			ExtFactory.getInstance().getExtent().log(Status.FAIL, "Test Case is failed");
			ExtFactory.getInstance().removeExtentObject();

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		TestInfra.THROWABLE_EXCEPTION = "";
	}


}
