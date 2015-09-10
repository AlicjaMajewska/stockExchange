package com.capgemini.starterkit.stock_exchange_game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVLoader {
	private BufferedReader dataReader;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource("dane.csv").getFile());

	public CSVLoader() throws FileNotFoundException {
		dataReader = new BufferedReader(new FileReader(file));
	}

	public Map<Date, List<Action>> createListOfActionsFromFile()
			throws IOException {
		Map<Date, List<Action>> actionsAndDatesMap = new HashMap<Date, List<Action>>();
		String currentLine = "";
		
		while ((currentLine = dataReader.readLine()) != null) {
			addActionOnMap(actionsAndDatesMap, createDateAndActionFromLine(currentLine));

		}

		return actionsAndDatesMap;
	}

	private void addActionOnMap(Map<Date, List<Action>> actionsAndDatesMap,
			DateAndAction loadedDateAndAction) {
		if ((loadedDateAndAction != null && !isDatePresentOnMap(
				actionsAndDatesMap, loadedDateAndAction))) {
			
			putNewDateOnMap(actionsAndDatesMap, loadedDateAndAction);
		}
		if (loadedDateAndAction != null
				&& isDatePresentOnMap(actionsAndDatesMap,
						loadedDateAndAction)) {
			
			addActionToExistingDate(actionsAndDatesMap, loadedDateAndAction);
		}
	}

	private void putNewDateOnMap(Map<Date, List<Action>> actionsAndDatesMap,
			DateAndAction loadedDateAndAction) {
		ArrayList<Action> actionsList = new ArrayList<Action>();
		actionsAndDatesMap.put(loadedDateAndAction.getDate(), actionsList);
	}

	private void addActionToExistingDate(
			Map<Date, List<Action>> actionsAndDatesMap,
			DateAndAction loadedDateAndAction) {
		actionsAndDatesMap.get(loadedDateAndAction.getDate()).add(loadedDateAndAction.getAction());
	}


	private boolean isDatePresentOnMap(Map<Date, List<Action>> actionsAndDatesMap,
			DateAndAction loadedDateAndAction) {
		return actionsAndDatesMap.containsKey(loadedDateAndAction.getDate());
	}

	private DateAndAction createDateAndActionFromLine(String currentLine) {
		String[] actionElement = currentLine.split(",");
		if (actionElement.length == 3) {
			try {
				return new DateAndAction(
						actionElement[0], 
						dateFormat.parse(actionElement[1]),
						Double.parseDouble(actionElement[2]));
			} catch (NumberFormatException | ParseException e) {
				System.err
						.println("Couldn't create an action, file line incorrect!");
			}
		}
		return null;
	}

}

class DateAndAction {

	private Date date;
	private Action action;

	public DateAndAction(String companyName, Date date, Double price) {
		super();
		this.date = date;
		this.action = new Action(companyName, price);
	}

	public Date getDate() {
		return date;
	}

	public Action getAction() {
		return action;
	}

}
