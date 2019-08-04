package com.nag.nagp.testcases;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

import com.nag.nagp.customException.ElementNotClickable;
import com.nag.nagp.customException.ElementNotFound;
import com.nag.nagp.pageObjects.RedbusBusTicketPageObject;
import com.nag.nagp.pageObjects.RedbusHotelBookingPageObject;
import com.nag.nagp.testcasebase.TestCasesBase;

public class Test_Redbus_HotelDetails extends TestCasesBase {
	
	@Test(description="Validate the maximum number of Guest allowed in 1 hotel room.")
	public void Test_MaxGuestPerRoom() throws ElementNotFound, ElementNotClickable {
		keywords.click("hotels_Menu",RedbusBusTicketPageObject.hotels_Menu);
		assertTrue(keywords.verifyElementPresence("SearchKey", RedbusHotelBookingPageObject.searchKey));
		keywords.click("GuestCount", RedbusHotelBookingPageObject.guestCount);
		keywords.selectByVisibleText("roomCountSelectionBox",RedbusHotelBookingPageObject.roomCountSelectionBox , "1");
		keywords.verifyElementPresence("adultGuestSelectionBox", RedbusHotelBookingPageObject.adultGuestSelectionBox);
		int maxAdultCount =getMaxCountInSelectBox("adultGuestSelectionBox", RedbusHotelBookingPageObject.adultGuestSelectionBox);
		int maxChildCount=getMaxCountInSelectBox("childGuestSelectionBox", RedbusHotelBookingPageObject.childGuestSelectionBox);
		m_custom.assertTrue((maxAdultCount+maxChildCount)==6, "Maximum guest per room count : "+(maxAdultCount+maxChildCount)+" doesn't matches with 6");
	}
	
	
	
	
	
	
	
}
