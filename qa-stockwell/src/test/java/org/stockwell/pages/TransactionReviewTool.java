package org.stockwell.pages;

import org.openqa.selenium.By;


public class TransactionReviewTool {
	public static final By BTN_ALL = By.xpath("//button[@aria-label='Side Nav']");
	//public static final By BTN_ALL = By.xpath("//button[text()='All']");
	public static final By BTN_NONFLAGGED = By.xpath("//button[text()='Non-Flagged']");
	public static final By BTN_FLAGGED = By.xpath("//button[text()='Flagged']");
	public static final By BTN_VERIFYJARVIS = By.xpath("//button[text()='Verify Jarvis']");
	public static final By BTN_GRABTRANSACTION = By.xpath("//button[text()='Grab transaction']");
	public static final By TBL_HEADER = By.xpath("//th/span");
	
}
