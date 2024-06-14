package at.smartshop.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
//import at.framework.ui.Foundation;
import at.smartshop.keys.Configuration;
import at.smartshop.keys.FilePath;

@Listeners(at.framework.reportsetup.Listeners.class)
public class LoginTest extends TestInfra {
	//private Foundation foundation = new Foundation();
	
	@Test
	public void verifyLoginPageAsSuperUser() {
		
		try {
			// Launch the browser & Signin 
			browser.navigateURL(
					propertyFile.readPropertyFile(Configuration.CURRENT_URL, FilePath.PROPERTY_CONFIG_FILE));
			login.login(
					propertyFile.readPropertyFile(Configuration.SUPER_USER, FilePath.PROPERTY_CONFIG_FILE),
					propertyFile.readPropertyFile(Configuration.PASSWORD, FilePath.PROPERTY_CONFIG_FILE));
			

		} catch (Exception exc) {
			TestInfra.failWithScreenShot(exc.toString());
		}
	}
}
