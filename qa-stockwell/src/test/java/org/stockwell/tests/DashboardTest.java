package org.stockwell.tests;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.stockwell.keys.Constants;
import org.stockwell.pages.Dashboard;
import org.stockwell.pages.Menu;
import org.stockwell.ui.UTBase;
import org.testng.annotations.Test;

public class DashboardTest extends TestBase {
	
	UTBase base = new UTBase();
	LoginTest login = new LoginTest();
	Dashboard dashboard = new Dashboard();
	
  @Test(groups = {"regression"},description="Webapp>Dashboard-View monthly sales data for the metro")
  public void  viewMonthlySalesData(){
	  try {
	  login.verifyLoginPageAsSuperUser();
	  base.click(Menu.BTN_HAMBURGER);
	  base.waitforElementToBeVisible(Menu.MI_DASHBOARD, Constants.ONE_SECOND);
	  base.click(Menu.MI_DASHBOARD);
	  base.click(Menu.BTN_METRODROPDOWN);
	  List<WebElement> svgElements=  base.getListofAllWebElements(Menu.BTN_EXPANDMORE);
		for (WebElement svgElement : svgElements) {
		   String styleAttribute = svgElement.getAttribute("style");
		   if (styleAttribute.contains("transform: rotate(180deg)")) {
		       svgElement.click();
		   }
		}
	  base.click(Menu.LST_METRO);
	  base.click(Menu.LST_SUBMETRO);
		 
	  } catch(Exception exc) {
		  TestBase.captureScreenshot(exc.toString());
	  }
	 
  }
}
