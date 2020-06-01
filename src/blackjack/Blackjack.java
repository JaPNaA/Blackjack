package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

import card.*;
import utils.Utils;

public class Blackjack {

	private final static int TARGET_NUMBER = 21;
	private final static int ACE_HIGH_VALUE = 11;
	private final static int ACE_LOW_VALUE = 1;

	private boolean playerBust, dealerBust, playerJack, dealerJack;

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
			doTurnCycle();
		} while (playerCanContinue);

		Utils.delay(750);
		didPlayerWin = winScreen(user.getHand(), dealer.getHand());
	}

	private void doTurnCycle() {
		userTurn(user.getHand());
		
		if (checkBust(user.getHand())) {
			playerBust = true;
			return;
		} else if (smartSum(user.getHand()) == TARGET_NUMBER) {
			playerJack = true;
			return;
		}

		dealerTurn(dealer.getHand());

		if (checkBust(dealer.getHand())) {
			dealerBust = true;
			return;
		} else if (smartSum(user.getHand()) == TARGET_NUMBER) {
			dealerJack = true;
			return;
		}
	}

	private void userTurn(ArrayList<Card> hand) {
		Utils.printwln("Enter 'H' to hit. Any other input will be interpreted as stand.");
		playerCanContinue = Utils.askConfirmByCopyChar('H');

		if (playerCanContinue) {
			hit(hand);
			Utils.printwln("The sum of your hand is " + smartSum(hand) + ".");
		}
	}

	private void dealerTurn(ArrayList<Card> hand) {
		if (smartSum(hand) <= 16) {
			Utils.printwln("The house draws a ");
			hit(hand);
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

	private boolean winScreen(ArrayList<Card> playerHand, ArrayList<Card> houseHand) {
		boolean playerWin;

		if (playerBust) {
			Utils.printwln("You busted and the house won.");
			playerWin = false;
		} else if (dealerBust) {
			Utils.printwln("The house busted and you won.");
			playerWin = true;
		} else if (playerJack) {
			Utils.printwln("You got a Blackjack! Congratulations.");
			playerWin = true;
		} else if (dealerJack) {
			Utils.printwln("The house got a Blackjack.");
			playerWin = false;
		} else {
			if (TARGET_NUMBER - smartSum(playerHand) < TARGET_NUMBER - smartSum(houseHand)) {
				Utils.printwln("You won by getting closer to BUST_NUMBER than the house.");
				playerWin = true;
			} else {
				Utils.printwln("The house won by getting closer to BUST_NUMBER than you.");
				playerWin = false;

			}
		}

		Utils.printwln("Your hand:");
		printHand(playerHand);
		Utils.printwln("The house's hand:");
		printHand(houseHand);

		return playerWin;
	}

}
