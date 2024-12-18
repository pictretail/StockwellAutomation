package org.stockwell.reportsetup;

import com.aventstack.extentreports.ExtentTest;

public class UTReportFactory {
	private UTReportFactory() {

	}

	private static UTReportFactory instance = new UTReportFactory();

	ThreadLocal<ExtentTest> extent = new ThreadLocal<>();
	
	public void setExtent(ExtentTest extentTestObject) {
		extent.set(extentTestObject);
	}

	public ExtentTest getExtent() {
		return extent.get();
	}

	public static UTReportFactory getInstance() {
		return instance;
	}
	
	public void removeExtentObject() {
		extent.remove();
	}
}
