package org.stockwell.generic;

import org.stockwell.reportsetup.ExtFactory;
import org.stockwell.tests.TestInfra;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
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
