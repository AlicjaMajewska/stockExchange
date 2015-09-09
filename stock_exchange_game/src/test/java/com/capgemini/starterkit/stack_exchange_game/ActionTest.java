package com.capgemini.starterkit.stack_exchange_game;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActionTest  {
	SimpleDateFormat dateFormat;
	Date date;
	@Before
	public void setUp() throws ParseException{
		dateFormat = new SimpleDateFormat("yyyyMMdd");
		date = dateFormat.parse("20130102");
	}
	
	@Test
	public void shouldLoadDateCorrectly(){
		Calendar calendar = new GregorianCalendar(2013, 0, 02);
		Date expectedDate = calendar.getTime();
		Assert.assertEquals(expectedDate, date);
	}
	

	
}
