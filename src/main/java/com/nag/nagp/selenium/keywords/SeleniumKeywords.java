package com.nag.nagp.selenium.keywords;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nag.nagp.client.BrowserFactory;
import com.nag.nagp.customException.ElementNotClickable;
import com.nag.nagp.customException.ElementNotFound;
import com.nag.nagp.reporterLogger.ReportFactory;

public class SeleniumKeywords {
	WebElement we;
	public static int waitTime;
	public boolean verifyElementPresence(String nameofObject,By selector) throws ElementNotFound{
		
		try {
			getElement(selector);
			we.isDisplayed();
			ReportFactory.getInstance().info("WebElement Present : "+nameofObject+" ["+selector+"]");
			return true;
		}
		catch(Exception e) {
			throw new ElementNotFound(nameofObject+" ["+selector+"]");
		}
		
	}
	
	public boolean verifyElementEnabled(String nameofObject,By selector) {
		we=null;
		try {
			getElement(selector);
			we.isEnabled();
			ReportFactory.getInstance().info("WebElement Enabled : "+nameofObject+" ["+selector+"]");
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}

	public void navigateTo(String URL) {
		BrowserFactory.getInstance().getDriver().get(URL);
		ReportFactory.getInstance().info("Navigated to URL : "+ URL);
	}
	
	
	public void navigateToPreviousPage() throws Exception {
		BrowserFactory.getInstance().getDriver().navigate().back();
		ReportFactory.getInstance().info("Navigated back to Prevoius page: "+ BrowserFactory.getInstance().getDriver().getCurrentUrl());
	}
	
	
	public void click(String nameofObject,By selector) throws ElementNotFound, ElementNotClickable {
		verifyElementPresence(nameofObject,selector);
		try {
			we.click();
			waitForWindowToLoad();
		}
		catch (Exception e) {
			throw new ElementNotClickable("WebElement "+nameofObject+" ["+selector+ "]");
		}
		ReportFactory.getInstance().info("WebElement Clicked : "+nameofObject+" ["+selector+"]");
		
	}
	
	public void mouseHover(String nameofObject,By selector) throws ElementNotFound {
		we=null;
		verifyElementPresence(nameofObject,selector);
		new Actions(BrowserFactory.getInstance().getDriver()).moveToElement(we).build().perform();;
		ReportFactory.getInstance().info("Mouse hovered Webelement : "+nameofObject+" ["+selector+"]");
	}
	
	
	
	public void typeText(String nameofObject, By selector,String text) throws ElementNotFound{
		we=null;
		verifyElementPresence(nameofObject, selector);
		we.sendKeys(text);
		ReportFactory.getInstance().info(text +" enterted in WebElement : "+nameofObject+" ["+selector+"]");
	}
	
	public void enterKey(String nameofObject, By selector) throws ElementNotFound{
		we=null;
		verifyElementPresence(nameofObject, selector);
		we.sendKeys(Keys.RETURN);
		ReportFactory.getInstance().info("Enter key Pressed for WebElement : "+nameofObject+" ["+selector+"]");
	}
	
	
	public String getBrowserLogs() {
		LogEntries logEntries = BrowserFactory.getInstance().getDriver().manage().logs().get(LogType.BROWSER);
		String logs="";
		for (LogEntry log : logEntries) {
			logs=logs+(new Date(log.getTimestamp()) + " " + log.getLevel() + " " + log.getMessage())+"\n";
		}
		//ReportFactory.getInstance().info("Browser console logs fetched : "+logs);
		return logs;
	}
	
	public boolean verifyElementWithTextPresence(String text) throws ElementNotFound{
		we=null;
		try {
			BrowserFactory.getInstance().getDriver().findElement(By.xpath("//*[text()='"+text+"']")).isDisplayed();
			return true;
		}
		catch(Exception e) {
			throw new ElementNotFound("Object with Text : \""+text+"\"");
		}
	}
	

   	public String getScreenshot(String screenshotName) {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String destination = null;
        try {
        	File src= ((TakesScreenshot)BrowserFactory.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        	destination = ReportFactory.reportFolder+"\\"+screenshotName+dateName+".png";
        	FileUtils.copyFile(src, new File(destination));
			return destination;
		} catch (IOException e) {
			ReportFactory.getInstance().info("Error in taking Screen shots");
		}
		return destination;
        
   	}
   	
   	public void getElement(By by) {
   		we=null;
   		List<WebElement> lst= BrowserFactory.getInstance().getDriver().findElements(by);
   		we= lst.get(0);
   	}
   	
   	public String getText(String nameofObject, By selector) throws ElementNotFound {
   		we=null;
   		verifyElementPresence(nameofObject,selector);
   		String str=we.getText();
   		ReportFactory.getInstance().info("Text : "+str +" fetched from Webelement : "+nameofObject+" ["+selector+"]");
   		return str;
   	}
   	
   	public String getText(String nameofObject, WebElement we) throws ElementNotFound {
   		String str=we.getText();
   		ReportFactory.getInstance().info("Text : "+str +" fetched from Webelement : "+we);
   		return str;
   	}
	
   	public List<WebElement> getChildElementList(String nameofObject, By parentSelector,By childSelector) throws ElementNotFound {
   		verifyElementPresence(nameofObject,parentSelector);
   		implicitWait();
   		List<WebElement> lst=we.findElements(childSelector);
   		ReportFactory.getInstance().info("Child element fetched from Webelement : "+nameofObject+" ["+parentSelector+"  "+childSelector+" ]");
   		return lst;
   	}
	
   	public List<WebElement> getElementList(String nameofObject, By selector) throws ElementNotFound {
   		List<WebElement> lst=we.findElements(selector);
   		ReportFactory.getInstance().info("Webelement list fetched : "+nameofObject+" ["+selector+"]");
   		return lst;
   	}
	
   	public WebElement getChildElement(String nameofObject, By parentSelector, By childSelector) throws ElementNotFound {
   		getElement(parentSelector);
   		return we.findElement(childSelector);
   	}
   	
   	public boolean verifyElementPresence(WebElement wb) throws ElementNotFound{
		try {
			wb.isDisplayed();
			ReportFactory.getInstance().info("WebElement Present : "+wb);
			return true;
		}
		catch(Exception e) {
			throw new ElementNotFound(wb.toString());
		}
		
	}
	
   	public void selectByVisibleText(String nameOfObject,By selector, String text) throws ElementNotFound {
   		verifyElementPresence(nameOfObject,selector);
		new Select(we).selectByVisibleText(text);
		ReportFactory.getInstance().info("Visible text : "+text+" selected in Selectionbox : "+nameOfObject+" ["+selector+"]");
   	}
   	
   	public void selectByValue(String nameOfObject,By selector, String value) throws ElementNotFound {
   		verifyElementPresence(nameOfObject,selector);
		new Select(we).selectByVisibleText(value);
		ReportFactory.getInstance().info("Value : "+value+" selected in Selectionbox : "+nameOfObject+" ["+selector+"]");
   	}
   	
   	
   	public void implicitWait() {
   		BrowserFactory.getInstance().getDriver().manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
   		
   	}
	
   	public void explicitWait(By by) {
   		WebDriverWait wait = new WebDriverWait(BrowserFactory.getInstance().getDriver(),waitTime);
   	    wait.until(ExpectedConditions.elementToBeClickable(by));
   	}
   	
   	
   	public void scrollWindow(int x, int y) {
   		getJsExecutorObject().executeScript("window.scrollBy("+x+","+y+")");
   	}
   	
   	public void scrollWindow(WebElement we) {
   		getJsExecutorObject().executeScript("arguments[0].scrollIntoView();", we);
   	}
   	
   	public void scrollWindow(By by) {
   		waitForElement(by);
   		getElement(by);
   		getJsExecutorObject().executeScript("arguments[0].scrollIntoView();", we);
   		implicitWait();
   		waitForWindowToLoad();
   	}
   	
   	private static JavascriptExecutor js=null;
   	
   	private static JavascriptExecutor getJsExecutorObject() {
   		if(js==null)
   			js=(JavascriptExecutor) BrowserFactory.getInstance().getDriver();
   		return js;
   	}
   	


   	public void click(String nameofObject,String selector) throws ElementNotFound {
		
		verifyElementPresence(nameofObject,By.xpath(selector));
		if(!we.isDisplayed()) {
			scrollWindow(we);
		}
		highlightElement(we);
		we.click();
		ReportFactory.getInstance().info("WebElement Clicked : "+nameofObject+" [xpath :"+selector+"]");
	}
   	
	public void highlightElement(WebElement element) {
		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) BrowserFactory.getInstance().getDriver();
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
					"color: yellow; border: 2px solid yellow;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
		}
	}

	public void waitForWindowToLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(BrowserFactory.getInstance().getDriver(), waitTime);
        wait.until(pageLoadCondition);
    }
	
	public void waitForElement(By by) {
   		WebDriverWait wait = new WebDriverWait(BrowserFactory.getInstance().getDriver(),waitTime);
   	    wait.until(ExpectedConditions.elementToBeClickable(by));
   	}
    
}
