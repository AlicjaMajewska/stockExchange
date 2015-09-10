package com.capgemini.starterkit.stock_exchange_game;

import java.util.List;

import com.capgemini.starterkit.strategy.Strategy;

public class Player {

	private Makler makler;
	private Wallet wallet = new Wallet();
	private Strategy strategy;

	Player(Makler makler, Strategy strategy) {
		this.makler = makler;
		this.makler.attachPlayer(this);
		this.strategy = strategy;
		this.strategy.attachPlayer(this);
	}

	public void trade() {
		sellActions();
		buyActions();
		wallet.clearMemory();
	}

	public void sellActions() {
		strategy.determineActionsToSell();
		makler.buyFromPlayer(wallet.realizeSale());
	}

	public void buyActions() {
		strategy.determineActionsToBuy();
		makler.sellToPlayer(wallet.realizePurchase());
	}

	public List<Action> getCurrentActions() {
		return makler.getCurrentActions();
	}

	public Double getCurrentPriceOfAction(String companyName) {
		for (Action action : makler.getCurrentActions()) {
			if (companyName.equals(action.getCompanyName())) {
				return action.getPrice();
			}
		}
		return null;
	}

	public Double getAverageActionPriceFromLastPeriod(String companyName) {
		Double sum = new Double(0);
		List<Double> actionPricesFromLastPeriod = getActionPricesFromLastPeriod(companyName);
		for (Double price : actionPricesFromLastPeriod) {
			sum += price;
		}
		return sum != 0 ? sum / actionPricesFromLastPeriod.size() : null;
	}

	public List<Double> getActionPricesFromLastPeriod(String companyName) {
		return makler.getActionPricesFromLastPeriod(companyName);
	}

	public Double getPreviousActionPrice(String companyName) {
		return makler.getActionPricesFromLastPeriod(companyName).get(0);
	}

	public Double getValueOfWallet() {
		return wallet.getAvailableCash()
				+ calculateCurrentValueOfActions(wallet.getOwnedActions());
	}

	public Wallet getWallet() {
		return wallet;
	}

	private Double calculateCurrentValueOfActions(List<OwnedAction> ownedActions) {
		Double currentValue = 0.0;
		for (OwnedAction ownedAction : ownedActions) {
			currentValue += ownedAction.getAmount()
					* getCurrentPriceOfAction(ownedAction.getCompanyName());
		}
		return currentValue;
	}
}
