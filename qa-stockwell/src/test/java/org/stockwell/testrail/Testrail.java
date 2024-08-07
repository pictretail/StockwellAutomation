package org.stockwell.testrail;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.stockwell.utilities.PropertyFile;
import org.stockwell.keys.Configuration;
import org.stockwell.keys.Constants;
import org.stockwell.keys.FilePath;

	
	 
public class Testrail {
	 
	public PropertyFile propertyFile = new PropertyFile();
 
	public void testRailPassResult(String caseNum) {
		testRailCreateResult(caseNum, Constants.TEST_RAIL_PASS_MESSAGE, Constants.TEST_RAIL_PASS_STATUSID);
	}
 
	public void testRailFailResult(String caseNum, String exception) {
		testRailCreateResult(caseNum, exception, Constants.TEST_RAIL_FAIL_STATUSID);
	}
 
	public void testRailCreateResult(String caseNum, String message, String testRailStatusId) {
		try {
			APIClient client = new APIClient(propertyFile.readPropertyFile(Configuration.TEST_RAIL_BASE_URL, FilePath.PROPERTY_CONFIG_FILE));
			client.setUser(propertyFile.readPropertyFile(Configuration.TEST_RAIL_USER_NAME, FilePath.PROPERTY_CONFIG_FILE));
			client.setPassword(propertyFile.readPropertyFile(Configuration.TEST_RAIL_PASSWORD, FilePath.PROPERTY_CONFIG_FILE));
			Map<String, Object> data = new HashMap<>();
			data.put("status_id", Integer.valueOf(testRailStatusId));
			data.put("comment", message);
 

			try {
				JSONObject createRequest = (JSONObject) client.sendPost(
						propertyFile.readPropertyFile(Configuration.TEST_RAIL_BASE_URI, FilePath.PROPERTY_CONFIG_FILE)
								+ propertyFile.readPropertyFile(Configuration.TEST_RAIL_RUNID,
										FilePath.PROPERTY_CONFIG_FILE)
								+ "/" + caseNum,data);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception exc) {
			 exc.printStackTrace();
		}
	}
}
