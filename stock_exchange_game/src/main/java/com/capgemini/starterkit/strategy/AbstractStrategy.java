package com.capgemini.starterkit.strategy;

import com.capgemini.starterkit.stack_exchange_game.Player;
import com.capgemini.starterkit.stack_exchange_game.Wallet;

public abstract class AbstractStrategy {
	protected Player player;
	protected Wallet wallet;

	public void attachPlayer(Player player){
		this.player = player;
		this.wallet = player.getWallet();
	}
	

}
