package org.stockwell.keys;

public class FilePath {
	public FilePath() {
	}

	

	public static final String FILE = "file:\\\\";
	public static final String PATH = System.getProperty("user.dir");
	public static final String HOME_PATH = System.getProperty("user.home");
	public static String PROPERTY_CONFIG_FILE = PATH + "\\src\\test\\resources\\ConfigStaging.properties";
	public static final String DRIVER_CHROME = PATH + "\\src\\test\\resources\\chromedriver.exe";
	
	public void setEnvironment(String environment) {
		if (environment.equals(Constants.STAGING)) {
			PROPERTY_CONFIG_FILE = PATH + "\\src\\test\\resources\\ConfigStaging.properties";
		} 
	}

	
	

	
}
