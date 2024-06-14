package org.stockwell.browser;


import org.openqa.selenium.WebDriver;
import org.stockwell.reportsetup.ExtFactory;
import org.testng.Assert;
import com.aventstack.extentreports.Status;

public class Browser extends Factory {
	
	public void launch(String driver, String browser) {
		try {
			setDriver(driver,browser);
			if (ExtFactory.getInstance().getExtent() != null)
				ExtFactory.getInstance().getExtent().log(Status.INFO, "[" + browser + " ]launched the browser");
		} catch (Exception exc) {
			Assert.fail(exc.toString());
		}
	}

	public void close() {
		try {
			WebDriver browser = getDriver();
			browser.quit();
			if (ExtFactory.getInstance().getExtent() != null)
				ExtFactory.getInstance().getExtent().log(Status.INFO, "closed the browser");
		} catch (Exception exc) {
			Assert.fail(exc.toString());
		}
	}

	public void navigateURL(String url) {
		try {
			getDriver().get(url);
			getDriver().manage().window().maximize();
			//getDriver().manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
			if (ExtFactory.getInstance().getExtent() != null)
				ExtFactory.getInstance().getExtent().log(Status.INFO, "navigated to url [" + url + " ]");
		} catch (Exception exc) {
			Assert.fail(exc.toString());
		}
	}
	

}