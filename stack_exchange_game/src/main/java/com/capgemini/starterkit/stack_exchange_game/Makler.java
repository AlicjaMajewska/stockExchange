package com.capgemini.starterkit.stack_exchange_game;

import java.util.ArrayList;
import java.util.List;

public class Makler {
	
	//Observer of stack exchagne and subject for players

	private List<Player> players = new ArrayList<Player>();
	private List<Action> upToDateActions;
	private StackExchange stackExchange;

	public Makler(StackExchange stackExchange) {
//		players.addAll(newPlayers);
		this.stackExchange = stackExchange;
		this.stackExchange.attachMakler(this);
	}
//	public Makler(List<Player> newPlayers, StackExchange stackExchange) {
//		players.addAll(newPlayers);
//		this.stackExchange = stackExchange;
//		this.stackExchange.attachMakler(this);
//	}
	
	

	void attachPlayer(Player newPlayer) {
		players.add(newPlayer);
	}

	public void updateActions(List<Action> actionsFromStachExchange) {
		upToDateActions = actionsFromStachExchange;
	}
	
	
}
