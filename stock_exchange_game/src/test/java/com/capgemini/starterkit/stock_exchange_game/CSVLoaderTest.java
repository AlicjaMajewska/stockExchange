package com.capgemini.starterkit.stock_exchange_game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.capgemini.starterkit.stock_exchange_game.Action;
import com.capgemini.starterkit.stock_exchange_game.CSVLoader;

public class CSVLoaderTest {

	private CSVLoader loader;

	@Before
	public void setUp() {
		try {
			loader = new CSVLoader();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void shouldReturnNotEmptyMapOfDatesAndListOfActions()
			throws IOException {
		// when
		Map<Date, List<Action>> createListOfActionsFromFile = loader
				.createListOfActionsFromFile();
		// then
		Assert.assertFalse(createListOfActionsFromFile.isEmpty());

	}

	@Test
	public void shouldReturnNotEmptyListOfActionsInMap() throws IOException {
		// when
		Map<Date, List<Action>> createListOfActionsFromFile = loader
				.createListOfActionsFromFile();
		// then
		Assert.assertFalse(createListOfActionsFromFile.isEmpty());
		for (Map.Entry<Date, List<Action>> entry : createListOfActionsFromFile
				.entrySet()) {

			List<Action> actions = entry.getValue();
			Assert.assertFalse(actions.isEmpty());

		}

	}

}
