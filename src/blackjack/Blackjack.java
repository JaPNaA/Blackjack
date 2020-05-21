package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

import card.Deck;
import utils.Utils;

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

		Utils.delay(750);
		return winScreen(user.getHand(), dealer.getHand());
	}

	public void userTurn(ArrayList<BlackjackCard> hand) {
		Utils.printwln("Enter 'H' to hit. Any other input will be interpreted as stand.");
		choice = sc.nextLine();
		choice = choice.toUpperCase();

		if (choice.equals("H")) {
			hit(hand);
			Utils.printwln("The sum of your hand is " + sum(hand) + ".");
		}
	}

	public void dealerTurn(ArrayList<BlackjackCard> hand) {
		if (sum(hand) <= 16) {
			Utils.printwln("The house draws a ");
			hit(hand);
		} else {
			Utils.printwln("The house doesn't do anything.");
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
		return sum(hand) > 21;
	}

	public boolean winScreen(ArrayList<BlackjackCard> playerHand, ArrayList<BlackjackCard> houseHand) {
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
			if (21 - sum(playerHand) < 21 - sum(houseHand)) {
				Utils.printwln("You won by getting closer to 21 than the house.");
				playerWin = true;
			} else {
				Utils.printwln("The house won by getting closer to 21 than you.");
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
