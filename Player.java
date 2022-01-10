/*  
 * 
 * 
 * Name: Zachary Coeur
 * Uni: zjc2106
 * Date: 3/26/21
 * 
 * Player class for Poker Game
 * 
*/

public class Player {
		
    private int bankroll;
    private int bet;
		
    public Player(){		
        //create a player here
        bankroll = 100;
        bet = 0;
    }
		
    public void bets(int amt){
        //player makes a bet
        bankroll = bankroll-amt;
        bet = amt;
        
    }

    public void winnings(int odds){
        //adjust bankroll if player wins
        bet = bet*odds;
        bankroll = bankroll+bet;
    }

    public int getBankroll(){
        //return current balance of bankroll
        return bankroll;
    }
}


