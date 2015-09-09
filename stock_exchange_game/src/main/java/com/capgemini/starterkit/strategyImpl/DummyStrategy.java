package com.capgemini.starterkit.strategyImpl;

import com.capgemini.starterkit.stack_exchange_game.Action;
import com.capgemini.starterkit.stack_exchange_game.Makler;
import com.capgemini.starterkit.stack_exchange_game.OwnedAction;
import com.capgemini.starterkit.strategy.AbstractStrategy;
import com.capgemini.starterkit.strategy.Strategy;

public class DummyStrategy extends AbstractStrategy implements Strategy {

	@Override
	public void determineActionsToBuy() {
		for (Action action : player.getCurrentActions()) {
			if (action.getPrice() * (1 + Makler.COMMISION) > player
					.getPreviousActionPrice(action.getCompanyName())) {
				OwnedAction createdOwnedAction = createOwnedAction(action);
				if (createdOwnedAction.getAmount() != 0) {
					wallet.spendCash(createdOwnedAction
							.calculatePurchasedValue());
					wallet.addActionsToBuy(createdOwnedAction);
				}
			}
		}
	}

	private OwnedAction createOwnedAction(Action action) {
		int maxActionsAmount = (int) (wallet.getAvailableCash() / 2 / (action
				.getPrice() * (1 + Makler.COMMISION)));
		return new OwnedAction(action.getPrice(), action.getCompanyName(),
				maxActionsAmount > 0 ? maxActionsAmount : 0);
	}

	@Override
	public void determineActionsToSell() {
		for (OwnedAction action : wallet.getOwnedActions()) {
			if (action.getPurchasePrice() < player
					.getCurrentPriceOfAction(action.getCompanyName())
					* (1 - Makler.COMMISION)) {
				action.setSalePrice(player.getCurrentPriceOfAction(action
						.getCompanyName()));
				wallet.addActionsToSell(action);
			}
		}
	}

}
