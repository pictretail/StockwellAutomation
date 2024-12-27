package org.stockwell.keys;

import java.io.File;

import org.testng.annotations.Test;

public class FilePath {
	public FilePath() {
	}

	//public static final String FILE = "file:/";
	public static final String PATH = System.getProperty("user.dir");
	//public static final String HOME_PATH = System.getProperty("user.home");
	public static String PROPERTY_CONFIG_FILE;
	 //public static final String DRIVER_CHROME = PATH + "/src/test/resources/chromedriver.exe";
	//public static String testdatafilePath =  "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData.xlsx";
	//public static  final String TEST_DATA = testdatafilePath ;
	public static  final String TEST_DATA = "src/test/resources/TestData.xlsx" ;
	
	public void setEnvironment(String executionenvironment) {
		if (executionenvironment.equals(Constants.STAGING)) {
			PROPERTY_CONFIG_FILE = PATH + "/src/test/resources/ConfigStaging.properties";
		} 
	}

	

	
}
