package com.capgemini.starterkit.stack_exchange_game;

public class Player {
	
	private Makler makler;

	Player (Makler makler) {
		this.makler = makler;
		this.makler.attachPlayer(this);
	}
	
	
	
	
	
}
