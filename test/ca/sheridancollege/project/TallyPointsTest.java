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
 * Class to test tallying of points at the end of a round.
 * @author Taryn Nelson
 * @since 2020-04-19
 */
public class TallyPointsTest {
    GameController controller;
    
    public TallyPointsTest() {
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
    
    @Test
    public void testTallyPointsGood() {
        System.out.println("Test Tally Points Good");
        
        Player player1 = controller.getGame().getPlayers().get(0);
        Player player2 = controller.getGame().getPlayers().get(1);
        Player player3 = controller.getGame().getPlayers().get(2);
        
        player2.getHand().addCard(new UnoCard(Colour.blue, Rank.zero));
        player2.getHand().addCard(new UnoCard(Colour.red, Rank.six));
        player2.getHand().addCard(new UnoCard(Colour.red, Rank.one));
        
        player3.getHand().addCard(new UnoCard(Colour.red, Rank.nine));
        player3.getHand().addCard(new UnoCard(Colour.blue, Rank.nine));
        
        controller.getGame().tallyRoundPoints(player1);

        boolean expResult = true;
        boolean result = player1.getScore() == 25;
        result = result && player2.getScore() == 0;
        result = result && player3.getScore() == 0;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTallyPointsBoundary() {
        System.out.println("Test Tally Points Boundary");
        
        Player player1 = controller.getGame().getPlayers().get(0);
        Player player2 = controller.getGame().getPlayers().get(1);
        Player player3 = controller.getGame().getPlayers().get(2);
        
        player2.getHand().addCard(new UnoCard(Colour.red, Rank.one));
        
        controller.getGame().tallyRoundPoints(player1);

        boolean expResult = true;
        boolean result = player1.getScore() == 1;
        result = result && player2.getScore() == 0;
        result = result && player3.getScore() == 0;
        assertEquals(expResult, result);
    }
    
    @Test
    public void testTallyPointsBad() {
        System.out.println("Test Tally Points Bad");
        
        Player player1 = controller.getGame().getPlayers().get(0);
        Player player2 = controller.getGame().getPlayers().get(1);
        Player player3 = controller.getGame().getPlayers().get(2);
        
        controller.getGame().tallyRoundPoints(player1);

        boolean expResult = true;
        boolean result = player1.getScore() == 0;
        result = result && player2.getScore() == 0;
        result = result && player3.getScore() == 0;
        assertEquals(expResult, result);
    }
}
