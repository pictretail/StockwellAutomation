package org.stockwell.tests;

import java.util.List;
import org.stockwell.pages.Menu;
import org.stockwell.keys.Constants;
import org.stockwell.pages.TransactionReviewTool;
import org.stockwell.ui.UTBase;
import org.stockwell.ui.UTTable;
import org.testng.annotations.Test;

public class TransactionReviewToolTest extends TestBase {
	LoginTest login = new LoginTest();
	UTBase base = new UTBase();
	UTTable table = new UTTable();
	
  @Test(groups={"regression"},description = "C246469-Webapp>Transaction Review Tool-Verify All transactions fields")
  public void VerifyAllTransactionsFields() {
	  try {
		  login.verifyLoginPageAsSuperUser();
		  base.click(Menu.BTN_HAMBURGER);
		  base.click(Menu.MI_TRANSACTIONREVIEWTOOL);
		  base.click(TransactionReviewTool.BTN_ALL);
		  base.waitforElement(TransactionReviewTool.TBL_HEADER, Constants.ONE_SECOND);
		  List<String> transactionHeaders=  table.getTblHeaders(TransactionReviewTool.TBL_HEADER);
		  System.out.println(transactionHeaders);
//		  for(String headers : transactionHeaders)
//		  {
//		  System.out.println(headers);
//		  }
		  
		  
	  } catch(Exception exc) {
		  TestBase.captureScreenshot(exc.toString());
	  }
	  
  }
}
