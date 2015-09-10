package com.capgemini.starterkit.stock_exchange_game;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClockTest {
	Clock clock;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	@Before 
	public void setUp() throws ParseException{
		Date startDate = dateFormat.parse("20150901");
		Date endDate = dateFormat.parse("20150921");
		clock = new Clock (startDate, endDate );
	}
	
	@Test
	public void shouldReturn14DatesOnList() {
		//when
		List<Date> listOfPreviousDates = clock.createListOfPreviousDates();
		//then
		Assert.assertEquals(14, listOfPreviousDates.size());
		
	}

}
