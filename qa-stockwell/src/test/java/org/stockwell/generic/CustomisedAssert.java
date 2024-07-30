package org.stockwell.generic;

import org.stockwell.reportsetup.ExtFactory;
import org.stockwell.tests.TestInfra;
import org.testng.Assert;
import com.aventstack.extentreports.Status;

public class CustomisedAssert {

	public static void assertTrue(boolean condition) {
		try 
		{
			Assert.assertTrue(condition);
			ExtFactory.getInstance().getExtent().log(Status.INFO, "AssertTrue:["+condition+"]");
		}
		catch(AssertionError exc){
			TestInfra.failWithScreenShot(exc.toString());
		}
	}

	// public static void fail(String message) {
	// 	try {
	// 		Assert.fail(message);
	// 		ExtFactory.getInstance().getExtent().log(Status.INFO, "Failed the test case : [" + message + "]");
	// 	} catch (AssertionError exc) {
	// 		TestInfra.failWithScreenShot(exc.toString());
	// 	}
	// }
}
