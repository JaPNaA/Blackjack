package blackjack;

import java.util.ArrayList;

import card.Deck;
import utils.Utils;

public class House {

	private ArrayList<BlackjackCard> houseHand;

	public House(Deck deck) {
		houseHand = new ArrayList<BlackjackCard>();

		Utils.printwln("The House's Hand:");
		houseHand.add(deck.deal());
		System.out.println(houseHand.get(0).toString() + "\nHidden Card");

		BlackjackCard temp = deck.deal();

		if (houseHand.get(0).getPlayNumber() == 11 && temp.getPlayNumber() == 11) {
			temp.setPlayNumber(1);
		}

		houseHand.add(temp);
	}

	public ArrayList<BlackjackCard> getHand() {
		return houseHand;
	}
}
