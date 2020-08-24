/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 * @author dancye, 2018
 * @modifier Taryn Nelson 2020-04-13
 */
public abstract class Player {
    private String playerID;
    private Hand hand;
    private int score; //the unique ID for this player
    
    /**
     * A constructor that allows you to set the player's unique ID
     * @param name the unique ID to assign to this player.
     */
    public Player(String name)
    {
        playerID = name;
        score = 0;
        hand = new Hand();
    }


    /**
     * @return the playerID
     */
    public String getPlayerID() 
    {
        return playerID;
    }
    
    /**
     * @return the player's Hand.
     */
    public Hand getHand(){
        return hand;
    }
    
    /**
     * @return the size of the player's Hand. 
     */
    public int getNumCards(){
       return hand.getSize();
    }

    /**
     * @return the player's score. 
     */
    public int getScore() {
	return this.score;
    }
  
    /**
    * Adds points to the player's point total.
    * @param points points to add to player's point total.
    */
    public void setScore(int points) {
	this.score += points;
    }
        
    
    /**
    * Draws a number of UnoCards from the DrawPile and adds them to the player's 
    * Hand.
    * @param numCards
    * @return Most recently drawn card.
    */
    public UnoCard drawCards(int numCards, DrawPile drawPile) {  
        
        UnoCard mostRecent = null;
        
        for(int i = 1; i <= numCards; i++){
            mostRecent = drawPile.removeCard();
            hand.addCard(mostRecent); 
        }
        
        return mostRecent;
    }
 
}
