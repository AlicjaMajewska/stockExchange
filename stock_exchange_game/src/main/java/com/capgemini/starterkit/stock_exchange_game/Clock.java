package com.capgemini.starterkit.stock_exchange_game;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Clock {
	private Calendar calendar = Calendar.getInstance();
	private Date endDate;

	public Clock(Date startDate, Date endDate) {
		this.calendar.setTime(startDate);
		this.endDate = endDate;

	}
	public List<Date> createListOfPreviousDates() {
		List<Date> actionsMemoryDates = new ArrayList<Date>();
		Calendar temporaryCalendar = Calendar.getInstance();
		temporaryCalendar.setTime(getDate());
		for (int i = 0; i < StockExchange.ACTIONS_MEMORY_DAYS; ++i) {
			temporaryCalendar.roll(Calendar.DATE, false);
			actionsMemoryDates.add(temporaryCalendar.getTime());
		}
		return actionsMemoryDates;
	}

	public void makeNextDay(Map<Date, List<Action>> actionsAndDateMap) {
		if (!endOfData()) {
			calendar.add(Calendar.DATE, 1);
		}
		skipWeekends(actionsAndDateMap);
	}

	private void skipWeekends(Map<Date, List<Action>> actionsAndDateMap) {
		while (!endOfData()
				&& !actionsAndDateMap.containsKey(calendar.getTime())) {
			calendar.add(Calendar.DATE, 1);
		}
	}

	public boolean endOfData() {
		return endDate.equals(getDate());
	}

	public Date getDate() {
		return calendar.getTime();
	}

	public Date getEndDate() {
		return endDate;
	}

}
