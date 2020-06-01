package blackjack;

import card.*;
import utils.Utils;

public class Player extends AbstractPlayer {

	public Player(Deck deck) {
		super(deck);
	}

	@Override
	public void printHand() {
		Utils.printwln("Your Hand:");
		printEntireHand();
	}
}
