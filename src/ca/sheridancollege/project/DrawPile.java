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
 * Class to instantiate a draw pile for the UnoGame.
 * @author Taryn Nelson 2020-04-13
 */
public class DrawPile extends GroupOfCards {

    /**
     * Shuffles an ArrayList of cards.
     */
    public void shuffle() {
        ArrayList<UnoCard> cards = getCards();
        UnoCard tempCard;
        int randomNum;
             
        for(int i = 0; i < cards.size(); i++){
            randomNum = generateRandomNum();
            tempCard = cards.get(i);
            cards.set(i, cards.get(randomNum));
            cards.set(randomNum, tempCard);
        }
    }
    
    /**
     * Generates and returns a random number between 0 and the size of the cards 
     * ArrayList - 1.
     * @return A random integer between 0 and the size of the cards ArrayList - 1.
     */
    private int generateRandomNum(){
        return (int)(Math.random() * (getCards().size()));
    }

    /**
     * Removes an UnoCard from the end of the draw pile's UnoCard ArrayList. If
     * no cards remain to be drawn, a new draw pile is generated and shuffled 
     * then a card is drawn.
     * @return The UnoCard removed from the draw pile. 
     */
    public UnoCard removeCard(){
        if(getSize() != 0){
            return getCards().remove(getSize() - 1);  
        } 
        else{
            generateDrawPile();
            shuffle();
            return getCards().remove(getSize() - 1); 
        }
    }
    
    /**
     * Adds all UnoCards to the draw pile.
     */
    public void generateDrawPile(){
        for(Rank rank: Rank.values()){
            for(Colour colour: Colour.values()){
                getCards().add(new UnoCard(colour,rank));
            }
        }
        
        Rank[] additionalRanks = {Rank.skip, Rank.reverse, Rank.draw_2};
        
        for(Rank rank: additionalRanks){
            for(Colour colour: Colour.values()){
                getCards().add(new UnoCard(colour,rank));
            }
        }
    }
}