package com.capgemini.starterkit.stack_exchange_game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class StackExchange {

	private List<Action> actions;
	private CSVLoader loader;
	private Calendar calendar = Calendar.getInstance();
	private Date endDate;
	private List<Makler> maklerObservers = new ArrayList<>();
	public static final int ACTIONS_MEMORY_DAYS = 10;

	public StackExchange() {
		try {
			CSVLoader loader = new CSVLoader();
			actions = loader.createListOfActionsFromFile();
			Collections.sort(actions);
			calendar.setTime(actions.get(0).getDate());
			endDate = actions.get(actions.size() - 1).getDate();

		} catch (IOException exception) {
			System.err.println("File not found!");
			exception.printStackTrace();
			System.exit(1);
		}
	}

	public void getNextWallStreetMorning() {
		notifyAllMaklerObservers();
		makeNextDay();
	}

	public void attachMakler(Makler newMakler) {
		maklerObservers.add(newMakler);
	}

	public void notifyAllMaklerObservers() {
		for (Makler makler : maklerObservers) {
			makler.updateActions(getUpToDateActions());
		}
	}

	public Date getDate() {
		return calendar.getTime();
	}

	private void makeNextDay() {
		if (!endOfData()) {
			calendar.add(Calendar.DATE, 1);
		}
	}

	public boolean endOfData() {
		return endDate.equals(getDate());
	}

	public List<Float> getCompanyActionPricesFromLastPeriod(String companyName) {
		List<Float> companyActionPrices = new ArrayList<Float>();
		List<Date> listOfPreviousDates = createListOfPreviousDates();
		for (Action action : actions) {
			if (listOfPreviousDates.contains(action.getDate())
					&& action.getCompanyName().equals(companyName)) {
				companyActionPrices.add(action.getPrice());
			}
		}
		return companyActionPrices;
	}

	public List<Date> createListOfPreviousDates() {
		List<Date> actionsMemoryDates = new ArrayList<Date>();
		Calendar temporaryCalendar = Calendar.getInstance();
		temporaryCalendar.setTime(getDate());
		for (int i = 0; i < ACTIONS_MEMORY_DAYS; ++i) {
			temporaryCalendar.roll(Calendar.DATE, false);
			actionsMemoryDates.add(temporaryCalendar.getTime());
		}
		return actionsMemoryDates;
	}

	public List<Action> getUpToDateActions() {
		List<Action> upToDateActions = new ArrayList<Action>();
		for (Action action : actions) {
			switch (getDate().compareTo(action.getDate())) {
			case -1:
				break;
			case 0:
				upToDateActions.add(action);
				break;
			case 1:
				return upToDateActions;
			}
		}
		return upToDateActions;
	}

}
