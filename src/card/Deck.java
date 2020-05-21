package card;

import java.util.ArrayList;

public class Deck {

	private ArrayList<Card> deck;
	private String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };

	public Deck() {
		deck = new ArrayList<Card>();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				deck.add(new Card(j + 1, suits[i]));
			}
		}
	}

	public void printDeck() {
		String lastSuit = null;

		for (Card c : deck) {
			if (!c.getSuit().equals(lastSuit)) {
				lastSuit = c.getSuit();
				System.out.println(lastSuit);
			}
			System.out.println(c.getNumber());
		}
	}

	public Card deal() {
		return deck.remove((int) (Math.random() * deck.size()));
	}
}
