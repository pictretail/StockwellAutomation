package org.stockwell.tests;

import org.stockwell.generic.CustomisedAssert;
import org.stockwell.keys.Configuration;
import org.stockwell.keys.FilePath;
import org.stockwell.pages.Login;
import org.stockwell.ui.UTBase;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.stockwell.reportsetup.UTListeners.class)
public class LoginTest extends TestBase {
	private UTBase foundation = new UTBase();
	
	@Test(description = "C246383 - Login to the application with valid credentials")
	public void verifyLoginPageAsSuperUser() {
		try {
			//Valid login verification
			browser.navigateURL(
					propertyFile.readPropertyFile(Configuration.CURRENT_URL, FilePath.PROPERTY_CONFIG_FILE));
				login.login(
					propertyFile.readPropertyFile(Configuration.SUPER_USER, FilePath.PROPERTY_CONFIG_FILE),
					propertyFile.readPropertyFile(Configuration.PASSWORD, FilePath.PROPERTY_CONFIG_FILE));
			foundation.waitforElement(Login.BTN_HAMBURGER, 5);
			CustomisedAssert.assertTrue(foundation.isDisplayed(Login.BTN_HAMBURGER));

		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
	}
}
