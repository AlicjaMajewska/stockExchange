package com.capgemini.starterkit.exceptions;

import java.util.Date;

public class BadDateException extends Exception{

	public BadDateException (Date date){
		super("No such a date in database, bad date " + date.toString());
	}
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
