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

/**
 * A class for instantiating a computerized player that automatically plays cards
 * and picks a colour when they play any type of wildcard.
 * @author Taryn Nelson
 * @since 2020-04-17
 */
public class ComputerizedPlayer extends Player {
    
    /**
     * Constructor initializes the computerized player with the given String as
     * a name. 
     * @param playerID The player's name.
     */
    public ComputerizedPlayer(String playerID){
        super(playerID);
    }
    
    /**
     * Plays the playable card in the player's hand with the highest point value.
     * @param discard The discard pile for the game.
     * @return UnoCard played if has a playable card, null if no playable card.
     */
    public UnoCard playCard(DiscardPile discard){
        UnoCard cardToPlay = null;
        for(UnoCard card: getHand().getCards()){
           if(getHand().isPlayable(card, discard)){
               if(cardToPlay == null){
                   cardToPlay = card;
               }
               else if(card.getValue() > cardToPlay.getValue()){
                   cardToPlay = card;
               }
           } 
        }
        
        if(cardToPlay != null){
            getHand().getCards().remove(cardToPlay);
            discard.addCard(cardToPlay);
        }
        return cardToPlay;
    }
    
    /**
     * Determines which colour of cards the player has the most of in their 
     * hand and returns a String containing that colour.
     * @return a String containing the colour to change the top card on the 
     * discard pile to.
     */
    public String chooseWildColour(){
        int red, blue, green, yellow;
        red = blue = green = yellow = 0;
        String color = "green";
        
        for(UnoCard card: getHand().getCards()){
            switch(card.getColour()){
                case blue:
                    blue++;
                    break;
                case red:
                    red++;
                    break;
                case yellow:
                    yellow++;
                    break;
                case green:
                    green++;
                    break;
            }
        }
        
        int max = 0;
        
        if(blue > max){
           max = blue;
           color = "blue";
        }
        if(red > max){
           max = red;
           color = "red"; 
        }
        if(yellow > max){
           max = yellow;
           color = "yellow"; 
        }
        if(green > max){
           color = "green"; 
        }
        
        return color;
    }

}