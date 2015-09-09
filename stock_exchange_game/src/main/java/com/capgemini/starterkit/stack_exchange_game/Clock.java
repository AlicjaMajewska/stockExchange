package com.capgemini.starterkit.stack_exchange_game;

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
