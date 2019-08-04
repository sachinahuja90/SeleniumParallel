package com.nag.nagp.customException;

import com.nag.nagp.reporterLogger.ReportFactory;

/* Class Decription - Class define Framework's own custom exception which can be thrown when File extension is not matched.    
 * Created by - Sachin Ahuja
 * Created on - 15th March
 * Modified by
 * Modified on
 * */
@SuppressWarnings("serial")
public class ZipCreationException extends Exception { 
	
    public ZipCreationException(String errorMessage) {
        super("\nError while creating ZIP file for folder :  "+errorMessage);
        ReportFactory.getInstance().error("Error while creating ZIP file for folder : "+errorMessage);
    }
}