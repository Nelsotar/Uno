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
 * A class to test the isWinner function in the UnoGame class.
 * @author Taryn Nelson
 * @since 2020-04-19
 */
public class isWinnerTest {
    
    GameController controller;
    
    public isWinnerTest() {
        controller = new GameController();  
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Creates and sets up a new UnoGame with 3 computerized players.
     */
    @Before
    public void setUp() {
        UnoGame game = new UnoGame("TestIsWinner");
        controller.setGame(game);
        controller.getGame().setDrawPile();
        controller.getGame().setDiscardPile();
        controller.getGame().getDiscardPile().addCard(new UnoCard(Colour.blue, Rank.zero));
        controller.getGame().addComputerizedPlayer("Player 1");
        controller.getGame().addComputerizedPlayer("Player 2");
        controller.getGame().addComputerizedPlayer("Player 3");
        controller.getGame().setCurrentPlayer(0);
    }
    
    @After
    public void tearDown() {
        System.out.println("Test complete.\n");
    }
    
    /**
     * Tests if there is a winner in the game when one player has well over 500 
     * points and if the correct winner is returned by findWinner().
     */
    @Test
    public void testIsWinnerGood() {
        System.out.println("Test isWinner Good");
        
        Player player1 = controller.getGame().getPlayers().get(0);
        Player player2 = controller.getGame().getPlayers().get(1);
        Player player3 = controller.getGame().getPlayers().get(2);
        
        player1.setScore(100);
        player2.setScore(700);
        player3.setScore(100);

        boolean expResult = true;
        boolean result = controller.getGame().isWinner();
        result = result && controller.getGame().findWinner().getPlayerID().equals("Player 2");
        assertEquals(expResult, result);
    }
    
    /**
     * Tests if there is a winner in the game when one player has exactly 500 
     * points and if the correct winner is returned by findWinner().
     */
    @Test
    public void testIsWinnerBoundary() {
        System.out.println("Test isWinner Boundary");
        
        Player player1 = controller.getGame().getPlayers().get(0);
        Player player2 = controller.getGame().getPlayers().get(1);
        Player player3 = controller.getGame().getPlayers().get(2);
        
        player1.setScore(100);
        player2.setScore(500);
        player3.setScore(250);

        boolean expResult = true;
        boolean result = controller.getGame().isWinner();
        result = result && controller.getGame().findWinner().getPlayerID().equals("Player 2");
        assertEquals(expResult, result);
    }
    
    /**
     * Tests if there is a winner in the game when all players have less than 
     * 500 points, also tests if findWinner() returns null.
     */
    @Test
    public void testIsWinnerBad() {
        System.out.println("Test isWinner Bad");
        
        Player player1 = controller.getGame().getPlayers().get(0);
        Player player2 = controller.getGame().getPlayers().get(1);
        Player player3 = controller.getGame().getPlayers().get(2);
        
        player1.setScore(100);
        player2.setScore(499);
        player3.setScore(250);

        boolean expResult = true;
        boolean result = !controller.getGame().isWinner();
        result = result && controller.getGame().findWinner() == null;
        assertEquals(expResult, result);
    }
}
