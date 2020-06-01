## Notable changes

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

[Used a function to ask to play again](../../commit/d10939c336a63275ffc3f39543022d628a865e55)

[Made a Utils class](../../commit/bf961a47f7a489c520b79458193799624cd98027), moving Blackjack.delay -> Utils.delay, and adding new function `printwln` to replace `System.out.println("\n...");`

[Removed BlackjackCard.java](../../commit/3ae1d4995c33ab3bd5510cd30279af01bce5e280), moving `BlackjackCard.getPlayValue` -> `Blackjack.getCardValue`. Some constants were also created, `TARGET_NUMBER`, `ACE_HIGH_VALUE`, and `ACE_LOW_VALUE` to be used.

The reason for this change is that originally, deck.Deck used blackjack.BlackjackCard instead of it's own deck.Card. To avoid using templates and other more complex features of java (for now), BlackjackCard was simply removed.

[Add `smartSum`](../../commit/ef3b2236daa6ec08249ca325041b1daec9c076a4); because BlackjackCard was removed, `setPlayValue` and such were also removed. Now, the actual value of the Ace is both 1 and 11 at the same time, but, when you `smartSum` it, it will collapse into 1 or 11, depending on the other cards in it's hand.

[Make Blackjack more encapsulated](../../commit/d32a16e7c12127b776bf861c65e82aeb5de6d5b4): playGame()'s returning boolean wasn't obvious (it could have been, did the game finish without errors?), so instead the return value is stored, and a function with, didPlayerWin returns said value. didPlayerWin returning a boolean makes much more sense, and is obvious what value it returns.

Also in this commit, many methods not used outside of the Blackjack class were changed from public to private.
