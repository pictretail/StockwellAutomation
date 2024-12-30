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
import org.stockwell.tests.TestBase;
import org.stockwell.browser.WebDriverFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

 
public class UTListeners implements ITestListener {
 
	static ExtentReports objReport;
	public static UTReport objReportName;
	ExtentTest test;
	private Testrail testRail = new Testrail();
 
	public static int passedCount;
	public static int failedCount;
	public static int skippedCount;
	HashMap<String,Integer> resultset1 = new HashMap<>();
	HashMap<String,Integer> resultset2 = new HashMap<>();
	HashMap<String,Integer> resultset3 = new HashMap<>();
	HashMap<String,Integer> resultset4 = new HashMap<>();
	public static List<String> classNames = new ArrayList<String>();
	public static List<Map<String, Integer>> listResultSet = new ArrayList<Map<String, Integer>>();
	public static List<Map<String, Integer>> listResultSetFinal = new ArrayList<Map<String, Integer>>();
 
	public void onTestStart(ITestResult result) {
		test = objReport.createTest("[" + result.getMethod().getRealClass().getName() + "]["
      + result.getMethod().getMethodName() + "]" + " - " + result.getMethod().getDescription());
		UTReportFactory.getInstance().setExtent(test);
	}
 
	
	  public void onTestSuccess(ITestResult result) {
		  UTReportFactory.getInstance().getExtent().log(Status.PASS, " method [" +result.getMethod().getMethodName() + "] is passed"); 
			//UTReportFactory.getInstance().getExtent().addScreenCaptureFromPath(TestBase.capturePassedScreenshot());
			
		   // UTReportFactory.getInstance().removeExtentObject();
		if(TestBase.updateTestRail.toLowerCase().equals(Constants.YES.toLowerCase())){ 
	    ArrayList<String> testCaseIdsList = getTestCaseIdsFromDescription(result.getMethod().getDescription()); 
	    for(String testId : testCaseIdsList) {
	    System.out.println("***[PASS Test Case Id]***: " + testId);
	    testRail.testRailPassResult(testId);
	  } 
	} 
	 passedCount++; 
	 int index =classNames.indexOf(result.getMethod().getRealClass().getSimpleName());
	 updateCount(listResultSet.get(index), Constants.PASS,result.getMethod().getRealClass().getSimpleName()); 
	 }
	 
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
		if (TestBase.updateTestRail.toLowerCase().equals(Constants.YES.toLowerCase())) {
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
			String linesofExc[] = result.getThrowable().toString().split("\\r?\\n");
			if (!linesofExc[0].contains(TestBase.THROWABLE_EXCEPTION)) {
				UTReportFactory.getInstance().getExtent().log(Status.FAIL, linesofExc[0],
						MediaEntityBuilder.createScreenCaptureFromBase64String(TestBase.captureScreenShotAsBase64()).build());
			}
			//UTReportFactory.getInstance().removeExtentObject();
 
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		TestBase.THROWABLE_EXCEPTION = "";
	}
 
	public void onTestSkipped(ITestResult result) {
		UTReportFactory.getInstance().getExtent().log(Status.SKIP,
				"Test Case" + result.getMethod().getMethodName() + "is skipped due to " + result.getThrowable());
		UTReportFactory.getInstance().getExtent().addScreenCaptureFromBase64String(TestBase.captureScreenShotAsBase64());
		//UTReportFactory.getInstance().removeExtentObject();
		skippedCount++;
		int index = classNames.indexOf(result.getMethod().getRealClass().getSimpleName());
		updateCount(listResultSet.get(index), Constants.SKIP, result.getMethod().getRealClass().getSimpleName());
	}
 
	public void onStart(ITestContext context) {
		try {
			objReportName = new UTReport("Stockwell Test Automation", "Stockwell Test Results");
			objReport = objReportName.getReporter();
			listResultSet = Arrays.asList(resultset1,resultset2,resultset3,resultset4);
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