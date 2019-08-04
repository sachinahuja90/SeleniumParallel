package com.nag.nagp.testcases;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlGroups;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlRun;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.nag.nagp.testListeners.TestListener;

public class DynamicTestNG {

	public static void main(String[] args) {

        String csvFile = "Clients.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                if(((br.readLine().split(cvsSplitBy))[2]).equals("email@gmail.com")){
                    String[] data = line.split(cvsSplitBy);

                    System.out.println("First Name: "+data[0]+" Last Name: "+data[1]+" Activity Level: "+data[7]);

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



	public  static void main1(String args[]) {
		
		//Create an instance on TestNG 
		TestNG tng = new TestNG();
		
		//Create an instance of XML Suite and assign a name for it.
		XmlSuite suite = new XmlSuite();
		suite.setName("RegressionSuite");
		//mySuite.setParallel("Tests"); 
		//mySuite.setThreadCount(10);
		
		//Create an instance of XmlTest and assign a name for it. 
		XmlTest test = new XmlTest(suite);
		test.setName("Test");
		//test.setPreserveOrder("true");
		
		// Create an instance of XmlGroups that will hold the Run Instance
		XmlGroups grp=new XmlGroups();
	    
		//Create an instance of XmlRun to include group name in XML file 
		XmlRun xmlRun = constructIncludes("Suite;Group1".split(";"));
	    grp.setRun(xmlRun);
		    
	    
	    test.setGroups(grp);
	    
	    List<XmlPackage> packages = new ArrayList<XmlPackage>();
	    packages.add(new XmlPackage("com.nag.nagp.testcases.*"));
	    packages.add(new XmlPackage("com.nag.nagp.testcasebase.*"));
	    test.setXmlPackages(packages);
		    
	    TestListener tla = new TestListener();
	    tng.addListener((ITestNGListener) tla);
	    
	    List<XmlSuite> suites = new ArrayList<XmlSuite>();
	    suites.add(suite);
		tng.setXmlSuites(suites);
		System.out.println (suite.toXml ());
		tng.run();
		
		
		    
	}
	
	private static XmlRun constructIncludes (String... methodNames) {
		XmlRun xmlRun = new XmlRun();
        for (String eachMethod : methodNames) {
        	xmlRun.onInclude(eachMethod.trim());
        }
        return xmlRun;
    }
}
