package org.stockwell.ui;

import org.stockwell.reportsetup.UTReportFactory;
import org.stockwell.tests.TestBase;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class UTAssert {

	public static void assertTrue(boolean condition) {
		try {
			Assert.assertTrue(condition);
			UTReportFactory.getInstance().getExtent().log(Status.INFO, "AssertTrue: [" + condition + "]");
		} catch (AssertionError exc) {
			TestBase.captureScreenshot(exc.toString());
		}
	}

	public static void assertFalse(boolean condition) {
		try {
			Assert.assertFalse(condition);
			UTReportFactory.getInstance().getExtent().log(Status.INFO, "AssertFalse: [" + condition + "]");
		} catch (AssertionError exc) {
			TestBase.captureScreenshot(exc.toString());
		}
	}
}