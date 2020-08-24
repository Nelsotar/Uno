/*
 * Copyright (C) Taryn Nelson
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * Class for instantiating a player's Hand of UnoCards.
 * @author Taryn Nelson
 * @since 2020-04-13
 */
public class Hand extends GroupOfCards {
    
    /**
     * Returns the UnoCard at the index of Hand's ArrayList of UnoCards.
     * @param index index of the UnoCard in the ArrayList in Hand.
     * @return 
     */
    public UnoCard getCard(int index){
        return getCards().get(index);
    }
    
    /**
     * Creates and returns a String containing a numbered list of all UnoCards 
     * in the GroupOfCards.
     * @return A String containing a numbered list of all UnoCards in the GroupOfCards.
     */
    @Override
    public String toString(){
        String hand = "";
        int index = 1;
        for(UnoCard card: getCards()){
            hand += index + ". " + card.getColour() + " " + card.getRank() + "\n";
            index++;
        }
        return hand;
    }  
    
    /**
     * Determines if a card is currently playable.
     * @param card an UnoCard
     * @param discard The discard pile for the UnoGame
     * @return True if the card is playable, false otherwise.
     */
    public boolean isPlayable(UnoCard card, DiscardPile discard){
            UnoCard discardCard = discard.showTopCard();
            
            if(card.getColour() == discardCard.getColour()  && card.getRank() != Rank.wild_draw_4){
                return true;
            }
            else if(card.getRank() == discardCard.getRank() && card.getRank() != Rank.wild_draw_4){
                return true;
            }
            //a wild draw four may only be playe=d if the player has no other
            //matching the colour of the top of the discard pile
            else if(card.getRank() == Rank.wild_draw_4 && card.getColour() == discardCard.getColour()){
                int numDrawFours = 0;
                for(UnoCard handCard: getCards()){
                    if(handCard.getRank() != Rank.wild_draw_4 && handCard.getColour() == discardCard.getColour()){
                        return false;
                    }
                    else if(handCard.getRank() == Rank.wild_draw_4 && handCard.getColour() == discardCard.getColour()){
                        numDrawFours++;
                    }
                }
                
                if(numDrawFours > 1){
                    return false;
                }
                return true;
            }
            
            return false;
        } 
    
    /**
     * Determines if the card at the cardIndex - 1 in the player's Hand is playable.
     * @param cardIndex The index of the UnoCard in the player's Hand.
     * @param discard The discard pile for the UnoGame
     * @return True if the card is playable, false otherwise.
     */
    public boolean isPlayable(int cardIndex, DiscardPile discard){
        
        cardIndex--;
        
        if(cardIndex < 0 || cardIndex >= this.getSize()){
           return false;
        }
        
        return isPlayable(getCard(cardIndex),discard);
    }   
   
}