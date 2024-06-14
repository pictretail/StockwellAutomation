package at.framework.generic;

import org.testng.Assert;
import com.aventstack.extentreports.Status;
import at.framework.reportsetup.ExtFactory;
import at.smartshop.tests.TestInfra;
public class CustomisedAssert {


	public static void fail(String message) {
		try {
			Assert.fail(message);
			ExtFactory.getInstance().getExtent().log(Status.INFO, "Failed the test case : [" + message + "]");
		} catch (AssertionError exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
	}
}
