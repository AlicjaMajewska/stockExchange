package com.capgemini.starterkit.stack_exchange_game;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackExchangeTest {
	StackExchange stackExchange;
	List<Action> actions = new ArrayList<Action>();

	@Before
	public void setUp() throws ReflectiveOperationException, SecurityException {
		stackExchange = new StackExchange();
		Field actionsField = stackExchange.getClass().getDeclaredField(
				"actions");
		actionsField.setAccessible(true);
		actions = (List<Action>) actionsField.get(stackExchange);

	}

	@Test
	public void startingDateShouldBeTheEqualsToLastDateOnList() {

		Assert.assertFalse(actions.isEmpty());
		Assert.assertEquals(stackExchange.getDate(), actions.get(0).getDate());
	}

	@Test
	public void listOfCompanyPricesFromLastPeriodShouldNotBeEmpty() {
		// given
	
		for (int i = 0; i < stackExchange.ACTIONS_MEMORY_DAYS; ++i) {
			stackExchange.getNextWallStreetMorning();
		}
		// when
		List<Float> companyActionPricesFromLastPeriod = stackExchange
				.getCompanyActionPricesFromLastPeriod("KGHM");
		// then
		Assert.assertFalse(companyActionPricesFromLastPeriod.isEmpty());
	}
	@Test
	public void listOfPreviousDatesFromLastPeriodShouldNotBeEmpty() {
		// when
		List<Date> listOfPreviousDates = stackExchange.createListOfPreviousDates();
		// then
		Assert.assertFalse(listOfPreviousDates.isEmpty());
		Assert.assertEquals(stackExchange.ACTIONS_MEMORY_DAYS, listOfPreviousDates.size());
	}

	@Test
	public void firstDateShouldBeTheYoungest() {

		Assert.assertFalse(actions.isEmpty());
		Assert.assertEquals(
				-1,
				actions.get(0).getDate()
						.compareTo(actions.get(actions.size() - 1).getDate()));
	}

	@Test
	public void shouldGetNotEmptyListOfUpToDateActions() {
		// when
		List<Action> upToDateActions = stackExchange.getUpToDateActions();
		// then
		Assert.assertFalse(upToDateActions.isEmpty());

	}

}
