package org.stockwell.ui;

import java.util.List;

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
	
	public static void assertEquals(List<String> actual, List<String> expected) {
		try {
			Assert.assertEquals(actual, expected);
			UTReportFactory.getInstance().getExtent().log(Status.INFO,
					"AssertEqual: [" + actual + "] and [" + expected + "]");
		} catch (AssertionError exc) {
			TestBase.captureScreenshot(exc.toString());
		}
	}
}