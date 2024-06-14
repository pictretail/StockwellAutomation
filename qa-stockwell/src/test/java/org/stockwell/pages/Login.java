package org.stockwell.pages;

import org.openqa.selenium.By;
import org.stockwell.browser.Factory;
import org.stockwell.keys.Constants;
import org.stockwell.tests.TestInfra;
import org.stockwell.ui.Foundation;
import org.stockwell.ui.TextBox;

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
