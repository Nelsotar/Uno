/*
 * Copyright (C) 2020 Taryn Nelson
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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class to test the behaviours of special action cards when they are the cards 
 * drawn for the discard pile at the beginning of the round.
 * @author Taryn Nelson
 * @since 2020-04-19
 */
public class CardInititalPlayTest {
    GameController controller;
    
    public CardInititalPlayTest() {
        controller = new GameController();  
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Creates and sets up a new UnoGame with 4 computerized players.
     */
    @Before
    public void setUp() {
        UnoGame game = new UnoGame("TestInitialPlay");
        controller.setGame(game);
        controller.getGame().setDrawPile();
        controller.getGame().setDiscardPile();      
        controller.getGame().addComputerizedPlayer("Player 1");
        controller.getGame().addComputerizedPlayer("Player 2");
        controller.getGame().addComputerizedPlayer("Player 3");
        controller.getGame().addComputerizedPlayer("Player 4");
        controller.getGame().setCurrentPlayer(0);
    }
    
    @After
    public void tearDown() {
        System.out.println("Test complete.\n");
    }

    /**
     * Tests the game's behavior when the initial card on the top of the discard
     * pile is a draw 2. The game should give the first player 2 cards, but it 
     * should still be their turn.
     */
    @Test
    public void testInitialDraw2GoodBoundary() {
        //There are only two cases for this test - the intial card is a draw 2 or it isn't
        System.out.println("Test Initial Draw 2 Good/Boundary");
        controller.getGame().getDiscardPile().addCard(new UnoCard(Colour.blue, Rank.draw_2));
        UnoCard topCard = controller.getGame().getDiscardPileTop();
        controller.specialActionInitial(topCard);
        
        Player player = controller.getGame().getCurrentPlayer();

        boolean expResult = true;
        boolean result = player.getNumCards() == 2;
        result = result && player.getPlayerID().equals("Player 1");
        assertEquals(expResult, result);
    }
    
    /**
     * Tests the game's behavior when the initial card on the top of the discard
     * pile is not a draw 2. The game should give the first player no cards, and it 
     * should still be their turn.
     */
    @Test
    public void testInitialDraw2Bad() {
        System.out.println("Test Initial Draw 2 Good/Boundary");
        controller.getGame().getDiscardPile().addCard(new UnoCard(Colour.blue, Rank.zero));
        UnoCard topCard = controller.getGame().getDiscardPileTop();
        controller.specialActionInitial(topCard);
        
        Player player = controller.getGame().getCurrentPlayer();

        boolean expResult = false;
        boolean result = player.getNumCards() == 2;
        result = result && player.getPlayerID().equals("Player 1");
        assertEquals(expResult, result);
    }
    
    /**
     * Tests the game's behavior when the initial card on the top of the discard
     * pile is a reverse card. The direction of play should now be "left" and 
     * the player to the left of the first player now gets to go first.
     */
    @Test
    public void testInitialReverseGoodBoundary() {
        //There are only two cases for this test - the intial card is a reverse or it isn't
        System.out.println("Test Initial Reverse Good/Boundary");
        controller.getGame().getDiscardPile().addCard(new UnoCard(Colour.blue, Rank.reverse));
        UnoCard topCard = controller.getGame().getDiscardPileTop();
        controller.specialActionInitial(topCard);
        
        Player player = controller.getGame().getCurrentPlayer();

        boolean expResult = true;
        boolean result = controller.getGame().getDirection().equals("left");
        result = result && player.getPlayerID().equals("Player 4");
        assertEquals(expResult, result);
    }
    
    /**
     * Tests the game's behavior when the initial card on the top of the discard
     * pile is not a reverse card. The direction of play should be "right" and 
     * it should still be the first player's turn.
     */
    @Test
    public void testInitialReverseBad() {
        System.out.println("Test Initial Reverse Good/Boundary");
        controller.getGame().getDiscardPile().addCard(new UnoCard(Colour.blue, Rank.one));
        UnoCard topCard = controller.getGame().getDiscardPileTop();
        controller.specialActionInitial(topCard);
        
        Player player = controller.getGame().getCurrentPlayer();

        boolean expResult = true;
        boolean result = controller.getGame().getDirection().equals("right");
        result = result && player.getPlayerID().equals("Player 1");
        assertEquals(expResult, result);
    }
    
    /**
     * Tests the game's behavior when the initial card on the top of the discard
     * pile is a skip card. It should now be the next player's turn. instead of 
     * the initial player's.
     */
    @Test
    public void testInitialSkipGoodBoundary() {
        //There are only two cases for this test - the intial card is a skip or it isn't
        System.out.println("Test Initial Skip Good/Boundary");
        controller.getGame().getDiscardPile().addCard(new UnoCard(Colour.blue, Rank.skip));
        UnoCard topCard = controller.getGame().getDiscardPileTop();
        controller.specialActionInitial(topCard);
        
        Player player = controller.getGame().getCurrentPlayer();

        boolean expResult = true;
        boolean result = player.getPlayerID().equals("Player 2");
        assertEquals(expResult, result);
    }
    
    /**
     * Tests the game's behavior when the initial card on the top of the discard
     * pile is not a skip card. It should still be Player 1's turn.
     */
    @Test
    public void testInitialSkipBad() {
        System.out.println("Test Initial Skip Bad");
        controller.getGame().getDiscardPile().addCard(new UnoCard(Colour.blue, Rank.one));
        UnoCard topCard = controller.getGame().getDiscardPileTop();
        controller.specialActionInitial(topCard);
        
        Player player = controller.getGame().getCurrentPlayer();

        boolean expResult = false;
        boolean result = player.getPlayerID().equals("Player 2");
        assertEquals(expResult, result);
    }
}
