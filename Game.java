/*  
 * 
 * 
 * Name: Zachary Coeur
 * Uni: zjc2106
 * Date: 3/26/21
 * 
 * Game class for Poker Game
 * 
*/


import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Game {
	
    private Player p;
    private Deck cards;
    private ArrayList<Card> hand;
    private Scanner input;
	
    public Game(String[] testHand){
        // constructor for testing code
		p = new Player();
        cards = new Deck();
        hand = new ArrayList<Card>();
        parseTestHand(testHand); //helper method, parses testHand, get rank/suit
    }
	
    public Game(){
        // This no-argument constructor is to actually play a normal game
        p = new Player();
        cards = new Deck();
        hand = new ArrayList<Card>();
        input = new Scanner(System.in);
    }
	
    public void play(){
        
        // this method should play the game	
        int keepPlaying = 1;
        int trader = 10;
        int bet = 0;
        // while loop, continues until player doesn't submit 1 at end
        while(keepPlaying == 1){
            hand.clear();
            System.out.println("What would you like your bet to be? 1-5: "+
            "You have: "+p.getBankroll()+" coins.");
            bet = input.nextInt();
            // checks user-input for bet
            while(bet<1||bet>5){
                System.out.println("That is an invalid bet. " 
                +"Bet again from 1-5: ");
                bet = input.nextInt();
            }
            p.bets(bet);
            cards.shuffle();
            for(int i = 0;i<5;i++){
                hand.add(cards.deal());
            }
            Collections.sort(hand);
            printHand(); // helper method
            swapHand(); // helper method
            Collections.sort(hand);
            printHand();
            p.winnings(checkHand()); // adjusts player's bankroll based on hand
            System.out.println("You now have: "+p.getBankroll()+" coins.");
            System.out.println("Would you like to play another hand?"
            +" 1-yes 0-no: ");
            keepPlaying = input.nextInt();
            cards = new Deck(); // new deck each hand
        }
    }
    
    public void testPlay(){
        // this method is used for testing the checkHand method
        // it should evaluate the hand that was passed
        // as a command-line argument and print the result
        Collections.sort(hand);
        printHand(); // helper method
        checkHand();
    }
	
	public int checkHand(){
        // this method checks the hand, starting at Royal Flush
        // and working down, relies on many helper methods for
        // checking each possible hand
        if(royalFlush()){
            System.out.println("Royal Flush");
            return 250;
        }
        else if(straightFlush()){
            System.out.println("Straight Flush");
            return 50;
        }
        else if(fourOfAKind()){
            System.out.println("Four of a Kind");
            return 25;
        }
        else if(fullHouse()){
            System.out.println("Full House");
            return 6;
        }
        else if(flush()){
            System.out.println("Flush");
            return 5;
        }
        else if(straight()){
            System.out.println("Straight");
            return 4;
        }
        else if(threeOfAKind()){
            System.out.println("Three of a Kind");
            return 3;
            
        }
        else if(twoPair()){
            System.out.println("Two Pair");
            return 2;
        }
		else if(pair()){
            System.out.println("Pair");
            return 1;
        }
        else{
            System.out.println("High Card");
            return 0;
        }
    }
    
    private void swapHand(){
        // this method asks the user if they want to keep
        // each of their cards and swaps it if they don't
        int trader = 10;
        for(int i = 0;i<5;i++){
            System.out.println("Would you like to keep card "+(i+1)+"? 0-no, 1-yes: ");
            trader = input.nextInt();
            if(trader == 0){
                    hand.set(i,cards.deal()); // gives new card
                }
        }
    }
    
    private boolean royalFlush(){
        // only returns true if hand is a straight and flush
        // and starts with 10 and ends with an ace
        if(straight()&&flush()){
            return(((hand.get(0)).getRank())==1 && ((hand.get(4)).getRank())==13);    
        }
        return false;
    }
	
    private boolean straightFlush(){
        // must be a straight and a flush
        return (straight() && flush());
    }
    
    private boolean fourOfAKind(){
        // checks that it isn't just a two pair and that the two pairs
        // are the same rank
        if(twoPair()){
            for(int i = 0; i<2;i++){
                if(((hand.get(i)).getRank())==((hand.get(i+3)).getRank())){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean fullHouse(){
        int answer = -1;
        if(pair() && threeOfAKind()){ 
            // checks that the pair and three of a kind are not 
            // the same cards
            for(int i = 0; i<4;i++){
                if(((hand.get(i)).getRank())!=((hand.get(i+1)).getRank())){
                    for(int j = i+1;j<4;j++){
                        if(((hand.get(j)).getRank())!=((hand.get(j+1)).getRank())){
                            answer = 0;
                        }
                    }
                }
            }
        if(answer!=0){
            return true;
        }
        else{
            return false;
        }
        }
        return false;
    }
    
    private boolean flush(){
        // checks that all cards have the same suit
        for(int i = 0;i<4;i++){
            if(((hand.get(i)).getSuit())!=((hand.get(i+1)).getSuit())){
                return false;
            }
            
        }
        return true;
    }
    
    private boolean straight(){
        // checks that each rank increases by one each card
        if(((hand.get(4).getRank())-(hand.get(0).getRank()))==4){
            for(int i = 0;i<4;i++){
                // adds one to the first card to see if it is equal to the next
                if(((hand.get(i)).getRank()+1)!=((hand.get(i+1)).getRank())){
                    return false;
                }
            
            }
            return true;
        }
        // special case for straights with ace and 10 as first card
        if(((hand.get(0).getRank())==1)&&((hand.get(1).getRank())==10)){
            for(int i = 1;i<4;i++){
                // same as above, ignores first card which is an ace
                if(((hand.get(i)).getRank()+1)!=((hand.get(i+1)).getRank())){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    private boolean threeOfAKind(){
        // iterates through the hand 3 times, seeing if any three
        // cards have the same rank
        for(int i = 0; i<3;i++){
            if(((hand.get(i)).getRank())==((hand.get(i+1)).getRank())){
                if(((hand.get(i+1)).getRank())==((hand.get(i+2)).getRank())){
                    return true;
                }
            }
        }
        return false;
        }
    
    private boolean twoPair(){
        // uses a nested for loop, if there is one pair then it 
        // goes into the second for loop and checks for another pair
        for(int i = 0; i<4;i++){
            if(((hand.get(i)).getRank())==((hand.get(i+1)).getRank())){
                for(int j = i+1;j<4;j++){
                    if(((hand.get(j)).getRank())==((hand.get(j+1)).getRank())){
                        return true;
                    }
                }
            }        
        }
        return false; 
    }
    
    private boolean pair(){
        // checks for a pair by comparing each card and the one after
        for(int i = 0; i<4;i++){
            if(((hand.get(i)).getRank())==((hand.get(i+1)).getRank())){
                return true;
        }

        }
        return false;
    }
    
    private void parseTestHand(String[] testHand){
        // goes through the each element in testHand and parses
        // the suit and rank into the numbers that the Card class
        // can handle
        String suit = "";
        String rank = "";
        int intSuit = 0;
        int intRank = 0;
        for(String element:testHand){
            if(element.length()>2){
                suit = element.substring(0,1);
                rank = element.substring(1,3);
            }
            if(element.length()==2){
                suit = element.substring(0,1);
                rank = element.substring(1,2);
            }
            if(suit.equals("s")){
                intSuit = 4;
            }
            if(suit.equals("h")){
                intSuit = 3;
            }
            if(suit.equals("d")){
                intSuit = 2;
            }
            if(suit.equals("c")){
                intSuit = 1;
            }
            intRank = Integer.parseInt(rank); // changes rank to int
            Card c = new Card(intSuit,intRank); 
            hand.add(c);
        }
    }
    
    private void printHand(){
        // prints hand by iterating through
        for(Card element:hand){
            System.out.println(element.toString());
        }
    }
}
