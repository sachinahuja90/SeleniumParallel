package com.nag.nagp.testcases;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.nag.nagp.customException.ElementNotClickable;
import com.nag.nagp.customException.ElementNotFound;
import com.nag.nagp.pageObjects.RedbusBusDetailsPageObject;
import com.nag.nagp.pageObjects.RedbusBusListPageObject;
import com.nag.nagp.testcasebase.TestCasesBase;

public class Test_Redbus_BusDetails extends TestCasesBase {
	
	@Test(description="Validate the number of buses with Wifi Amenities when user select Wifi.",groups= {"Group1"})
	public void Test_Validate_BusCountWithWifi() throws ElementNotFound, ElementNotClickable, Exception {
		setJourneyDetails(configProperties.get("Source"),configProperties.get("Destination"),"CurrentDate");
		keywords.scrollWindow(RedbusBusListPageObject.wifiFilter);
		keywords.click("wifiFilter",RedbusBusListPageObject.wifiFilter);
		int busCountInList=getTotalBusCountInList();
		int busCountLabel=getBusCountWifiAmenties();
		m_custom.assertEquals(busCountInList, busCountLabel,"Bus List count with wifi label : "+busCountLabel +" doesn't matches with number of buses in list: "+busCountInList);
	}
	
	
	
	@Test(description="Validate wifi icons with the buses having Wifi Amenities.",groups= {"Group1"},dependsOnMethods= {"Test_Validate_BusCountWithWifi"})
	public void Test_Validate_WifiIcon() throws ElementNotFound, ElementNotClickable, InterruptedException,Exception {
		setJourneyDetails(configProperties.get("Source"),configProperties.get("Destination"),"CurrentDate");
		keywords.explicitWait(RedbusBusListPageObject.filters);
		keywords.scrollWindow(RedbusBusListPageObject.wifiFilter);
		keywords.click("Wifi Filter",RedbusBusListPageObject.wifiFilter);
		keywords.mouseHover("Redbus 1st BusDetails", RedbusBusDetailsPageObject.redbusBusDetailsObjects);
		keywords.getChildElement("amenityOject ", RedbusBusDetailsPageObject.redbusBusDetailsObjects, RedbusBusDetailsPageObject.amenityOject).click();
		assertTrue(keywords.verifyElementPresence("wifiAmenityIconOject", RedbusBusDetailsPageObject.wifiAmenityIconOject));
	}
	
}
