## Notable changes

b34adc244b11a47e893bcc7a121663d5f674c36a: Replaced an instance of:

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

d10939c336a63275ffc3f39543022d628a865e55: Used a function to ask to play again

bf961a47f7a489c520b79458193799624cd98027: Made a Utils class, moving Blackjack.delay -> Utils.delay, and adding new function `printwln` to replace `System.out.println("\n...");`

3ae1d4995c33ab3bd5510cd30279af01bce5e280: Removed BlackjackCard.java, moving `BlackjackCard.getPlayValue` -> `Blackjack.getCardValue`. Some constants were also created, `TARGET_NUMBER`, `ACE_HIGH_VALUE`, and `ACE_LOW_VALUE` to be used.

The reason for this change is that originally, deck.Deck used blackjack.BlackjackCard instead of it's own deck.Card. To avoid using templates and other more complex features of java (for now), BlackjackCard was simply removed.

ef3b2236daa6ec08249ca325041b1daec9c076a4: Because BlackjackCard was removed, `setPlayValue` and such were also removed. Now, the actual value of the Ace is both 1 and 11 at the same time, but, when you `smartSum` it, it will collapse into 1 or 11, depending on the other cards in it's hand.
