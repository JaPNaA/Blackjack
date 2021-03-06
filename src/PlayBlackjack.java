import java.util.Scanner;

import blackjack.Blackjack;
import utils.Utils;

public class PlayBlackjack {

	static Scanner sc = new Scanner(System.in);

	static int numGames = 0, playerWins = 0, houseWins = 0;
	static double money = 100;

	public static void main(String[] args) {
		System.out.println(
				"Welcome to the game of Blackjack, simplified so my head doesn't explode. Numerical cards have their stated value. Face cards have a value of 10. "
						+ "\nAce has a value of 11, unless that would cause a bust, in which case it is 1. That decision is made automatically and not retroactively."
						+ "\nAll other rules are typical."
						+ "\nYou will be playing against the computer. You start with $100.");

		do {
			playARound();

			Utils.delay(250);

			if (money <= 0) {
				break;
			}
			numGames++;

			printStats();

		} while (Utils.askConfirmByCopyChar('Y'));

		if (money <= 0) {
			Utils.printwln("You have lost all of your money. You must leave this instant.");
		} else {
			System.out.println(
					"\nThank you so much for playing tonight. We hope you enjoyed your time and expect to see you back soon. "
							+ "\nDon't forget to fill out the customer experience survey on your way out!");
		}

	}

	private static void playARound() {
		Utils.printwln("Please enter the amount of money you would like to wager in Game " + numGames + ".");

		double wager = Utils.askDouble();
		Blackjack game = new Blackjack();
		game.play();

		if (game.didPlayerWin()) {
			playerWins++;
			money += wager;
		} else {
			houseWins++;
			money -= wager;
		}
	}

	private static void printStats() {
		Utils.printwln("You have won " + playerWins + " times, while the house was won " + houseWins
				+ " times. You have $" + money + ".");
		System.out
				.println("\nIf you would like to play another game, enter 'Y'. Any other input will exit the session.");
	}
}

/**
 * Improvement List
 * -Exception Handling
 * -GUI
 */
