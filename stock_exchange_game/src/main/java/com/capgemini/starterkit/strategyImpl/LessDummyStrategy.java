package com.capgemini.starterkit.strategyImpl;


import java.util.List;

import com.capgemini.starterkit.stock_exchange_game.Action;
import com.capgemini.starterkit.stock_exchange_game.Makler;
import com.capgemini.starterkit.stock_exchange_game.OwnedAction;
import com.capgemini.starterkit.strategy.AbstractStrategy;
import com.capgemini.starterkit.strategy.Strategy;

public class LessDummyStrategy extends AbstractStrategy implements Strategy {

//	@Override
	public void determineActionsToBuy() {
		List<Action> currentActions = player.getCurrentActions();
		int amountOfActionsToBuy = countProfitableActions(currentActions);
		for (Action action : currentActions) {
			if (action.getPrice() * (1 + Makler.COMMISION) > player
					.getPreviousActionPrice(action.getCompanyName())) {
				OwnedAction createdOwnedAction = createOwnedAction(action, amountOfActionsToBuy);
				if(createdOwnedAction.getAmount() != 0){
					wallet.spendCash(createdOwnedAction.calculatePurchasedValue());
					wallet.addActionsToBuy(createdOwnedAction);
				}
			}
		}
	}

	private int countProfitableActions(List<Action> currentActions) {
		int amountOfActionsToBuy = 0;
		for (Action action : currentActions) {
			if (action.getPrice() * (1 + Makler.COMMISION) > player
					.getPreviousActionPrice(action.getCompanyName())) {
				++amountOfActionsToBuy;
			}
		}
		return amountOfActionsToBuy;
	}

	private OwnedAction createOwnedAction(Action action, int amountOfActionsToBuy) {
		int maxActionsAmount = (int) (wallet.getAvailableCash()  / amountOfActionsToBuy / (action
				.getPrice() * (1 + Makler.COMMISION)));
		return new OwnedAction(action.getPrice(), action.getCompanyName(),
				maxActionsAmount > 0 ? maxActionsAmount : 0);
	}

//	@Override
	public void determineActionsToSell() {
		for (OwnedAction action : wallet.getOwnedActions()) {
			if (action.getPurchasePrice()  < player
					.getCurrentPriceOfAction(action.getCompanyName()) * (1 - Makler.COMMISION)) {
				action.setSalePrice(player.getCurrentPriceOfAction(action
						.getCompanyName()));
				wallet.addActionsToSell(action);
			}
		}
	}
}
