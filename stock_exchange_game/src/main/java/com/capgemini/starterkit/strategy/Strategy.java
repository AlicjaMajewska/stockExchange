package com.capgemini.starterkit.strategy;

import com.capgemini.starterkit.stock_exchange_game.Player;

public interface Strategy {
	public void determineActionsToBuy();
	
	public void determineActionsToSell();

	public void attachPlayer(Player player);
}
