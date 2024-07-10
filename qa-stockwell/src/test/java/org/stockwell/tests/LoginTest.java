package org.stockwell.tests;

import org.stockwell.generic.CustomisedAssert;
import org.stockwell.keys.Configuration;
import org.stockwell.keys.FilePath;
import org.stockwell.pages.Login;
import org.stockwell.ui.Foundation;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.stockwell.reportsetup.Listeners.class)
public class LoginTest extends TestInfra {
	private Foundation foundation = new Foundation();
	
	@Test
	public void verifyLoginPageAsSuperUser() {
		
		try {
			// Launch the browser & Signin 
			browser.navigateURL(
					propertyFile.readPropertyFile(Configuration.CURRENT_URL, FilePath.PROPERTY_CONFIG_FILE));
			login.login(
					propertyFile.readPropertyFile(Configuration.SUPER_USER, FilePath.PROPERTY_CONFIG_FILE),
					propertyFile.readPropertyFile(Configuration.PASSWORD, FilePath.PROPERTY_CONFIG_FILE));
			foundation.waitforElement(Login.BTN_HAMBURGER, 5);
			CustomisedAssert.assertTrue(foundation.isDisplayed(Login.BTN_HAMBURGER));
			
			
			

		} catch (Exception exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
	}
}
