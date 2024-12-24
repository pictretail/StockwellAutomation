package org.stockwell.tests;

import org.stockwell.keys.Configuration;
import org.stockwell.keys.FilePath;
import org.stockwell.pages.Login;
import org.stockwell.ui.UTAssert;
import org.stockwell.ui.UTBase;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.stockwell.reportsetup.UTListeners.class)
public class LoginTest extends TestBase {
	private UTBase base = new UTBase();
	
	@Test(description = "C246383 - Login to the application with valid credentials")
	public void verifyLoginPageAsSuperUser() {
		try {
			//Valid login verification
			browser.navigateURL(
					propertyFile.readPropertyFile(Configuration.CURRENT_URL, FilePath.PROPERTY_CONFIG_FILE));
				login.login(
					propertyFile.readPropertyFile(Configuration.SUPER_USER, FilePath.PROPERTY_CONFIG_FILE),
					propertyFile.readPropertyFile(Configuration.PASSWORD, FilePath.PROPERTY_CONFIG_FILE));
				base.waitforElement(Login.BTN_HAMBURGER, 5);
			//UTAssert.assertTrue(base.getTitle().contains("dashboard"));
				Thread.sleep(2000);
				UTAssert.assertTrue(base.isDisplayed(Login.BTN_HAMBURGER));

		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
	}
	
	@Test
	public void testExcel()
	{
		System.out.println(excel.getCellData("C:\\Projects\\Stockwell\\StockwellAutomation\\qa-stockwell\\src\\test\\resources\\TestData.xlsx", "Sheet1", 1,1));
		
	}
}
