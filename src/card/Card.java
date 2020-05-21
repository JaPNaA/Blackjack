package card;

public class Card {

	public static final int ACE = 1;
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;

	protected int number;
	protected String suit;

	public Card(int x, String s) {
		number = x;
		suit = s;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int x) {
		if (x > 0 && x <= 13) {
			number = x;
		}
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String s) {
		if (s.equals("Clubs") || s.equals("Diamonds") || s.equals("Hearts") || s.equals("Spades")) {
			suit = s;
		}
	}

	public boolean equals(Card b) {
		if (this.getNumber() == b.getNumber() && this.getSuit().equals(b.getSuit())) {
			return true;
		}
		return false;
	}

	public String toString() {
		String rank;

		if (number == 1 || number > 10) {
			rank = toStringFace();
		} else {
			rank = Integer.toString(number);
		}

		if (suit.equals("Clubs")) {
			return rank + " of \u2663";
		} else if (suit.equals("Diamonds")) {
			return rank + " of \u2666";
		} else if (suit.equals("Hearts")) {
			return rank + " of \u2665";
		}
		return rank + " of \u2660";
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
