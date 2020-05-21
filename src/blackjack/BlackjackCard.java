package blackjack;

import card.Card;

public class BlackjackCard extends Card {

	private int playNum;

	public BlackjackCard(int x, String s) {
		super(x, s);

		if (x > 10) {
			playNum = 10;
		} else if (x == 1) {
			playNum = 11;
		} else {
			playNum = x;
		}
	}

	public int getPlayNumber() {
		return playNum;
	}

	public void setPlayNumber(int x) {
		if (x > 0 && x <= 10) {
			playNum = x;
		}
	}

}
