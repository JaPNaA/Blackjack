package blackjack;

import java.util.ArrayList;

import card.*;
import utils.Utils;

public class House {

	private ArrayList<Card> houseHand;

	public House(Deck deck) {
		houseHand = new ArrayList<Card>();

		Utils.printwln("The House's Hand:");
		houseHand.add(deck.deal());
		System.out.println(houseHand.get(0).toString() + "\nHidden Card");

		Card temp = deck.deal();

		houseHand.add(temp);
	}

	public ArrayList<Card> getHand() {
		return houseHand;
	}
}
