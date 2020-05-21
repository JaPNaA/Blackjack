package blackjack;

import java.util.ArrayList;

import card.*;
import utils.Utils;

public class Player {

	private ArrayList<Card> playerHand;

	public Player(Deck deck) {
		playerHand = new ArrayList<Card>();

		Utils.printwln("Your Hand:");
		playerHand.add(deck.deal());
		System.out.println(playerHand.get(0).toString());

		Card temp = deck.deal();

		playerHand.add(temp);
		System.out.println(playerHand.get(1).toString());
	}

	public ArrayList<Card> getHand() {
		return playerHand;
	}
}
