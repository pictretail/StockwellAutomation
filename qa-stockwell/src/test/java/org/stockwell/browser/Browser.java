package org.stockwell.browser;

import org.stockwell.reportsetup.UTReportFactory;
import org.testng.Assert;
import com.aventstack.extentreports.Status;

public class Browser extends WebDriverFactory {
	
	public void launch(String browser) {
		try {
			setDriver(browser);
			if (UTReportFactory.getInstance().getExtent() != null)
				UTReportFactory.getInstance().getExtent().log(Status.INFO, "[" + browser + " ]launched the browser");
		} catch (Exception exc) {
			Assert.fail(exc.toString());
		}
	}

	public void close() {
		try {
			getDriver().quit();
			if (UTReportFactory.getInstance().getExtent() != null)
				UTReportFactory.getInstance().getExtent().log(Status.INFO, "closed the browser");
		} catch (Exception exc) {
			Assert.fail(exc.toString());
		}
	}

	public void navigateURL(String url) {
		try {
			getDriver().get(url);
			getDriver().manage().window().maximize();
			if (UTReportFactory.getInstance().getExtent() != null)
				UTReportFactory.getInstance().getExtent().log(Status.INFO, "navigated to url [" + url + " ]");
		} catch (Exception exc) {
			Assert.fail(exc.toString());
		}
	}
	

}