package com.capgemini.starterkit.stack_exchange_game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StockExchange {

	private Map<Date, List<Action>> actionsAndDateMap;
	private CSVLoader loader;
	private Clock clock;
	private List<Makler> maklerObservers = new ArrayList<Makler>();
	public static final int ACTIONS_MEMORY_DAYS = 10;

	public StockExchange() {
		try {
			CSVLoader loader = new CSVLoader();
			actionsAndDateMap = loader.createListOfActionsFromFile();
			clock = new Clock(Collections.min(actionsAndDateMap.keySet()),
					Collections.max(actionsAndDateMap.keySet()));

		} catch (IOException exception) {
			System.err.println("File not found!");
			exception.printStackTrace();
			System.exit(1);
		}
	}

	public void getNextWallStreetMorning() {
		notifyAllMaklerObservers();
		tradeWithMaklerObesrvers();
		clock.makeNextDay(actionsAndDateMap);
	}

	public void notifyAllMaklerObservers() {
		for (Makler makler : maklerObservers) {
			makler.updateActions();
		}
	}

	public void tradeWithMaklerObesrvers() {
		for (Makler makler : maklerObservers) {
			makler.tradeWithAllPlayers();
		}
	}

	public void attachMakler(Makler newMakler) {
		maklerObservers.add(newMakler);
	}

	public List<Double> getCompanyActionPricesFromLastPeriod(String companyName) {
		List<Double> companyActionPrices = new ArrayList<Double>();
		List<Date> listOfPreviousDates = createListOfPreviousDates();

		for (Date date : listOfPreviousDates) {
			List<Action> actionsInSpecifiedDay = actionsAndDateMap.get(date);
			if (actionsInSpecifiedDay != null) {
				for (Action action : actionsInSpecifiedDay) {
					if (companyName.equals(action.getCompanyName())) {
						companyActionPrices.add(action.getPrice());
					}
				}
			}
		}
		return companyActionPrices;
	}

	public List<Date> createListOfPreviousDates() {
		List<Date> actionsMemoryDates = new ArrayList<Date>();
		Calendar temporaryCalendar = Calendar.getInstance();
		temporaryCalendar.setTime(clock.getDate());
		for (int i = 0; i < ACTIONS_MEMORY_DAYS; ++i) {
			temporaryCalendar.roll(Calendar.DATE, false);
			actionsMemoryDates.add(temporaryCalendar.getTime());
		}
		return actionsMemoryDates;
	}

	public void sellActions(String companyName, int amount) {
		// empty since we don't need to store this information to unable
		// purchase to another player because of endless amount of actions
		// assumed
		// nor do we really affect the stock exchange by selling or buying
	}

	public void buyActions(String companyName, int amount) {
		// empty since we don't need to store this information to unable
		// purchase to another player because of endless amount of actions
		// assumed
		// nor do we really affect the stock exchange by selling or buying
	}

	public List<Action> getCurrentActions() {
		return actionsAndDateMap.get(clock.getDate());
	}

	public boolean endOfData() {
		return clock.endOfData();
	}

}
