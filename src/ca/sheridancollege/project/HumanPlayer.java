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
 * Class for instantiating a HumanPlayer for the UnoGame.
 * @author Taryn Nelson
 * @since 2020-04-13
 */
public class HumanPlayer extends Player {

    /**
     * Creates a HumanPlayer with the provided String as a name.
     * @param playerID player name.
     */
    public HumanPlayer(String playerID){
        super(playerID);
    }

    /**
     * Allows the user to play an UnoCard from their Hand according to it's index.
     * @param cardNum One more than the index of the card in the Player's Hand.
     * @param discard The CiscardPile of the UnoGame.
     * @return The played card.
     */
    public UnoCard playCard(int cardNum, DiscardPile discard){
        UnoCard card = getHand().getCards().remove(cardNum - 1);
        discard.addCard(card);
        
        return card;
    }
    
    /**
     * Allows the user to play an UnoCard from their Hand.
     * @param cardNum One more than the index of the card in the Player's Hand.
     * @param discard The CiscardPile of the UnoGame.
     * @return The played card.
     */
    public UnoCard playCard(UnoCard card, DiscardPile discard){
        getHand().getCards().remove(card);
        discard.addCard(card);
        return card;
    }
        
}