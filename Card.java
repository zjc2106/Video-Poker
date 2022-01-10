/*  
 * 
 * 
 * Name: Zachary Coeur
 * Uni: zjc2106
 * Date: 3/26/21
 * 
 * Card Class for Poker Game
 * 
*/

public class Card implements Comparable<Card>{
	
    private int suit; //use integers 1-4 to encode the suit
    private int rank; //use integers 1-13 to encode the rank
	
    public Card(int s, int r){
        //make a card with suit s and rank r
        suit = s;
        rank = r;
    }
    
    public int compareTo(Card c){
        //use this method to compare cards so they 
        //may be easily sorted
        if(this.rank < c.rank){
            return -1; //for collections.sort
        }
        else if(this.rank>c.rank){
            return 1;
        }
        //only compare suit if rank is the same
        else if(this.rank==c.rank){
            if(this.suit>c.suit){
                return 1;
            }
            if(this.suit<c.suit){
                return -1;
            }
        }
        return 0;
    }
	
    public String toString(){
        //use this method to easily print a Card object
        String rankString;
        String suitString;
        rankString = createRankString(); //helper method
        suitString = createSuitString(); //helper method
        String fullString = rankString+" of "+suitString;
        return fullString;
    }
    
    //accessor method for rank of card
    public int getRank(){
        return rank;
    }
    //accessor method for suit of card
    public int getSuit(){
        return suit;
    }

    private String createSuitString(){
        // returns string with name of suit depending on suit variable
        String suitString = "shovels";
        if(suit == 1){
            suitString = "Clubs";
        } 
        if(suit ==2){
            suitString = "Diamonds";
        }
        if(suit ==3){
            suitString = "Hearts";
        }
        if(suit ==4){
            suitString = "Spades";
        }
        return suitString;
    }
    
    private String createRankString(){
        // returns a string with rank depending on rank variable
        String rankString = "18";
        // if rank less then 10 and not 1, return the rank as string
        if(rank<=10&&rank>1){
           
            rankString = String.valueOf(rank);
        }
        // if 1 or greater than 10, return the name of face card based on rank
        if(rank==11){
            rankString = "Jack";
        }
        if(rank==12){
            rankString = "Queen";
        }
        if(rank==13){
            rankString = "King";
        }
        if(rank==1){
            rankString = "Ace";
        }
        return rankString;
    }
}
