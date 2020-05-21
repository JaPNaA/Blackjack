# Blackjack

Forked from https://github.com/BlueMonkeys/Blackjack, and making writing it the way I would.

[See changes](Changes.md)


## Description

This is a program that lets the user play a simplified version of the famous card game Blackjack against the computer.
The user is started with $100 and can chose how much they would like to wager on each game. They keep playing games until they choose to quit or run out of money.

## The internals

The program consists of a main class - PlayBlackjack - that serves as the hub of activity, introducing the player to the game, repeating the game, and ending it.
The blackjack package consists of the essential classes for play: classes for the player and the computer (the house), a special subclass of the card class called BlackjackCard, which contains features essential for the game, and Blackjack, which runs the game and checks for wins or busts.
Finally, the card package is the core of the entire system, containing the Card and Deck classes upon which everything is built.

This program involves multiple classes and is heavily dependent on object oriented programming, making it the most complex program yet in ~~this~~ [BlueMonkeys' Github profile](https://github.com/BlueMonkeys) (the creator is aware that this is not a high bar). Regardless, it is a significant step up from [Infinite Series](https://github.com/BlueMonkeys/Infinite_Series). It is also a rather large step up from TicTacToe, which was created as part of a school assignment and hence was not uploaded to Github. That program did not make use of OOP. 

## Issues

There are no known major bugs. The game seems to function really well. Future improvements include adding exception handling because users are stupid and I'm implementing a GUI. 
