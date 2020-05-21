package blackjack;

import java.util.ArrayList;

import card.Deck;
import utils.Utils;

public class Player {

	private ArrayList<BlackjackCard> playerHand;

	public Player(Deck deck) {
		playerHand = new ArrayList<BlackjackCard>();

		Utils.printwln("Your Hand:");
		playerHand.add(deck.deal());
		System.out.println(playerHand.get(0).toString());

		BlackjackCard temp = deck.deal();

		if (playerHand.get(0).getPlayNumber() == 11 && temp.getPlayNumber() == 11) {
			temp.setPlayNumber(1);
		}

		playerHand.add(temp);
		System.out.println(playerHand.get(1).toString());
	}

	public ArrayList<BlackjackCard> getHand() {
		return playerHand;
	}
}
