package com.nag.nagp.pageObjects;

import org.openqa.selenium.By;

public interface RedbusBusListPageObject {
	By redbusList=By.xpath("//ul[@class='bus-items']");
	By redbusListElement=By.xpath("//div[@class='clearfix bus-item']");
	By journeyDetails=By.xpath("//div[@id='fixer']");
	By filters=By.xpath("//ul[@class='addn-filters']");
	By filterList=By.xpath("//ul[@class='addn-filters']//span");
	By liveTrackingFilter=By.xpath("//li/span[contains(text(),'Live Tracking')]");
	By liveTrackingLabel=By.xpath("//li[@title='Live Tracking']");
	
	
	By totalBusCountLabel=By.xpath("//span[@class='f-bold busFound']");
	By governmentBusCount=By.xpath("//div[@class='group-data clearfix']//div[@class='f-bold ']");
	
	By filterResetButton=By.xpath("//div[text()='FILTERS']/span[text()='RESET']");
	
	By wifiFilter=By.xpath("//ul[@class='amenity-list clearfix']//span[text()='WIFI']");//("//span[@title='WIFI']/parent::li");
}
