package com.capgemini.starterkit.stock_exchange_game;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.starterkit.stock_exchange_game.Action;
import com.capgemini.starterkit.stock_exchange_game.Clock;
import com.capgemini.starterkit.stock_exchange_game.StockExchange;

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
	public void shouldSkipWeekendsListOfDatesWith10Elem() {
		//given
		Date startDate = clock.getDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.add(Calendar.DATE, StockExchange.ACTIONS_MEMORY_DAYS);
		Date startTimeAfterTwoWeeks = calendar.getTime();
		// when
		for (int i = 0; i < (StockExchange.ACTIONS_MEMORY_DAYS - 2 * (StockExchange.ACTIONS_MEMORY_DAYS / 7) ); ++i ) {
			clock.makeNextDay(actionsAndDateMap);
		}
		Date endDate = clock.getDate();
		//then
		Assert.assertEquals(endDate, startTimeAfterTwoWeeks);
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
	public void shouldGetNotEmptyListOfUpToDateActions() {
		// when
		List<Action> upToDateActions = stockExchange.getCurrentActions();
		// then
		Assert.assertFalse(upToDateActions.isEmpty());

	}

}
