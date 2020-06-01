package card;

import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> deck;

	public Deck() {
		deck = new ArrayList<Card>();

		for (CardSuit suit : CardSuit.ARRAY) {
			for (int j = 0; j < 13; j++) {
				deck.add(new Card(j + 1, suit));
			}
		}
	}

	public Card deal() {
		return deck.remove((int) (Math.random() * deck.size()));
	}
}
