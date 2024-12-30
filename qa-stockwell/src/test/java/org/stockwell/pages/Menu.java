package org.stockwell.pages;

import org.openqa.selenium.By;
import org.stockwell.keys.Constants;

public class Menu {
	
	public static final By BTN_HAMBURGER = By.xpath("//button[@aria-label='Side Nav']");
	public static final By MI_DASHBOARD = By.xpath("//p[text()='Dashboard']");
	public static final By MI_SEARCHTOOL = By.xpath("//p[text()='Search Tool']");
	public static final By MI_STOCKWELLS = By.xpath("//p[text()='Stockwells']");
	public static final By MI_TRANSACTIONREVIEWTOOL = By.xpath("//p[text()='Transaction Review Tool']");
	public static final By MI_TRANSACTIONSDASHBOARD = By.xpath("//p[text()='Transaction Review Tool']");
	public static final By MI_LABELREACHSEGMENTS = By.xpath("//p[text()='Label Reach Segments']");
	public static final By MI_LABELSTOCKWELLPHOTOS = By.xpath("//p[text()='Label Stockwell Photos']");
	public static final By MI_GLOBALITEMS = By.xpath("//p[text()='Global Items']");
	public static final By MI_ITEMS = By.xpath("//p[text()='Items']");
	public static final By MI_SUPPLIERS = By.xpath("//p[text()='Suppliers']");
	public static final By MI_STORESCHEMATOOL = By.xpath("//p[text()='Store Schema Tool']");
	public static final By MI_UPLOADPODPHOTO = By.xpath("//p[text()='Upload Pod Photo']");
	public static final By MI_DIAGNOSTICS = By.xpath("//p[text()='Diagnostics']");
	public static final By MI_DEVICEMANAGEMENT = By.xpath("//p[text()='Device Management']");
	public static final By MI_CAMERACALIBRATIONS = By.xpath("//p[text()='Camera Calibrations']");
	public static final By MI_DEVICEDASHBOARD = By.xpath("//p[text()='Device Dashboard']");
	public static final By MI_SSHCOMMANDS = By.xpath("//p[text()='SSH Commands']");
	public static final By MI_METROMANAGEMENT = By.xpath("//p[text()='Metro Management']");
	public static final By MI_OPERATINGCOMPANY = By.xpath("//p[text()='Operating Company']");
	public static final By MI_USERMANAGEMENT = By.xpath("//p[text()='User Management']");
	public static final By MI_PERMISSIONGROUPS = By.xpath("//p[text()='Permission Groups']");
	public static final By MI_SIGNOUT = By.xpath("//p[text()='Sign Out']");
	public static final By BTN_METRODROPDOWN = By.xpath("//button[@aria-label='Metro']//*[local-name()='svg'][2]");
	public static final By BTN_EXPANDMORE = By.xpath("//*[local-name()='svg' and @data-testid='ExpandMoreIcon']");
	public static final By LST_METROOPTIONS = By.xpath("//ul[@role='menu']//li");
	public static final By LST_METRO = By.xpath("//ul[@role='menu']//p[text()='"+Constants.METRO+"']");
	public static final By LST_SUBMETRO = By.xpath("//ul[@role='menu']//li[text()='"+Constants.SUBMETRO+"']");
	

}
