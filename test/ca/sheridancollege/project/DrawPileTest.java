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
 * A class to test the auto-regeneration of the DrawPile.
 * @author Taryn Nelson
 */
public class DrawPileTest {
   GameController controller;
    
    public DrawPileTest() {
        controller = new GameController();  
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Creates and sets up a new UnoGame with 1 computerized player.
     */
    @Before
    public void setUp() {
        UnoGame game = new UnoGame("TestDrawPile");
        controller.setGame(game);
        controller.getGame().setDrawPile();
        controller.getGame().addComputerizedPlayer("Player 1");
        controller.getGame().setCurrentPlayer(0);
    }
    
    @After
    public void tearDown() {
        System.out.println("Test complete.\n");
    }
    
    /**
     * Tests whether a DrawPile regenerates when a player tries to draw more 
     * cards than it contains.
     */
    @Test
    public void testRegenerateDrawPileGood() {
        System.out.println("Test Regenerate DrawPile Good");
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        int numCards = controller.getGame().getDrawPile().getSize() + 10;
        player.drawCards(numCards, controller.getGame().getDrawPile());
        
        System.out.println("New Drawpile");
        for(UnoCard card: controller.getGame().getDrawPile().getCards()){
            System.out.println(card.toString());
        }

        boolean expResult = true;
        boolean result = player.getNumCards() == numCards;
        result = result && controller.getGame().getDrawPile().getSize() > 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Tests whether a DrawPile regenerates when a player tries to draw one more 
     * card than it contains.
     */
    @Test
    public void testRegenerateDrawPileBoundary() {
        System.out.println("Test Regenerate DrawPile Boundary");
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        int numCards = controller.getGame().getDrawPile().getSize() + 1;
        player.drawCards(numCards, controller.getGame().getDrawPile());
        
        System.out.println("New Drawpile");
        for(UnoCard card: controller.getGame().getDrawPile().getCards()){
            System.out.println(card.toString());
        }

        boolean expResult = true;
        boolean result = player.getNumCards() == numCards;
        result = result && controller.getGame().getDrawPile().getSize() > 0;
        assertEquals(expResult, result);
    }
    
    /**
     * Tests whether the DrawPile regenerates only after a player tries to draw 
     * more cards than it contains. DrawPile should not regenerate until a
     * player attempts to draw more cards.
     */
    @Test
    public void testRegenerateDrawPileBad() {
        System.out.println("Test Regenerate DrawPile Bad");
        
        ComputerizedPlayer player = (ComputerizedPlayer)controller.getGame().getCurrentPlayer();
        int numCards = controller.getGame().getDrawPile().getSize();
        player.drawCards(numCards, controller.getGame().getDrawPile());
        
        System.out.println("New Drawpile");
        for(UnoCard card: controller.getGame().getDrawPile().getCards()){
            System.out.println(card.toString());
        }

        boolean expResult = true;
        boolean result = player.getNumCards() == numCards;
        result = result && controller.getGame().getDrawPile().getSize() == 0;
        assertEquals(expResult, result);
    }
}
