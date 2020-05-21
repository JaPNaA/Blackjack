package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

import card.*;
import utils.Utils;

public class Blackjack {

	private final static int TARGET_NUMBER = 21;
	private final static int ACE_HIGH_VALUE = 11;
	private final static int ACE_LOW_VALUE = 1;

	static Scanner sc = new Scanner(System.in);
	private boolean playerBust, dealerBust, playerJack, dealerJack;
	private String choice;
	private Deck set;

	public boolean playGame() {
		set = new Deck();
		Player user = new Player(set);
		House dealer = new House(set);

		do {
			userTurn(user.getHand());
			if (checkBust(user.getHand())) {
				playerBust = true;
				break;
			} else if (smartSum(user.getHand()) == TARGET_NUMBER) {
				playerJack = true;
				break;
			}

			dealerTurn(dealer.getHand());
			if (checkBust(dealer.getHand())) {
				dealerBust = true;
				break;
			} else if (smartSum(user.getHand()) == TARGET_NUMBER) {
				dealerJack = true;
				break;
			}
		} while (choice.equals("H"));

		Utils.delay(750);
		return winScreen(user.getHand(), dealer.getHand());
	}

	public void userTurn(ArrayList<Card> hand) {
		Utils.printwln("Enter 'H' to hit. Any other input will be interpreted as stand.");
		choice = sc.nextLine();
		choice = choice.toUpperCase();

		if (choice.equals("H")) {
			hit(hand);
			Utils.printwln("The sum of your hand is " + smartSum(hand) + ".");
		}
	}

	public void dealerTurn(ArrayList<Card> hand) {
		if (smartSum(hand) <= 16) {
			Utils.printwln("The house draws a ");
			hit(hand);
		} else {
			Utils.printwln("The house doesn't do anything.");
		}
	}

	public void hit(ArrayList<Card> hand) {
		Card temp = set.deal();

		hand.add(temp);
		System.out.println(temp.toString());
	}

	/**
	 * Automatically sums a hand, reducing values of Aces if the sum is greater than
	 * the target value
	 */
	public int smartSum(ArrayList<Card> hand) {
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

	public void printHand(ArrayList<Card> hand) {
		for (Card c : hand) {
			System.out.println(c.toString());
		}
	}

	public boolean checkBust(ArrayList<Card> hand) {
		return smartSum(hand) > TARGET_NUMBER;
	}

	public boolean winScreen(ArrayList<Card> playerHand, ArrayList<Card> houseHand) {
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
