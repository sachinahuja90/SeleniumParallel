package com.nag.nagp.propertyReader;

import java.io.*;
import java.util.*;

/* Class Decription - Class contains all the functions and variables related to Property.config   
 * Created by - Sachin Ahuja
 * Created on - 27th April
 * Modified by
 * Modified on
 * */

public class PropertyReader {
	 
    private Properties prop;

    /* Function Decription - Function will read and load the property file provided in parameter     
     * Created by - Sachin Ahuja
     * Created on - 7th April
     * Modified by
     * Modified on
     * */

    private void loadProps(String propertyFile) throws FileNotFoundException,IOException {
           File cfgfile = new File(propertyFile);
           if (cfgfile.exists()) {
                  FileInputStream propin = new FileInputStream(cfgfile);
                  prop.load(propin);
           }
    }
    
    /* Function Decription - Function will return value of the key provided in function parameters     
     * Created by - Sachin Ahuja
     * Created on - 7th April
     * Modified by
     * Modified on
     * */
    
    public String readProperty(String propkey) {           
           return prop.getProperty(propkey);
    }
    
    /* Function Decription - Function will call fucntion to read property file if Property Map is null and return the property map [hashmap]     
     * Created by - Sachin Ahuja
     * Created on - 7th April
     * Modified by
     * Modified on
     * */


	public HashMap<String, String> getProperties(String propertyPath) throws FileNotFoundException, IOException {
		prop=new Properties();  
		HashMap<String, String> map = new HashMap<String, String>();
		loadProps(propertyPath);
       	Set<Object> keys = prop.keySet();
	   	for(Object k:keys){
	   		String key = (String)k;
	   		map.put(key, (String)prop.getProperty(key));
	   	}
		return map;
	}
	
} 



