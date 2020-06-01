package blackjack;

import java.util.ArrayList;

import card.Card;
import card.Deck;

abstract class AbstractPlayer {

    public final ArrayList<Card> hand = new ArrayList<>();

    protected AbstractPlayer(Deck deck) {
        for (int i = 0; i < 2; i++) {
            hand.add(deck.deal());
        }
    }

    abstract public void printHand();

    public void printEntireHand() {
        for (Card card : hand) {
            System.out.println(card.toString());
        }
    }

}