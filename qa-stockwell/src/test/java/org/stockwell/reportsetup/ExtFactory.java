package org.stockwell.reportsetup;

import com.aventstack.extentreports.ExtentTest;

public class ExtFactory {
	private ExtFactory() {

	}

	private static ExtFactory instance = new ExtFactory();

	ThreadLocal<ExtentTest> extent = new ThreadLocal<>();

	
	public void setExtent(ExtentTest extentTestObject) {
		extent.set(extentTestObject);
	}
	public ExtentTest getExtent() {
		return extent.get();
	}
	public static ExtFactory getInstance() {
		return instance;
	}
	public void removeExtentObject() {
		extent.remove();
	}
}
