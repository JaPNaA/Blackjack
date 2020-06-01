package card;

public class Card {

	public static final int ACE = 1;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;

	public int number;
	public CardSuit suit;

	public Card(int x, CardSuit s) {
		number = x;
		suit = s;
	}

	public boolean equals(Card b) {
		return this.number == b.number && this.suit == b.suit;
	}

	@Override
	public String toString() {
		String rank = getFaceString();
		String suit = getSuitString();
		return suit + " " + rank;
	}

	private String getFaceString() {
		switch (number) {
		case ACE:
			return "Ace";
		case JACK:
			return "Jack";
		case QUEEN:
			return "Queen";
		case KING:
			return "King";
		default:
			return Integer.toString(number);
		}
	}

	private String getSuitString() {
		switch (suit) {
		case Clubs:
			return "\u2663";
		case Diamonds:
			return "\u2666";
		case Hearts:
			return "\u2665";
		case Spades:
			return "\u2660";
		default:
			throw new Error("Suit is not a suit?");
		}
	}

}
