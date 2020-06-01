## Notable changes

(...skipped )

[Replaced an instance of](../../commit/b34adc244b11a47e893bcc7a121663d5f674c36a):

```java
if (someStatement) {
  return true;
}
return false;
```

With 

```java
return someStatement;
```

(This was replacement was done again in [Card.java](../../commit/dcc76d5772ee30da2756109982e1150408874a09#diff-baed53f82ede74117b26c104f95a0c19R19))

[Used a function to ask to play again](../../commit/d10939c336a63275ffc3f39543022d628a865e55)

### blackjack

_most of the following commits were done to the blackjack package_


[Made a Utils class](../../commit/bf961a47f7a489c520b79458193799624cd98027), moving Blackjack.delay -> Utils.delay, and adding new function `printwln` to replace `System.out.println("\n...");`

[Removed BlackjackCard.java](../../commit/3ae1d4995c33ab3bd5510cd30279af01bce5e280), moving `BlackjackCard.getPlayValue` -> `Blackjack.getCardValue`. Some constants were also created, `TARGET_NUMBER`, `ACE_HIGH_VALUE`, and `ACE_LOW_VALUE` to be used.

The reason for this change is that originally, deck.Deck used blackjack.BlackjackCard instead of it's own deck.Card. To avoid using templates and other more complex features of java (for now), BlackjackCard was simply removed.

[Add `smartSum`](../../commit/ef3b2236daa6ec08249ca325041b1daec9c076a4); because BlackjackCard was removed, `setPlayValue` and such were also removed. Now, the actual value of the Ace is both 1 and 11 at the same time, but, when you `smartSum` it, it will collapse into 1 or 11, depending on the other cards in it's hand.

[Make Blackjack more encapsulated](../../commit/d32a16e7c12127b776bf861c65e82aeb5de6d5b4): playGame()'s returning boolean wasn't obvious (it could have been, did the game finish without errors?), so instead the return value is stored, and a function with, didPlayerWin returns said value. didPlayerWin returning a boolean makes much more sense, and is obvious what value it returns.

Also in this commit, many methods not used outside of the Blackjack class were changed from public to private.

[Remove redundant line](../../commit/f5fd576ed815324b0a10f3e7b2c561bd940c4145): So I found out scanner.hasNextLine() will always return true...
Refactor play()

[Refactor play()](../../commit/c41a088efaf8f2e239cccdc8f20512b034a7d425): The play() function felt too long. Avoid long functions and large amounts of indentation.

Comparing 'choice' with 'H' twice didn't seem right, so it was refactored out into playerCanContinue

(... skipped 3 commits)

[Breaking out of the loop with Exceptions](../../commit/fcad85c35d7d359a7e484dc1a658398b3708d886): One of the important things to remember about Errors and Exceptions, once they're thrown, all parent functions are also stopped. This can be thought of as a return statement spanning several function calls, until a suitable `catch` statement is found.

We use this to allow child functions to break out from a loop in a parent function.

(... skipped 1 commit)

[Clean up winScreen](../../commit/c79a0c5beb06ac0008be9c90925fc314257ed90d): Instead of settings flags (boolean variables), and then checking them later, just do what the check would have done, without setting the flag.

This removes a large chunk of code and the need for 4 variables.

(... skipped 1 commit)

[Rename temp -> newCard](../../commit/df2c15f052d4ed4b12f41a9f6e882ddd33063c1e): Avoid using the variable name `temp`. It's not descriptive.

[Factor out AbstractPlayer](../../commit/ef5099fba9f74e78aae5338dcffc42d834114b74): The House and the Player class seemed similiar (probably because you copy-pasted), so I factored it out into a new class. (If you're copy-pasting, you're doing it wrong).

I also modified the class a little to make code reuse easier. (Which I do in the next commit)

(... skipped 2 commits)

### card

_most of the following commits were done to the card package_

[Refactor Card, create CardSuit enum](../../commit/dcc76d5772ee30da2756109982e1150408874a09): Instead of using a String to store the suit, we use an enum instead.

The getter and setter were removed from Card, because I didn't feel like it was necessary. (Do note, I just committed a bad practice is some people's eyes)

[Refactor Card](../../commit/8e2bf6720ff07e9f6e6f6751ff055393382f39ad): See for yourself. In my opinion, this is the most beautiful file in this project.

(... skipped 3 commits)

### PlayBlackjack.java

[Create Utils.askDouble](../../commit/621d3f3f8a9cfa0a6e887120ea515b826eb6e7ab): With error handling!

(... skipped 1 commit)
