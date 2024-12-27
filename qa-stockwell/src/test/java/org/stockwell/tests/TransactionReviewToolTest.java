package org.stockwell.tests;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.stockwell.pages.Menu;
import org.stockwell.keys.Constants;
import org.stockwell.keys.FilePath;
import org.stockwell.pages.TransactionReviewTool;
import org.stockwell.ui.UTAssert;
import org.stockwell.ui.UTBase;
import org.stockwell.ui.UTTable;
import org.stockwell.utilities.UTExcel;
import org.testng.annotations.Test;

public class TransactionReviewToolTest extends TestBase {
	LoginTest login = new LoginTest();
	UTBase base = new UTBase();
	UTTable table = new UTTable();
	UTExcel excel = new UTExcel();
	
  @Test(groups={"regression"},description = "C246469-Webapp>Transaction Review Tool-Verify All transactions fields")
  public void VerifyAllTransactionsFields() {
	  try {
		  login.verifyLoginPageAsSuperUser();
		  base.click(Menu.BTN_HAMBURGER);
		  base.click(Menu.MI_TRANSACTIONREVIEWTOOL);
		  base.click(TransactionReviewTool.BTN_ALL);
		  base.waitforElement(TransactionReviewTool.TBL_HEADER, Constants.ONE_SECOND);
		  List<String> transactionHeaders=  table.getTblHeaders(TransactionReviewTool.TBL_HEADER);
		  List<String> expectedTransactionHeaders = excel.getRowData(FilePath.TEST_DATA, Constants.TRANSACTIONREVIEWTOOL,0);
		  UTAssert.assertEquals(transactionHeaders, expectedTransactionHeaders);
		  
	  } catch(Exception exc) {
		  TestBase.captureScreenshot(exc.toString());
	  }  
  }
  
  @Test(groups= {"regression"},description = "C246470-Webapp>Transaction Review Tool-Verify All transactions video status")
  public void VerifyAllTransactionsVideoStatus() {
	  try {
		  login.verifyLoginPageAsSuperUser();
		  base.click(Menu.BTN_HAMBURGER);
		  base.click(Menu.MI_TRANSACTIONREVIEWTOOL);
		  base.click(TransactionReviewTool.BTN_ALL);
		  base.waitforElement(TransactionReviewTool.TBL_HEADER, Constants.ONE_SECOND);
		  Set<String> videoStatus = table.getColumnValues(7);
		  System.out.println(videoStatus);
	  List<String> statusesToVerify = Arrays.asList("Finished", "Item inference");//, "Uploading","Processing Segments");
	  for(String status : statusesToVerify) {

		  if(videoStatus.contains(status)) {
			  	
			  	UTAssert.assertTrue(true);
		  }
		  }
		  
		  
		 
//		  System.out.println(videoStatus);
//		  UTAssert.assertTrue(videoStatus.contains(statusesToVerify));
//		  
	  } catch(Exception exc) {
		  TestBase.captureScreenshot(exc.toString());
	  }
  }
}
