package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

import card.Deck;

public class Blackjack {

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
			} else if (sum(user.getHand()) == 21) {
				playerJack = true;
				break;
			}

			dealerTurn(dealer.getHand());
			if (checkBust(dealer.getHand())) {
				dealerBust = true;
				break;
			} else if (sum(user.getHand()) == 21) {
				dealerJack = true;
				break;
			}
		} while (choice.equals("H"));

		delay(750);
		return winScreen(user.getHand(), dealer.getHand());
	}

	public void userTurn(ArrayList<BlackjackCard> hand) {
		System.out.println("\nEnter 'H' to hit. Any other input will be interpreted as stand.");
		choice = sc.nextLine();
		choice = choice.toUpperCase();

		if (choice.equals("H")) {
			hit(hand);
			System.out.println("\nThe sum of your hand is " + sum(hand) + ".");
		}
	}

	public void dealerTurn(ArrayList<BlackjackCard> hand) {
		if (sum(hand) <= 16) {
			System.out.println("\nThe house draws a ");
			hit(hand);
		} else {
			System.out.println("\nThe house doesn't do anything.");
		}
	}

	public void hit(ArrayList<BlackjackCard> hand) {
		BlackjackCard temp = set.deal();

		if (sum(hand) + temp.getPlayNumber() > 21 && temp.getPlayNumber() == 11) {
			temp.setPlayNumber(1);
		}

		hand.add(temp);
		System.out.println(temp.toString());
	}

	public int sum(ArrayList<BlackjackCard> hand) {
		int sum = 0;

		for (BlackjackCard c : hand) {
			sum += c.getPlayNumber();
		}

		return sum;
	}

	public void printHand(ArrayList<BlackjackCard> hand) {
		for (BlackjackCard c : hand) {
			System.out.println(c.toString());
		}
	}

	public boolean checkBust(ArrayList<BlackjackCard> hand) {
		if (sum(hand) > 21) {
			return true;
		}
		return false;
	}

	public boolean winScreen(ArrayList<BlackjackCard> playerHand, ArrayList<BlackjackCard> houseHand) {
		boolean playerWin;

		if (playerBust) {
			System.out.println("\nYou busted and the house won.");
			playerWin = false;
		} else if (dealerBust) {
			System.out.println("\nThe house busted and you won.");
			playerWin = true;
		} else if (playerJack) {
			System.out.println("\nYou got a Blackjack! Congratulations.");
			playerWin = true;
		} else if (dealerJack) {
			System.out.println("\nThe house got a Blackjack.");
			playerWin = false;
		} else {
			if (21 - sum(playerHand) < 21 - sum(houseHand)) {
				System.out.println("\nYou won by getting closer to 21 than the house.");
				playerWin = true;
			} else {
				System.out.println("\nThe house won by getting closer to 21 than you.");
				playerWin = false;

			}
		}

		System.out.println("\nYour hand:");
		printHand(playerHand);
		System.out.println("\nThe house's hand:");
		printHand(houseHand);

		return playerWin;
	}

	public void delay(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}

}
