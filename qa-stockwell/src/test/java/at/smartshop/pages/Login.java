package at.smartshop.pages;

import org.openqa.selenium.By;
import at.framework.browser.Factory;
import at.framework.ui.Foundation;
import at.framework.ui.TextBox;
import at.smartshop.keys.Constants;
import at.smartshop.tests.TestInfra;

public class Login extends Factory {
		private TextBox textBox = new TextBox();
		private Foundation foundation = new Foundation();
		
		
	
		public static final By TXT_EMAIL = By.xpath("//input[@type='text']");
		public static final By TXT_PASSWORD = By.xpath("//input[@type='password']");
		public static final By BTN_SIGN_IN = By.xpath("//span[text()='Sign In']");
		
		
		public void login(String userName, String password) {
			try {
				
				foundation.waitforElement(TXT_EMAIL, Constants.THREE_SECOND);
				textBox.enterText(TXT_EMAIL, userName);
				//foundation.click(BTN_SIGN_IN);
				foundation.waitforElement(TXT_PASSWORD, Constants.THREE_SECOND);
				textBox.enterText(TXT_PASSWORD, password);
				foundation.click(BTN_SIGN_IN);
			} catch (Exception exc) {
				TestInfra.failWithScreenShot(exc.toString());
			}
		}
		
		
}
