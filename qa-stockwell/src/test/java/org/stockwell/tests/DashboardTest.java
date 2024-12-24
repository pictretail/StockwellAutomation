package org.stockwell.tests;

import org.stockwell.pages.Dashboard;
import org.stockwell.ui.UTBase;
import org.testng.annotations.Test;

public class DashboardTest extends TestBase {
	
	UTBase base = new UTBase();
	LoginTest login = new LoginTest();
	Dashboard dashboard = new Dashboard();
	
  @Test(groups = {"regression"},description="Webapp>Dashboard-View monthly sales data for the metro")
  public void  viewMonthlySalesData(){
	  login.verifyLoginPageAsSuperUser();
	  base.click(dashboard.BTN_DASHBOARD);
	  if (!base.getCurrentUrl().contains("dashboard")) {
          System.out.println("Failed to navigate to Dashboard.");
          //return;
      }
	  
	  
  }
}
