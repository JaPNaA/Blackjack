package blackjack;

import java.util.ArrayList;

import card.*;
import utils.Utils;

public class Blackjack {

	private final static int TARGET_NUMBER = 21;
	private final static int ACE_HIGH_VALUE = 11;
	private final static int ACE_LOW_VALUE = 1;

	private Deck set = new Deck();
	private Player user = new Player(set);
	private House dealer = new House(set);

	private boolean playerCanContinue = true;
	private boolean didPlayerWin = false;

	public boolean didPlayerWin() {
		return didPlayerWin;
	}

	public void play() {
		do {
			try {
				doTurnCycle();
			} catch (GameEndException exception) {
				break;
			}
		} while (playerCanContinue);

		Utils.delay(750);
		winScreen(user.getHand(), dealer.getHand());
	}

	private void doTurnCycle() {
		userTurn();
		checkUserHand();

		dealerTurn();
		checkDealerHand();
	}

	private void userTurn() {
		Utils.printwln("Enter 'H' to hit. Any other input will be interpreted as stand.");
		playerCanContinue = Utils.askConfirmByCopyChar('H');

		if (playerCanContinue) {
			hit(user.getHand());
			Utils.printwln("The sum of your hand is " + smartSum(user.getHand()) + ".");
		}
	}

	private void checkUserHand() {
		if (checkBust(user.getHand())) {
			Utils.printwln("You busted and the house won.");
			didPlayerWin = false;
			endGame();
		} else if (smartSum(user.getHand()) == TARGET_NUMBER) {
			Utils.printwln("You got a Blackjack! Congratulations.");
			didPlayerWin = true;
			endGame();
		}
	}

	private void checkDealerHand() {
		if (checkBust(dealer.getHand())) {
			Utils.printwln("The house busted and you won.");
			didPlayerWin = true;
			endGame();
		} else if (smartSum(user.getHand()) == TARGET_NUMBER) {
			Utils.printwln("The house got a Blackjack.");
			didPlayerWin = false;
			endGame();
		}
	}

	private void endGame() {
		throw new GameEndException();
	}

	private void dealerTurn() {
		if (smartSum(dealer.getHand()) <= 16) {
			Utils.printwln("The house draws a ");
			hit(dealer.getHand());
		} else {
			Utils.printwln("The house doesn't do anything.");
		}
	}

	private void hit(ArrayList<Card> hand) {
		Card temp = set.deal();

		hand.add(temp);
		System.out.println(temp.toString());
	}

	/**
	 * Automatically sums a hand, reducing values of Aces if the sum is greater than
	 * the target value
	 */
	private int smartSum(ArrayList<Card> hand) {
		int sum = 0;
		int acesCount = 0;

		for (Card card : hand) {
			if (card.getNumber() == Card.ACE) {
				acesCount++;
			}

			sum += getCardValue(card);
		}

		while (sum > TARGET_NUMBER && acesCount > 0) {
			sum -= ACE_HIGH_VALUE;
			sum += ACE_LOW_VALUE;
		}

		return sum;
	}

	private int getCardValue(Card card) {
		if (card.getNumber() == Card.ACE) {
			return 11;
		}
		return Math.min(card.getNumber(), 10);
	}

	private void printHand(ArrayList<Card> hand) {
		for (Card c : hand) {
			System.out.println(c.toString());
		}
	}

	private boolean checkBust(ArrayList<Card> hand) {
		return smartSum(hand) > TARGET_NUMBER;
	}

	private void winScreen(ArrayList<Card> playerHand, ArrayList<Card> houseHand) {
		// if player could have continued;
		// that means the game was broken at some point
		if (!playerCanContinue) {
			if (TARGET_NUMBER - smartSum(playerHand) < TARGET_NUMBER - smartSum(houseHand)) {
				Utils.printwln("You won by getting closer to " + TARGET_NUMBER + " than the house.");
				didPlayerWin = true;
			} else {
				Utils.printwln("The house won by getting closer to " + TARGET_NUMBER + " than you.");
				didPlayerWin = false;
			}
		}

		Utils.printwln("Your hand:");
		printHand(playerHand);
		Utils.printwln("The house's hand:");
		printHand(houseHand);
	}

}

/**
 * Thrown when the game ends
 * 
 * Note: the class actually extends and Error, although it's more fitting to
 * call it an exeption.
 * 
 * The reason is simply because I don't like how Java handles Exceptions.
 */
class GameEndException extends Error {
	private static final long serialVersionUID = 1L;
}
