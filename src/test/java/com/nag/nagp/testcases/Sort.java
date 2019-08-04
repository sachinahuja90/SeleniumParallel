package com.nag.nagp.testcases;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.internal.junit.ArrayAsserts;

public class Sort {
	
	public boolean verifySort(By table,String columnName,String sequence) {
		WebDriver wd=new FirefoxDriver();
		WebElement we=wd.findElement(table);
		List<WebElement> headerList=we.findElements(By.xpath("//th"));
		int headcount=0;
		for(WebElement w1:headerList) {
			headcount++;
			if(w1.getText().toUpperCase().equals(columnName.toUpperCase())) {
				break;
			}
		}
		List<WebElement> lst=we.findElements(By.xpath("//tr//td["+headcount+"]"));
		List<String> arr=new ArrayList<String>();
		
		for(WebElement w2:lst) {
			arr.add(w2.getText());
		}
		return verifySort(arr,sequence);
		
	}
	
	
	public boolean verifySort(List<String> arr,String sequence) {
		try { 
	        Integer.parseInt(arr.get(0));
	        if(sequence.toLowerCase().equals("ascending")) {
		        for(int i=0;i<arr.size();i++) {
		        	if(Integer.parseInt(arr.get(i))<Integer.parseInt(arr.get(i+1))) {
		        		return false;
		        	}
		        }
	        }
	        else {
	        	for(int i=0;i<arr.size();i++) {
		        	if(Integer.parseInt(arr.get(i))>Integer.parseInt(arr.get(i+1))) {
		        		return false;
		        	}
		        }
	        }
	    } catch(NumberFormatException e) { 
	    	if(sequence.toLowerCase().equals("ascending")) {
		        for(int i=0;i<arr.size();i++) {
		        	if(arr.get(i).compareTo(arr.get(i+1))>0) {
		        		return false;
		        	}
		        }
	        }
	        else {
	        	for(int i=0;i<arr.size();i++) {
	        		if(arr.get(i).compareTo(arr.get(i+1))<0) {
		        		return false;
		        	}
		        }
	        }
		
	    }
		return true;
	}
	
	

}
