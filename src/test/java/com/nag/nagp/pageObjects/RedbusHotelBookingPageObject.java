package com.nag.nagp.pageObjects;

import org.openqa.selenium.By;

public interface RedbusHotelBookingPageObject {
	
	By searchKey=By.xpath("//input[@name='search_key']");
	
	By checkInDate=By.xpath("//input[@name='checkin_date']");
	By checkOutDate=By.xpath("//input[@name='checkout_date']");
	
	By guestCount=By.xpath("//input[@name='guest_count']");
	
	By searchButton=By.xpath("//button[@name='search_hotel']");
	
	By adultGuestSelectionBox=By.xpath("//label[text()='Adults']/following-sibling::select");
	
	By childGuestSelectionBox=By.xpath("//label[text()='Children']/following-sibling::select");
	
	By roomCountSelectionBox=By.xpath("//label[text()='Rooms']/following-sibling::select");
	
	
	
}
