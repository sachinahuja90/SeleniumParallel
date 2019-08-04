package com.nag.nagp.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.nag.nagp.customException.ElementNotClickable;
import com.nag.nagp.customException.ElementNotFound;
import com.nag.nagp.pageObjects.RedbusBusListPageObject;
import com.nag.nagp.testcasebase.TestCasesBase;

public class Test_Redbus_BusList_Page extends TestCasesBase {
	
	
	@Test(description="Validate the type of filter available on Bus list page.")
	public void Test_BusListFilters() throws ElementNotFound, ElementNotClickable,Exception {
		setJourneyDetails(configProperties.get("Source"),configProperties.get("Destination"),"CurrentDate");
		assertTrue(keywords.verifyElementPresence("FilterLsit",RedbusBusListPageObject.filters));
		m_custom.assertTrue(verifyFilterList(), "Filter list on Buslist page doesn't match with expected values");
	}
	
	@Test(description="Validate when user selected filters Live Tracking.")
	public void Test_BusList_With_LiveTracking_Filter() throws ElementNotFound, ElementNotClickable, Exception {
		setJourneyDetails(configProperties.get("Source"),configProperties.get("Destination"),"CurrentDate");
		assertTrue(keywords.verifyElementPresence("FilterLsit",RedbusBusListPageObject.filters));
		keywords.click("liveTrackingFilter", RedbusBusListPageObject.liveTrackingFilter);
		assertTrue(keywords.verifyElementPresence("liveTrackingLabel",RedbusBusListPageObject.liveTrackingLabel));
		m_custom.assertTrue(verifyBusListWithFilter(RedbusBusListPageObject.liveTrackingFilter,"Live Tracking"), "Bus List filter label count doesn't matches with number of buses in list.");
	}
	
	@Test(description="Validate reset button when no filter is selected.")
	public void Test_BusListResetFilter() throws ElementNotFound, ElementNotClickable,Exception {
		setJourneyDetails(configProperties.get("Source"),configProperties.get("Destination"),"CurrentDate");
		keywords.explicitWait(RedbusBusListPageObject.filters);
		keywords.click("liveTrackingFilter", RedbusBusListPageObject.liveTrackingFilter);
		m_custom.assertTrue(verifyBusListWithFilter(RedbusBusListPageObject.liveTrackingFilter,"Live Tracking"), "Bus List filter label count doesn't matches with number of buses in list.");
		keywords.click("filterResetButton", RedbusBusListPageObject.filterResetButton);
		int busCountInList=getTotalBusCountInList();
		int busCountLabel=getTotalBusCountOnLabel();
		m_custom.assertEquals(busCountInList, busCountLabel,"Bus List top label count: "+busCountLabel +" doesn't matches with number of buses in list: "+busCountInList);
	}
	
}
