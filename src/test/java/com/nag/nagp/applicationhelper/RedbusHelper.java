package com.nag.nagp.applicationhelper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.nag.nagp.client.BrowserFactory;
import com.nag.nagp.common.util.Utilities;
import com.nag.nagp.customException.ElementNotClickable;
import com.nag.nagp.customException.ElementNotFound;
import com.nag.nagp.pageObjects.RedbusBusListPageObject;
import com.nag.nagp.pageObjects.RedbusBusTicketPageObject;
import com.nag.nagp.reporterLogger.ReportFactory;
import com.nag.nagp.testcasebase.TestCasesBase;

public class RedbusHelper{

	public String getOriginDate() {
		Date date=TestCasesBase.utility.increamentCurrentDate(Integer.parseInt(TestCasesBase.configProperties.get("DateAfterCurrentDate")));
		return date.toString();
	}
	
	
	
	public void setOriginDate(String dt) throws ElementNotFound, ElementNotClickable, Exception {
		int day=Integer.parseInt(dt.substring(8, 10));
		int month=Integer.parseInt(dt.substring(5,7));
		int currentMonth=Calendar.getInstance().get(Calendar.MONTH)+1;
		TestCasesBase.keywords.click("OnwardDate", RedbusBusTicketPageObject.onwardDate);
		Thread.sleep(1000);
		while(month!=currentMonth) {
			TestCasesBase.keywords.click("NextMonthButton", RedbusBusTicketPageObject.nextMonthIcon);
			currentMonth++;
		}
		String text=RedbusBusTicketPageObject.onwardDateCalendarDay.replace("<DAY>", day+"");
		TestCasesBase.keywords.click("NextMonthButton", text);
		Thread.sleep(1000);
		TestCasesBase.keywords.implicitWait();
	}
	
	public String getReturnDate() {
		Date date=new Utilities().increamentCurrentDate(Integer.parseInt(TestCasesBase.configProperties.get("DateAfterCurrentDate")+4));
		String formattedDate=new Utilities().dateFormatter(date);
		return formattedDate;
	}
	
	public void setJourneyDetails(String source,String destination,String date) throws ElementNotFound, ElementNotClickable,Exception {
		TestCasesBase.keywords.click("busTickets_Menu",RedbusBusTicketPageObject.busTickets_Menu);
		TestCasesBase.keywords.typeText("sourceLocation", RedbusBusTicketPageObject.sourceLocation, source);
		
		Thread.sleep(1000);
		TestCasesBase.keywords.enterKey("sourceLocation", RedbusBusTicketPageObject.sourceLocation);
		TestCasesBase.keywords.typeText("destinationLocation", RedbusBusTicketPageObject.destinationLocation, destination);
		Thread.sleep(1000);
		TestCasesBase.keywords.enterKey("destinationLocation",RedbusBusTicketPageObject.destinationLocation);
		if(!(date.equals("")))
			setOriginDate(getOriginDate());
			
		TestCasesBase.keywords.click("searchBus_Button",RedbusBusTicketPageObject.searchBus_Button);
		TestCasesBase.keywords.waitForWindowToLoad();
	}
	
	public int getTotalBusCountOnLabel() throws ElementNotFound {
		String str=TestCasesBase.keywords.getText("totalBusCountLabel", RedbusBusListPageObject.totalBusCountLabel);
		return Integer.parseInt(str.replaceAll("Buses", "").trim());
		 
	}
	
	
	public int getTotalBusCountInList_WithFilter() throws ElementNotFound {
		TestCasesBase.keywords.waitForWindowToLoad();TestCasesBase.keywords.waitForWindowToLoad();
		
		int ulCount=TestCasesBase.keywords.getChildElementList("redbusBusList", RedbusBusListPageObject.redbusList,RedbusBusListPageObject.redbusListElement).size();
		
		ReportFactory.getInstance().info("Total no. of Buses in BUS list : "+(ulCount));
		return (ulCount);
	}
	
	public int getTotalBusCountInList() throws ElementNotFound {
		int divCount=0;
		TestCasesBase.keywords.waitForWindowToLoad();TestCasesBase.keywords.waitForWindowToLoad();
		
		List<WebElement>
		lst=TestCasesBase.keywords.getElementList("governmentBusCount",	RedbusBusListPageObject.governmentBusCount); 
		for(WebElement we:lst) {
		divCount=divCount+Integer.parseInt(we.getText().replaceAll("Buses",	"").trim()); 
		}
		
		int ulCount=TestCasesBase.keywords.getChildElementList("redbusBusList", RedbusBusListPageObject.redbusList,RedbusBusListPageObject.redbusListElement).size();
		
		ReportFactory.getInstance().info("Total no. of Buses in BUS list : "+(ulCount+divCount));
		return (ulCount+divCount);
	}
	
	public boolean verifyFilterList() throws ElementNotFound {
		String[] filters="Live Tracking;Red Deals;Reschedulable".split(";");
		List<WebElement> lst=  TestCasesBase.keywords.getElementList("BusListPage_FilterList", RedbusBusListPageObject.filterList);
		if(lst.size()!=3) { 
			return false;
		}
		
		for(int i=0;i<lst.size();i++) {
			if(!(lst.get(i).getText().contains(filters[i]))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean verifyBusListWithFilter(By by,String filterName) throws ElementNotFound {
		TestCasesBase.keywords.waitForWindowToLoad();
		List<WebElement> lst=  TestCasesBase.keywords.getElementList("BusListPage_FilterList", RedbusBusListPageObject.filterList);
		int busCountLabel=Integer.parseInt(lst.get(0).getText().replaceAll(filterName, "").replace("(","").replace(")","").trim());
		int busCountInList=getTotalBusCountInList_WithFilter();
		if(busCountLabel==busCountInList) {
			
			ReportFactory.getInstance().info("Bus List filter label count : "+busCountLabel +" matches with number of buses in list: "+busCountInList);
			return true;
		}
		else
			return false;
	}
	
	public int getBusCountWifiAmenties() throws ElementNotFound {
		String str=TestCasesBase.keywords.getText("wifiFilter ", RedbusBusListPageObject.wifiFilter);
		 return Integer.parseInt(str.replaceAll("WIFI", "").replace("(","").replace(")","").trim());
	}
	
	
	public int getMaxCountInSelectBox(String nameOfObject,By selector) {
		List<WebElement> allOptions =new Select(BrowserFactory.getInstance().getDriver().findElement(selector)).getOptions();
		int maxCount=Integer.parseInt(allOptions.get(allOptions.size()-1).getText());
		return maxCount;
	}
	
}
