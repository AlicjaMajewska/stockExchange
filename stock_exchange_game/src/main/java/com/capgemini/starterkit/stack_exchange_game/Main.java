package com.capgemini.starterkit.stack_exchange_game;

import com.capgemini.starterkit.strategyImpl.DummyStrategy;
import com.capgemini.starterkit.strategyImpl.LessDummyStrategy;

public class Main {
	public static void main(String[] args) {
		StockExchange stockExchange = new StockExchange();
		Makler makler = new Makler(stockExchange);
		Player player1 = new Player(makler, new DummyStrategy());
		Player player2 = new Player(makler, new LessDummyStrategy());

		while (!stockExchange.endOfData()) {
			stockExchange.getNextWallStreetMorning();
		}
		System.out.println(" THE END player.getValueOfWallet() - dummyStrategy "
				+ player1.getValueOfWallet());
		System.out.println(" THE END player.getValueOfWallet() - lessDummyStrategy "
				+ player2.getValueOfWallet());

	}
}
