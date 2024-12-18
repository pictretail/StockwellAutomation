package org.stockwell.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.stockwell.browser.WebDriverFactory;
import org.stockwell.keys.Constants;
import org.stockwell.tests.TestBase;
import org.stockwell.ui.UTBase;
import org.stockwell.ui.UTTextBox;

public class Login extends WebDriverFactory {
		private UTTextBox textBox = new UTTextBox();
		private UTBase base = new UTBase();
		
		public static final By TXT_EMAIL = By.xpath("//input[@type='text']");
		public static final By TXT_PASSWORD = By.xpath("//input[@type='password']");
		public static final By BTN_SIGN_IN = By.xpath("//button[text()='Sign In']");
		public static final By BTN_HAMBURGER = By.xpath("//button[@aria-label='Side Nav']");
		public static final By STOCKWELL_HOME = By.xpath("//span[text()='Stockwell']");
		
		
		public void login(String userName, String password) {
			try {
				
				base.waitforElement(TXT_EMAIL, Constants.THREE_SECOND);
				textBox.enterText(TXT_EMAIL, userName);
				base.waitforElement(TXT_PASSWORD, Constants.THREE_SECOND);
				textBox.enterText(TXT_PASSWORD, password);
				base.click(BTN_SIGN_IN);
			} catch (Exception exc) {
				TestBase.captureScreenshot(exc.toString());
			}
		}	
}
