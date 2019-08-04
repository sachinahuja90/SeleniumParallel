package com.nag.nagp.testcases;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.nag.nagp.customException.ElementNotClickable;
import com.nag.nagp.customException.ElementNotFound;
import com.nag.nagp.pageObjects.RedbusBusTicketPageObject;
import com.nag.nagp.testcasebase.TestCasesBase;


public class Test_Redbus_BusTicket_BookingPage extends TestCasesBase {
	
	@Test(description="Validate the fields on Redbus bus ticket page.")
	public void Test_Validate_BusTicket_Fields() throws ElementNotFound, ElementNotClickable {
		keywords.click("busTickets_Menu",RedbusBusTicketPageObject.busTickets_Menu);
		assertTrue(keywords.verifyElementPresence("sourceLocation", RedbusBusTicketPageObject.sourceLocation));
		assertTrue(keywords.verifyElementPresence("destinationLocation", RedbusBusTicketPageObject.destinationLocation));
		assertTrue(keywords.verifyElementPresence("onwardDate", RedbusBusTicketPageObject.onwardDate));
		assertTrue(keywords.verifyElementPresence("returnDate", RedbusBusTicketPageObject.returnDate));
		assertTrue(keywords.verifyElementPresence("searchBus_Button", RedbusBusTicketPageObject.searchBus_Button));
	}
	
	@Test(description="Validate the number of buses when Source and Destination are same.")
	public void Test_Validate_BusesSameSourceDestination() throws ElementNotFound, ElementNotClickable, Exception {
		setJourneyDetails(configProperties.get("Source"),configProperties.get("Source"),"CurrentDate");
		m_custom.assertTrue(keywords.verifyElementWithTextPresence("Oops! No buses found."));
	}
//	
//	@Test(description="Validate the number of buses when Source and Destination are same.")
//	public void Test_Validate_BusesWithNoOriginDate() throws ElementNotFound, ElementNotClickable, Exception {
//		setJourneyDetails(configProperties.get("Source"),configProperties.get("Destination"),"");
//		assertTrue(keywords.verifyElementPresence("onwardDate", RedbusBusTicketPageObject.onwardDate));
//	}
//
//
//	@Test(description="Validate the number of buses on the route when Source is New Delhi and Destination is Jaipur.")
//	public void Test_Validate_BusesBetween_Delhi_Jaipur() throws ElementNotFound, ElementNotClickable, Exception {
//		setJourneyDetails(configProperties.get("Source"),configProperties.get("Destination"),"CurrentDate");
//		assertTrue(keywords.verifyElementPresence("journeyDetails", RedbusBusListPageObject.journeyDetails));
//		assertTrue(keywords.verifyElementPresence("redbusList", RedbusBusListPageObject.redbusList));
//		int busCountInList=getTotalBusCountInList();
//		int busCountLabel=getTotalBusCountOnLabel();
//		m_custom.assertEquals(busCountInList, busCountLabel,"Bus List top label count: "+busCountLabel +" doesn't matches with number of buses in list: "+busCountInList);
//	}

}
