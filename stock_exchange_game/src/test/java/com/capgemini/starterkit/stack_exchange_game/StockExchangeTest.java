package com.capgemini.starterkit.stack_exchange_game;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StockExchangeTest {
	StockExchange stockExchange;
	Clock clock;
	Map<Date, List<Action>> actionsAndDateMap = new HashMap<Date, List<Action>>();

	@Before
	public void setUp() throws ReflectiveOperationException, SecurityException {
		stockExchange = new StockExchange();
		Field actionsAndDateField = stockExchange.getClass().getDeclaredField(
				"actionsAndDateMap");
		actionsAndDateField.setAccessible(true);
		actionsAndDateMap = (Map<Date, List<Action>>) actionsAndDateField.get(stockExchange);
		
		Field clockField = stockExchange.getClass().getDeclaredField(
				"clock");
		clockField.setAccessible(true);
		clock = (Clock) clockField.get(stockExchange);
		
	}

	@Test
	public void startingDateShouldBeTheEqualsToLastDateOnList() {

		Assert.assertFalse(actionsAndDateMap.isEmpty());
		Assert.assertEquals(clock.getDate(), Collections.min(actionsAndDateMap.keySet()));
	}

	@Test
	public void listOfCompanyPricesFromLastPeriodShouldNotBeEmpty() {
		// given
	
		for (int i = 0; i < StockExchange.ACTIONS_MEMORY_DAYS; ++i) {
			stockExchange.getNextWallStreetMorning();
		}
		// when
		List<Double> companyActionPricesFromLastPeriod = stockExchange
				.getCompanyActionPricesFromLastPeriod("KGHM");
		// then
		Assert.assertFalse(companyActionPricesFromLastPeriod.isEmpty());
	}
	@Test
	public void listOfPreviousDatesFromLastPeriodShouldNotBeEmpty() {
		// when
		List<Date> listOfPreviousDates = stockExchange.createListOfPreviousDates();
		// then
		Assert.assertFalse(listOfPreviousDates.isEmpty());
		Assert.assertEquals(stockExchange.ACTIONS_MEMORY_DAYS, listOfPreviousDates.size());
	}


	@Test
	public void shouldGetNotEmptyListOfUpToDateActions() {
		// when
		List<Action> upToDateActions = stockExchange.getCurrentActions();
		// then
		Assert.assertFalse(upToDateActions.isEmpty());

	}

}
