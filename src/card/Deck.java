package card;

import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> deck;
	private CardSuit[] suits = { CardSuit.Clubs, CardSuit.Diamonds, CardSuit.Hearts, CardSuit.Spades, };

	public Deck() {
		deck = new ArrayList<Card>();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				deck.add(new Card(j + 1, suits[i]));
			}
		}
	}

	public void printDeck() {
		CardSuit lastSuit = null;

		for (Card c : deck) {
			if (c.suit != lastSuit) {
				lastSuit = c.suit;
				System.out.println(lastSuit);
			}
			System.out.println(c.number);
		}
	}

	public Card deal() {
		return deck.remove((int) (Math.random() * deck.size()));
	}
}
