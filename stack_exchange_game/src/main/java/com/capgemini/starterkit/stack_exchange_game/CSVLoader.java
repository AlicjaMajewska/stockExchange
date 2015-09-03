package com.capgemini.starterkit.stack_exchange_game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CSVLoader {
	private BufferedReader dataReader;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	ClassLoader classLoader = getClass().getClassLoader();
	File file = new File(classLoader.getResource("dane.csv").getFile());
	
	public CSVLoader() throws FileNotFoundException {
		dataReader = new BufferedReader(new FileReader(file));
	}

	public ArrayList<Action> createListOfActionsFromFile() throws IOException {
		ArrayList<Action> actionsList = new ArrayList<Action>();
		String currentLine = "";
		while ((currentLine = dataReader.readLine()) != null) {
			Action createdAction = createActionFromLine(currentLine);
			if(createdAction != null){
				actionsList.add(createdAction);
			}

		}

		return actionsList;
	}

	private Action createActionFromLine(String currentLine) {
		String[] actionElement = currentLine.split(",");
		if (actionElement.length == 3) {
			try {
				Action actionToAdd = new Action(actionElement[0],
						dateFormat.parse(actionElement[1]),
						Float.parseFloat(actionElement[2]));
				return actionToAdd;
			} catch (NumberFormatException | ParseException e) {
				System.err
						.println("Couldn't create an action, file line incorrect!");
			}
		}
		return null;
	}

}
