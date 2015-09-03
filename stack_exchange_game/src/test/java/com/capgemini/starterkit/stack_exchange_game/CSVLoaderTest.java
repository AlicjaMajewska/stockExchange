package com.capgemini.starterkit.stack_exchange_game;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
	public void shouldReturnNotEmptyListOfActions() throws IOException {
		//when
		ArrayList<Action> actions = loader.createListOfActionsFromFile();
		//then
		Assert.assertFalse(actions.isEmpty());
		
	}

}
