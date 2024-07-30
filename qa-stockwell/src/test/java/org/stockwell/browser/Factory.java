package org.stockwell.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.stockwell.keys.Constants;
import org.testng.Assert;


public class Factory {

	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

	public void setDriver(String drivers,String browser) {
		try {
			//DesiredCapabilities capabilities = null;
				if (browser.equals(Constants.CHROME)) {
					// capabilities = new DesiredCapabilities();
					// ChromeOptions chromeOptions = new ChromeOptions();
					// //chromeOptions.addArguments("--remote-allow-origins=*");
					// chromeOptions.addArguments("--start-maximized");
					// Map<String, Object> chromePrefs = new HashMap<>();
					// chromePrefs.put("credientials enable service", false);
					// chromeOptions.setExperimentalOption("prefs", chromePrefs);
					// chromeOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable.popup.blocking");
					// capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
					// capabilities.setCapability("applicationCacheEnabled", false);
					// chromeOptions.merge(capabilities);
					// //System.setProperty("webdriver.chrome.driver", FilePath.DRIVER_CHROME);
					// chromeOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable.popup.blocking");
					// webDriver.set(new ChromeDriver(chromeOptions));

					ChromeOptions cap = new ChromeOptions();
            cap.addArguments("--no-sandbox");
            cap.addArguments("enable-automation");
            cap.addArguments("--disable-gpu");
            cap.addArguments("--headless"); // Enable headless mode
            cap.addArguments("--disable-extensions");
            cap.addArguments("--use-gl=swiftshader");
            cap.addArguments("--disable-dev-shm-usage");
            WebDriver driver = new ChromeDriver(cap);
            webDriver.set(driver); 
			} 
		} catch (Exception exc) {
			Assert.fail(exc.toString());
		}
	}
	
	public static WebDriver getDriver() {
		return webDriver.get();
	}
	
}
