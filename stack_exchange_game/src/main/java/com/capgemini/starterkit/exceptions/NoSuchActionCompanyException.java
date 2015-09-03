package com.capgemini.starterkit.exceptions;


public class NoSuchActionCompanyException extends Exception{

	public NoSuchActionCompanyException (String companyName){
		super("No such a company name in database, bad name " + companyName);
	}
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
