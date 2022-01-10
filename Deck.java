/*  
 * 
 * 
 * Name: Zachary Coeur
 * Uni: zjc2106
 * Date: 3/26/21
 * 
 * Deck Class for Poker Game
 * 
*/

import java.util.Random;

public class Deck {
	
    private Card[] cards;
    private int top; //the index of the top of the deck

    public Deck(){
        //make a 52 card deck here
        cards = new Card[52];
        int i = 0;
        // nested for loop, iterates through each suit 13 times for each rank
        for(int suit = 1;suit<=4;suit++){
            for(int rank = 1;rank<14;rank++){
                cards[i++] = new Card(suit,rank);
            }
        }
    }
	
    public void shuffle(){
        //shuffle the deck here
        Random rand = new Random();
        // fisher-yates method for shuffling
        for(int i = 51;i>0;i--){
            int j = rand.nextInt(i+1);
            Card swap = cards[i];
            cards[i] = cards[j];
            cards[j] = swap;
        }
    }
    
    public Card deal(){
        // deal a single card here
        return cards[top++]; // top increases 1 each time deal is called
    }
}
