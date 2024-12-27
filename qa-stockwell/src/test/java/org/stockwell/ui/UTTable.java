package org.stockwell.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.stockwell.browser.WebDriverFactory;
import org.stockwell.tests.TestBase;


public class UTTable extends WebDriverFactory{
	
	UTBase base = new UTBase();
  
	public List<String> getTblHeaders(By locator) {
		List<String> headerValues = new ArrayList<>();
		try {
			
			List<WebElement> columnHeaders = getDriver().findElements(locator);
			for (WebElement columnHeader : columnHeaders) {
				headerValues.add(columnHeader.getText());
				
			}
		} catch (Exception exc) {
			TestBase.captureScreenshot(exc.toString());
		}
		return headerValues;
	}
	
	public Set<String> getColumnValues(int columnNumber) {
		Set<String> columnValue = new HashSet<>();
		int totalRows = base.getSizeofListElement(By.xpath("//tbody//tr"));
		for(int i = 1; i<= totalRows; i++)
		{
			List<WebElement> columnData = getDriver().findElements(By.xpath("//tbody//tr//td["+columnNumber+"]/span"));
			for(WebElement data: columnData){
				columnValue.add(data.getText());
			}
				//base.getTextofListElement(By.xpath("//tr//td["+columnNumber+"]"));
		}
		return columnValue;
	}
}
