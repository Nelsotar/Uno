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
 * Test class for special action cards while played in the course of the UnoGame.
 * All of the tests that cause a player to miss a turn will simply move to the 
 * player after the one who played that card since the playROund() method will
 * jump to the next player after that.
 * @author Taryn Nelson
 * @since 2020-04-18
 */
public class CardFunctionalityTest {
    GameController controller;
    
    public CardFunctionalityTest() {
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
        UnoGame game = new UnoGame("TestCardFunctionailty");
        controller.setGame(game);
        controller.getGame().setDrawPile();
        controller.getGame().setDiscardPile();
        controller.getGame().getDiscardPile().addCard(new UnoCard(Colour.blue, Rank.zero));
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
     * Test of playTurnComputer() when the player has multiple playable draw 2 cards. 
     */
    @Test
    public void testPlayDraw2ComputerGood() {
        System.out.println("Test Play Draw 2 Computer Good");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.draw_2));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.draw_2));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getPlayers().get(1).getHand().getSize() == 2;
        result = result && controller.getGame().getCurrentPlayer().getPlayerID().equals("Player 2");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has a single playable draw 2. 
     */
    @Test
    public void testPlayDraw2ComputerBoundary() {
        System.out.println("Test Play Draw 2 Computer Boundary");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.draw_2));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getPlayers().get(1).getHand().getSize() == 2;
        result = result && controller.getGame().getCurrentPlayer().getPlayerID().equals("Player 2");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() does not have any playable draw 2 cards. 
     */
    @Test
    public void testPlayDraw2ComputerBad() {
        System.out.println("Test Play Draw 2 Computer Bad");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.zero));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.yellow,Rank.draw_2));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getCurrentPlayer().getPlayerID().equals("Player 1");
        System.out.println(controller.getGame().getCurrentPlayer().getPlayerID());
                
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has multiple playable reverse cards. 
     */
    @Test
    public void testPlayReverseComputerGood() {
        System.out.println("Test Play Reverse Computer Good");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.reverse));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.reverse));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getDirection().equals("left");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has a single playable reverse card. 
     */
    @Test
    public void testPlayReverseComputerBoundary() {
        System.out.println("Test Play Reverse Computer Boundary");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.reverse));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getDirection().equals("left");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has no playable revers cards. 
     */
    @Test
    public void testPlayReverseComputerBad() {
        System.out.println("Test Play Reverse Computer Bad");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.zero));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.yellow,Rank.reverse));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getDirection().equals("right");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has multiple playable wild 
     * cards and multiple other cards in their hand. 
     */
    @Test
    public void testPlayWildComputerGood() {
        System.out.println("Test Play Wild Computer Good");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.wild));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.wild));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.green, Rank.zero));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.green, Rank.one));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.green, Rank.two));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.red, Rank.zero));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.red, Rank.one));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getDiscardPileTop().getColour() == Colour.green;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has only a single playable wild card in their hand. 
     */
    @Test
    public void testPlayWildComputerBoundary() {
        System.out.println("Test Play Wild Computer Boundary");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.wild));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        //The default colour to turn to is green
        boolean result = controller.getGame().getDiscardPileTop().getColour() == Colour.green;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has no playable wild cards. 
     */
    @Test
    public void testPlayWildComputerBad() {
        System.out.println("Test Play Wild Computer Bad");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.green, Rank.wild));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.red, Rank.zero));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.red, Rank.one));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = false;
        boolean result = controller.getGame().getDiscardPileTop().getColour() == Colour.blue;
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has a single wild draw 4 card
     * and multiple other cards in their hand. 
     */
    @Test
    public void testPlayWildDraw4ComputerGood() {
        System.out.println("Test Play Wild Draw 4 Computer Good");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.wild_draw_4));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.green, Rank.zero));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.green, Rank.one));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.green, Rank.two));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.red, Rank.zero));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.red, Rank.one));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getDiscardPileTop().getColour() == Colour.green;
        result = result && controller.getGame().getPlayers().get(1).getNumCards() == 4;
        result = result && controller.getGame().getCurrentPlayer().getPlayerID().equals("Player 2");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has a single wild draw 4 card
     * and no other cards in their hand. 
     */
    @Test
    public void testPlayWildDraw4ComputerBoundary() {
        System.out.println("Test Play Wild Draw 4 Computer Boundary");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.wild_draw_4));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getDiscardPileTop().getColour() == Colour.green;
        result = result && controller.getGame().getPlayers().get(1).getNumCards() == 4;
        result = result && controller.getGame().getCurrentPlayer().getPlayerID().equals("Player 2");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has no playable wild draw 4 cards in their hand. 
     */
    @Test
    public void testPlayWildDraw4ComputerBad() {
        System.out.println("Test Play Wild Draw 4 Computer Bad");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue, Rank.wild_draw_4));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue, Rank.five));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.green, Rank.two));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.red, Rank.zero));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getDiscardPileTop().getColour() == Colour.blue;
        result = result && controller.getGame().getPlayers().get(1).getNumCards() == 0;
        result = result && controller.getGame().getCurrentPlayer().getPlayerID().equals("Player 1");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has multiple playable skip 
     * cards in their hand. 
     */
    @Test
    public void testPlaySkipComputerGood() {
        System.out.println("Test Skip Computer Good");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.skip));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.skip));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getCurrentPlayer().getPlayerID().equals("Player 2");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has a single playable skip 
     * card in their hand. 
     */
    @Test
    public void testPlaySkipComputerBoundary() {
        System.out.println("Test Skip Computer Boundary");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.skip));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getCurrentPlayer().getPlayerID().equals("Player 2");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of playTurnComputer() when the player has no playable skip 
     * cards in their hand. 
     */
    @Test
    public void testPlaySkipComputerBad() {
        System.out.println("Test Skip Computer Bad");
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.blue,Rank.zero));
        controller.getGame().getCurrentPlayer().getHand().addCard(new UnoCard(Colour.green,Rank.skip));
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        controller.playTurnComputer(player);

        boolean expResult = true;
        boolean result = controller.getGame().getCurrentPlayer().getPlayerID().equals("Player 1");
        assertEquals(expResult, result);
    }
    
    
}
