package com.nag.nagp.client;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {
	
	private static BrowserFactory instance=null;
	
	private BrowserFactory() {
		
	}
	
	public static BrowserFactory getInstance() {
		if(instance==null) {
			instance=new BrowserFactory();
		}
		return instance;
	}
		
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	public final void launchBrowser(String browserType) throws Exception{
		
			if(browserType.equalsIgnoreCase("Chrome")) 			
				setChromeDriver();
			else if(browserType.equalsIgnoreCase("Firefox")) 
				setFireFoxDriver();	
	}
	
	private void setChromeDriver() throws Exception {
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> chromePrefs=new HashMap<String, Object>();
		chromePrefs.put("credentials_enable_service", false);
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--no-sandbox","--disable-extensions","--start-maximized","--disable-notifications");
		System. setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\drivers\\chromedriver.exe");
		driver.set(new ChromeDriver(options));		
	}
	

	private void setFireFoxDriver() throws Exception{
		
		FirefoxOptions options = new FirefoxOptions();
		
		options.addPreference("dom.webnotifications.enabled", false);
//		options.addPreference("webdriver_assume_untrusted_issuer", true);

		// Start Mozilla assuming the Untrusted certificates accepting
//		options.addPreference("setAssumeUntrustedCertificateIssuer", true);
//		options.addPreference("security.enterprise_roots.enabled", true);
        
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\main\\drivers\\geckodriver.exe");
		driver.set(new FirefoxDriver(options));
	}
	
	
	public WebDriver getDriver() {		
		return driver.get();
	}
	
	
	public void quitDriver() {
		driver.get().quit();
	}

}
