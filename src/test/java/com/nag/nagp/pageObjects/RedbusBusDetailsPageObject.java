
package com.nag.nagp.pageObjects;

import org.openqa.selenium.By;

public interface RedbusBusDetailsPageObject {
	
	By redbusBusDetailsObjects=By.xpath("//ul[@class='bus-items']/div");
	By amenityOject=By.xpath("//span[text()='Amenities']");
	By wifiAmenityIconOject=By.xpath("//span[@class='amenity-name d-color' and text()='WIFI']");
	
	
}
