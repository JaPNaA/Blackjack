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

	public String toString() {
		String rank;

		if (number == 1 || number > 10) {
			rank = toStringFace();
		} else {
			rank = Integer.toString(number);
		}

		switch (suit) {
			case Clubs:
				return rank + " of \u2663";
			case Diamonds:
				return rank + " of \u2666";
			case Hearts:
				return rank + " of \u2665";
			case Spades:
				return rank + " of \u2660";
			default:
				throw new Error("Suit is not a suit?");
		}
	}

	public String toStringFace() {
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
			return "Error!";
		}
	}
}
