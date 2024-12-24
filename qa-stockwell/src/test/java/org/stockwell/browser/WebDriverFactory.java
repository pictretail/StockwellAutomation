package org.stockwell.browser;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.stockwell.keys.Constants;
import org.testng.Assert;

public class WebDriverFactory {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public void setDriver(String browser, String environment) {
        try {
            WebDriver driver;
            if(environment.equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case Constants.CHROME:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(getChromeOptions());
                    break;

                case Constants.FIREFOX:
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(getFirefoxOptions());
                    break;

                case Constants.EDGE:
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver(getEdgeOptions());
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            webDriver.set(driver);  
            }
            else if(environment.equalsIgnoreCase("remote")) {
            	ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("enable-automation");
                options.addArguments("--disable-gpu");
                options.addArguments("--headless"); 
                options.addArguments("--disable-extensions");
                options.addArguments("--use-gl=swiftshader");
                options.addArguments("--disable-dev-shm-usage");
                driver = new ChromeDriver(options);
                webDriver.set(driver); 
            }

           // Set the driver in ThreadLocal

        } catch (Exception exc) {
            Assert.fail(exc.toString());
        }
    }

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    private static List<String> getBrowserOptions() {
        List<String> arguments = new ArrayList<>();
        arguments.add("--start-maximized");
        arguments.add("--disable-notifications");
        arguments.add("--window-size=1920,1080");
        return arguments;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(getBrowserOptions());
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("enable-automation");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--use-gl=swiftshader");
        options.addArguments("--disable-dev-shm-usage");
        return options;
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments(getBrowserOptions());
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments(getBrowserOptions());
        return options;
    }
}
