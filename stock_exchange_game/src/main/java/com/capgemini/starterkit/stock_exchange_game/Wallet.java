package com.capgemini.starterkit.stock_exchange_game;

import java.util.ArrayList;
import java.util.List;

public class Wallet {

	private final static int INITIAL_CASH = 10000;
	private Double cash = new Double(INITIAL_CASH);
	private List<OwnedAction> ownedActions = new ArrayList<OwnedAction>();
	private List<OwnedAction> actionsToSell = new ArrayList<OwnedAction>();
	private List<OwnedAction> actionsToBuy = new ArrayList<OwnedAction>();

	public void addActionsToSell(OwnedAction actionToSell) {
		this.actionsToSell.add(actionToSell);
	}

	public void clearMemory() {
		actionsToBuy.clear();
		actionsToSell.clear();
	}

	public List<OwnedAction> realizeSale() {
		cash += calculateSaleValueOfActions(actionsToSell)
				* (1 - Makler.COMMISION);
		ownedActions.removeAll(actionsToSell);
		return actionsToSell;
	}

	public void addActionsToBuy(OwnedAction actionToBuy) {
		this.actionsToBuy.add(actionToBuy);
	}

	public void spendCash(Double cashToSpend) {
		cash -= cashToSpend;
	}

	public List<OwnedAction> realizePurchase() {
		ownedActions.addAll(actionsToBuy);
		return actionsToBuy;
	}

	public Double calculateSaleValueOfActions(List<OwnedAction> actions) {
		Double operationCost = 0.0;
		for (OwnedAction action : actions) {
			operationCost += action.calculateSaleValue();
		}
		return operationCost;
	}

	public Double calculatePurchaseValueOfActions(List<OwnedAction> actions) {
		Double operationCost = 0.0;
		for (OwnedAction action : actions) {
			operationCost += action.calculatePurchasedValue();
		}
		return operationCost;
	}

	public List<OwnedAction> getOwnedActions() {
		return ownedActions;
	}

	public Double getAvailableCash() {
		return cash;
	}

}
