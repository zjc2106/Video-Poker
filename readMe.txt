readMe.txt
Name: Zachary Coeur
uni: zjc2106
Date: 3/29/21

Programming Project 4- Video Poker

For this project, I started by coding the Deck, Card, and Player classes as those were necessary for the Game class, which is most important.
The Player class was very straightforward with just some mutator and accessor methods to change and access the Player's score. For the Card class,
I focused first on comparing the cards which was relatively simple, and then continued on to the toString method, for which I just used multiple 
if statements to see which rank and suit corresponeded with the integers passed in the Card. I split this up into two helper methods, one focused
on the rank and one on the suit so the overall method wasn't too long. For the Deck class, I started by creating a deck by iterating through
a loop 52 times and initializing a card into the deck each time. Then I shuffled using the Fisher-Yates method, and for deal, each time it is called, 
the top card increases by 1

For the Game class, I created the regular play() method with a while loop so that the game could be continually played. I then went through the Poker 
Game, creating helper methods where I needed to so that the play method wasn't too long. One was the swapHand() method, which just asks the player
5 times whether they want to keep a certain card. Another was the printHand() method, which used a for loop to go through the hand, printing out
a card each time. The most important method was checkHand(), which was also broken up into many different helper methods for each possible hand 
that the player could have. It started with royalFlush() and ended with pair() so that if it did find a hand that matched, it stopped going through.
For the testPlay() method, I parsed the testHand by slicing the strings, using if statements to find the right rank and suit, and creating a hand
that had each card in the testHand in it. Then I called checkHand() to test.

Sources
https://en.wikipedia.org/wiki/Fisher–Yates_shuffle#The_modern_algorithm
I used this source to find an efficient and elegant way to shuffle the deck.