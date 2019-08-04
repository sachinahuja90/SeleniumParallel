package com.nag.nagp.customException;

/* Class Decription - Class define Framework's own custom exception which can be thrown when File extension is not matched.    
 * Created by - Sachin Ahuja
 * Created on - 15th March
 * Modified by
 * Modified on
 * */
@SuppressWarnings("serial")
public class ElementNotClickable extends Exception { 
	
    public ElementNotClickable(String errorMessage) {
        super("\n"+errorMessage + " Not Clickable");
    }
}