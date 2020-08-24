/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * A concrete class that represents any grouping of cards for a Game.
 * HINT, you might want to subclass this more than once.
 * The group of cards has a maximum size attribute which is flexible for reuse.
 * @author dancye
 * @modfier Taryn Nelson 2020-04-13
 */
public abstract class GroupOfCards {
    //The group of cards, stored in an ArrayList
    private ArrayList <UnoCard> cards;
    
    /**
     * Initializes the cards ArrayList.
     */
    public GroupOfCards(){
        cards = new ArrayList();
    }
    
    /**
     * Returns all UnoCards in the GroupOfCards.
     * @return ArrayList of all UnoCards in the GroupOfCards. 
     */
    public ArrayList<UnoCard> getCards(){
        return cards;
    }

    /**
     * Gets the size of the GroupOfCards.
     * @return the size of the group of cards
     */
    public int getSize() {
        return cards.size();
    }
    
    /**
    * Adds provided card to the GroupOfCards
    * @param card Card to add to the GroupOfCards.
    */
    public void addCard(UnoCard card) {
        cards.add(card);
    }
}//end class
