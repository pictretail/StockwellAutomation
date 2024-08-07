package org.stockwell.reportsetup;
 
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.stockwell.keys.Constants;
import org.stockwell.testrail.Testrail;
import org.stockwell.tests.TestInfra;
import org.stockwell.browser.Factory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

 
public class Listeners implements ITestListener {
 
	static ExtentReports objReport;
	public static ExtReport objReportName;
	ExtentTest test;
	private Testrail testRail = new Testrail();
 
	public static int passedCount;
	public static int failedCount;
	public static int skippedCount;
	public static Map<String, Integer> resultSet1 = new HashMap<>();
	public static Map<String, Integer> resultSet2 = new HashMap<>();
	public static Map<String, Integer> resultSet3 = new HashMap<>();
	public static Map<String, Integer> resultSet4 = new HashMap<>();
	public static Map<String, Integer> resultSet5 = new HashMap<>();
	public static Map<String, Integer> resultSet6 = new HashMap<>();
	public static Map<String, Integer> resultSet7 = new HashMap<>();
	public static Map<String, Integer> resultSet8 = new HashMap<>();
	public static Map<String, Integer> resultSet9 = new HashMap<>();
	public static Map<String, Integer> resultSet10 = new HashMap<>();
	public static Map<String, Integer> resultSet11 = new HashMap<>();
	public static Map<String, Integer> resultSet12 = new HashMap<>();
	public static Map<String, Integer> resultSet13 = new HashMap<>();
	public static Map<String, Integer> resultSet14 = new HashMap<>();
	public static Map<String, Integer> resultSet15 = new HashMap<>();
	public static Map<String, Integer> resultSet16 = new HashMap<>();
	public static Map<String, Integer> resultSet17 = new HashMap<>();
	public static Map<String, Integer> resultSet18 = new HashMap<>();
	public static Map<String, Integer> resultSet19 = new HashMap<>();
	public static Map<String, Integer> resultSet20 = new HashMap<>();
	public static List<String> classNames = new ArrayList<String>();
	public static List<Map<String, Integer>> listResultSet = new ArrayList<Map<String, Integer>>();
	public static List<Map<String, Integer>> listResultSetFinal = new ArrayList<Map<String, Integer>>();
 
	public void onTestStart(ITestResult result) {
		test = objReport.createTest("[" + result.getMethod().getRealClass().getName() + "]["
      + result.getMethod().getMethodName() + "]" + " - " + result.getMethod().getDescription());
		ExtFactory.getInstance().setExtent(test);
	}
 
	
	  public void onTestSuccess(ITestResult result) {
		//TestInfra.capturePassedScreenshot();
	    ExtFactory.getInstance().getExtent().log(Status.PASS, " method [" +result.getMethod().getMethodName() + "] is passed"); 
		ExtFactory.getInstance().getExtent().addScreenCaptureFromBase64String(TestInfra.captureScreenShotAsBase64());
		
		if(TestInfra.updateTestRail.toLowerCase().equals(Constants.YES.toLowerCase())){ 
	    ArrayList<String> testCaseIdsList = getTestCaseIdsFromDescription(result.getMethod().getDescription()); 
	    for(String testId : testCaseIdsList) {
	    System.out.println("***[PASS Test Case Id]***: " + testId);
	    testRail.testRailPassResult(testId);
	  } 
	} 
	 passedCount++; 
	 int index =classNames.indexOf(result.getMethod().getRealClass().getSimpleName());
	 updateCount(listResultSet.get(index), Constants.PASS,result.getMethod().getRealClass().getSimpleName()); }
	 
	/*
	 * Get all test case id from description
	 */
	public ArrayList<String> getTestCaseIdsFromDescription(String description) {
		String idArr[] = StringUtils.split(description, "-");
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < idArr.length; i++) {
			if (idArr[i].replaceAll("[^0-9]", "").toString().length() >= 6) {
				list.add(StringUtils.getDigits(idArr[i].trim()));
			}
		}
		return list;
	}
 
	public void onTestFailure(ITestResult result) {
		
		// update test rail
		if (TestInfra.updateTestRail.toLowerCase().equals(Constants.YES.toLowerCase())) {
			ArrayList<String> testCaseIdsList = getTestCaseIdsFromDescription(result.getMethod().getDescription());
			for (String testId : testCaseIdsList) {
				System.out.println("***[FAIL Test Case Id]***: " + testId);
				testRail.testRailFailResult(testId, "Exception is " + result.getThrowable());
			}
		}
		// update fail count
				failedCount++;
				int index = classNames.indexOf(result.getMethod().getRealClass().getSimpleName());
				updateCount(listResultSet.get(index), Constants.FAIL, result.getMethod().getRealClass().getSimpleName());
				
		// get screenshot
		try {
			String screenshot = null;
			String linesofExc[] = result.getThrowable().toString().split("\\r?\\n");
			if (!linesofExc[0].contains(TestInfra.THROWABLE_EXCEPTION)) {
				ExtFactory.getInstance().getExtent().log(Status.FAIL, linesofExc[0]);
				screenshot = objReportName.getScreenshot(Factory.getDriver());
				//ExtFactory.getInstance().getExtent().addScreenCaptureFromPath(screenshot);
				ExtFactory.getInstance().getExtent().addScreenCaptureFromBase64String(TestInfra.captureScreenShotAsBase64());
			}
			ExtFactory.getInstance().getExtent().log(Status.FAIL, "Test Case is failed");
			ExtFactory.getInstance().removeExtentObject();
 
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		TestInfra.THROWABLE_EXCEPTION = "";
	}
 
	public void onTestSkipped(ITestResult result) {
		ExtFactory.getInstance().getExtent().log(Status.SKIP,
				"Test Case" + result.getMethod().getMethodName() + "is skipped due to " + result.getThrowable());
		ExtFactory.getInstance().removeExtentObject();
		skippedCount++;
		int index = classNames.indexOf(result.getMethod().getRealClass().getSimpleName());
		updateCount(listResultSet.get(index), Constants.SKIP, result.getMethod().getRealClass().getSimpleName());
	}
 
	public void onStart(ITestContext context) {
		try {
			objReportName = new ExtReport("Stockwell Test Automation", "Stockwell Test Results");
			objReport = objReportName.getReporter();
			listResultSet = Arrays.asList(resultSet1, resultSet2, resultSet3, resultSet4, resultSet5, resultSet6,
					resultSet7, resultSet8, resultSet9, resultSet10, resultSet11, resultSet12, resultSet13, resultSet14,
					resultSet15, resultSet16, resultSet17, resultSet18, resultSet19, resultSet20);
			List<ITestNGMethod> methods = context.getSuite().getAllMethods();
			Set<String> classNamesHash = new HashSet<>();
			for (ITestNGMethod method : methods) {
				classNamesHash.add(method.getRealClass().getSimpleName());
			}
			classNames = new ArrayList<String>(classNamesHash);
			for (int i = 0; i < classNames.size(); i++) {
				initializeMap(listResultSet.get(i));
			}
 
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
 
	public void onFinish(ITestContext context) {
		 for (Map<String, Integer> map : listResultSet) {
		 	if (!map.isEmpty())
		 		listResultSetFinal.add(map);
		 }
		
		objReport.flush();
	}
 
	public void initializeMap(Map<String, Integer> map) {
		map.put(Constants.PASS, 0);
		map.put(Constants.FAIL, 0);
		map.put(Constants.SKIP, 0);
	}
 
	public void updateCount(Map<String, Integer> map, String testCaseStatus, String className) {
		map.put(testCaseStatus, map.get(testCaseStatus) + 1);
	}
}