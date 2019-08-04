package com.nag.nagp.pageObjects;

import org.openqa.selenium.By;

public interface RedbusBusTicketPageObject{
	
	By busTickets_Menu=By.xpath("//a[@title='redBus']");
	By hotels_Menu=By.xpath("//a[@title='redBus Hotels']");
	By pligrims_Meun=By.xpath("//a[@title='pilgrimages']");
	By busHire_Menu=By.xpath("//a[@title='redBus Bus Hire']");
	By sourceLocation=By.xpath("//input[@id='txtSource']");
	By destinationLocation=By.xpath("//input[@id='txtDestination']");
	By onwardDate=By.id("txtOnwardCalendar");
	By onwardDateCalendarMonth = By.xpath("//div[@id='onward']//div[@id='rb-calmiddle']//span[@id='rb-month']");
	String onwardDateCalendarDay="//div[@id='onward']//div[@id='rb-calmiddle']//li[@data-date='<DAY>']";
	
	By nextMonthIcon=By.xpath("//div[@id='onward']//li[@id='rb-next-middle' and @class='rb-next active']");
	
	By returnDate=By.id("txtReturnCalendar");
	By returnDateCalendar = By.id("rb-calendar_return_cal");
	
	
	By searchBus_Button=By.xpath("//button[@class='search-btn searchBuses']");
	By pickFirstSourcePlace=By.xpath("//div[@class='autocomplete-suggestions'][1]/div[1]");
	By pickFirstDestinationPlace=By.xpath("//div[@class='autocomplete-suggestions'][2]/div[1]");
	
}
