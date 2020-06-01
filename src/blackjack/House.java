package blackjack;

import card.*;
import utils.Utils;

public class House extends AbstractPlayer {

	public House(Deck deck) {
		super(deck);
	}

	@Override
	public void printHand() {
		Utils.printwln("The House's Hand:");
		System.out.println(hand.get(0).toString() + "\nHidden Card");
	}

}
