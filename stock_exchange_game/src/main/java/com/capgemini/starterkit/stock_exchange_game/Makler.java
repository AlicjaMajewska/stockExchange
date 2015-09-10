package com.capgemini.starterkit.stock_exchange_game;

import java.util.ArrayList;
import java.util.List;

public class Makler {

	// Observer of stack exchagne and subject for players

	private List<Player> playerObservers = new ArrayList<Player>();
	private List<Action> currentActions;
	private StockExchange stockExchange;
	public static final double COMMISION = 0.005;

	public Makler(StockExchange stackExchange) {
		this.stockExchange = stackExchange;
		this.stockExchange.attachMakler(this);
	}

	void attachPlayer(Player newPlayer) {
		playerObservers.add(newPlayer);
	}
	public void tradeWithAllPlayers(){
		for (Player player : playerObservers) {
			player.trade();
		}
	}
	

	public void notifyAllPlayerObservers() {
		for (Player playerObserver : playerObservers) {
			playerObserver.getCurrentActions();
		}
	}

	public void updateActions() {
		currentActions = stockExchange.getCurrentActions();
	}

	public List<Action> getCurrentActions() {
		return currentActions;
	}

	public List<Double> getActionPricesFromLastPeriod(String companyName) {
		return stockExchange.getCompanyActionPricesFromLastPeriod(companyName);
	}

	public void buyFromPlayer(List<OwnedAction> actionsToSellByPlayer) {
		for (OwnedAction actionToSell : actionsToSellByPlayer) {
			stockExchange.buyActions(actionToSell.getCompanyName(), actionToSell.getAmount());
		}
	}

	public void sellToPlayer(List<OwnedAction> actionsToBuyByPlayer) {
		for (OwnedAction actionToBuy : actionsToBuyByPlayer) {
			stockExchange.sellActions(actionToBuy.getCompanyName(), actionToBuy.getAmount());
		}
	}

}
