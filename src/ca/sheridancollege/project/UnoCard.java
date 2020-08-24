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

/** A class to instantiate an UnoCard for the UnoGame.
 *@author Taryn Nelson
 *@since 2020-04-13
 */
    public class UnoCard extends Card {
    
        /**
         * The rank of the card.
         */
        private Rank rank;
        
        /**
         * The colour of the card.
         */
        private Colour colour;
        
        /**
         * The point value of the card.
         */
        private int value;
    
        @Override
        public String toString(){
         return colour + " " + rank;
        };
        
	/**
	 * Constructor sets a car's colour and rank and calls setValue() to set 
         * it's point value;
	 * @param colour the card's colour.
	 * @param rank the card's rank.
	 */
	public UnoCard(Colour colour, Rank rank) {
            this.colour = colour;
            this.rank = rank;
            setValue();
	}

        /**
         * Sets the UnoCard's point value according to it's rank.
         */
	final private void setValue() {
            switch(rank){
                case zero:
                    value = 0;
                    break;
                case one:
                    value = 1;
                    break;
                case two:
                    value = 2;
                    break;
                case three:
                    value = 3;
                    break;
                case four:
                    value = 4;
                    break;
                case five:
                    value = 5;
                    break;
                case six:
                    value = 6;
                    break;
                case seven:
                    value = 7;
                    break;
                case eight:
                    value = 8;
                    break;
                case nine:
                    value = 9;
                    break;
                case skip:
                case reverse:
                case draw_2:
                    value = 20;
                    break;
                case wild:
                case wild_draw_4:
                    value = 50;
                    break;
            }
	}

        /**
         * @return Point value of the UnoCard.
         */
	public int getValue() {
            return this.value;
	}

        /**
         * @return Colour of the UnoCard
         */
	public Colour getColour() {
            return this.colour;
	}

        /**
         * Used only with wildcards in the game, changes the colour of the card.
         * @param colour Colour to change card to.
         */
	public void setColour(Colour colour) {
            this.colour = colour;
	}

        /**
         * @return Rank of the UnoCard.
         */
	public Rank getRank() {
            return this.rank;
	}  
           
    }
