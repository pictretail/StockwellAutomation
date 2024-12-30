package org.stockwell.pages;

import org.openqa.selenium.By;
import org.stockwell.tests.LoginTest;
import org.stockwell.ui.UTBase;


public class TransactionReviewTool {
	
	LoginTest login = new LoginTest();
	UTBase base = new UTBase();
	
	public static final By BTN_ALL = By.xpath("//button[text()='All']");
	public static final By BTN_NONFLAGGED = By.xpath("//button[text()='Non-Flagged']");
	public static final By BTN_FLAGGED = By.xpath("//button[text()='Flagged']");
	public static final By BTN_VERIFYJARVIS = By.xpath("//button[text()='Verify Jarvis']");
	public static final By BTN_GRABTRANSACTION = By.xpath("//button[text()='Grab transaction']");
	public static final By TBL_HEADER = By.xpath("//th/span");
	public static final By LINK_TRANSACTIONS = By.linkText("Transactions");
	
	
	public void navigateToTransactionReviewToolPage() {
		login.verifyLoginPageAsSuperUser();
		  base.click(Menu.BTN_HAMBURGER);
		  base.click(Menu.MI_TRANSACTIONREVIEWTOOL);
	}
}


