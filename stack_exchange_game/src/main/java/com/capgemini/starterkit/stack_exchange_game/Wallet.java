package com.capgemini.starterkit.stack_exchange_game;

import java.util.ArrayList;
import java.util.List;

public class Wallet {

	private final static int INITIAL_CASH = 10000;
	private int cash;
	private List<PossessedAction> possessedActions;

	Wallet() {
		cash = INITIAL_CASH;
		possessedActions = new ArrayList<PossessedAction>();
	}

	public int getCash() {
		return cash;
	}

	public void addPossessedAction(float purchasePrice, String companyName,
			int amount) {
		possessedActions.add(new PossessedAction(purchasePrice, companyName,
				amount));
	}

	public void removePossessedAction(float purchasePrice, String companyName,
			int amount) {
		possessedActions.remove(new PossessedAction(purchasePrice, companyName,
				amount));

	}

	public List<PossessedAction> getPossessedActions() {
		return possessedActions;
	}

}
