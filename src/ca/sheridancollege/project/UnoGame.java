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
 * Class to instantiate the UnoGame.
 * @author Taryn Nelson
 * @since 2020-04-13
 */
public class UnoGame extends Game{
    
    /**
     * The index of the current player in the Players ArrayList.
     */
    private int currentPlayer;
    
    /**
     * The direction of play. May be either right or left.
     */
    private String direction;
    
    /**
     * The draw pile of UnoCards.
     */
    private DrawPile drawPile;
    
    /**
     * The discard pile for UnoCards.
     */
    private DiscardPile discardPile;
    
    /**
     * Constructor creates the UnoGame with provided name, creates the Players
     * ArrayList and sets the direction initially to "right".
     * @param givenName name of the UnoGame.
     */
    public UnoGame(String givenName){
        super(givenName);
        setPlayers(new ArrayList());
        direction = "right";
    }
    
    /**
     * Sets up the game to play.
     */
   @Override
    public void play(){
        setDrawPile();
        setDiscardPile();
        dealHands(); 
    }
    
    /**
     * Determines if there is a winner among the Players.
     * @return true if there is a winner, false if there isn't.
     */
    public boolean isWinner(){
        boolean isWinner = false;
        
        for(Player player: getPlayers()){
            if(player.getScore() >= 500){
                isWinner = true;
            }
        }
        
        return isWinner;
    }
    
    /**
     * Creates a HumanPlayer and adds it to the Player ArrayList.
     * @param playerID name of the HumanPlayer.
     */
    public void addHumanPlayer(String playerID){
        Player player = new HumanPlayer(playerID);
        getPlayers().add(player);
    }
    
    /**
     * Creates a ComputerizedPlayer and adds it to the Player ArrayList.
     * @param playerID name of the ComputerizedPlayer.
     */
    public void addComputerizedPlayer(String playerID){
        Player player = new ComputerizedPlayer(playerID);
        getPlayers().add(player);
    }

    /**
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return getPlayers().get(currentPlayer);
    }

    /**
     * Sets the index of the current player to provided int.
     * @param currentPlayer new index of the current player.
     */
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    /**
     * Sets the current Player to a random Player.
     */
    public void setIntialPlayer(){
        int firstPlayer = (int)(Math.random() * (getPlayers().size()));
        setCurrentPlayer(firstPlayer);
    }
    
    /**
     * Calculates the value of cards left in non-winners Hands and adds the 
     * points to the winner's point total.
     * @param winningPlayer The winner of the round.
     */
    public void tallyRoundPoints(Player winningPlayer){
        int points = 0;
        
        for(Player player: getPlayers() ){
            for(UnoCard card: player.getHand().getCards()){
                points += card.getValue();
            }
        }
        
        winningPlayer.setScore(points);
    }
    
    /**
     * @return The Player who won the entire UnoGame. 
     */
    public Player findWinner(){
        for(Player player: getPlayers()){
            if(player.getScore() >= 500){
                return player;
            }
        }   
        return null;
    }
    
    /**
     * Removes 7 cards from the DrawPile for each Player and puts them in their 
     * Hands.
     */
    public void dealHands() {
        for(Player player: getPlayers()){
            player.getHand().getCards().clear();
            player.drawCards(7, drawPile);
        }
    }

    /**
     * Creates, fills, and shuffles the DrawPile for the UnoGame.
     */
    public void setDrawPile() {
        drawPile = new DrawPile();
        drawPile.generateDrawPile();
        drawPile.shuffle();
    }

    /**
     * Creates the DiscardPile and adds the first card to it from the DrawPile.
     */
    public void setDiscardPile(){
        discardPile = new DiscardPile();
        
        UnoCard card;
        do{
            card = drawPile.removeCard();
        }while(card.getRank() == Rank.wild || card.getRank() == Rank.wild_draw_4);
        discardPile.addCard(card);
    }

    /**
     * Changes the index of current player to the index of the next player.
     */
    public void nextTurn(){
        currentPlayer = getNextPlayer();
    }
        
    /**
     * @return The index of the player that next has their turn.
     */
    public int getNextPlayer(){
        int nextPlayer = currentPlayer;
        ArrayList<Player> players = getPlayers();
        if(direction.equals("right") && currentPlayer < players.size() - 1){
            nextPlayer++;
        }
        else if(direction.equals("right") && currentPlayer == players.size() - 1){
            nextPlayer = 0;
        }
        else if(direction.equals("left") && currentPlayer > 0){
            nextPlayer--;
        }
        else if(direction.equals("left") && currentPlayer == 0){
            nextPlayer = players.size() - 1;
        }
            
        return nextPlayer;
    }

    /**
     * Skips a player's turn.
     */
    public void skip() {
        nextTurn();
    }

    /**
     * Reverses the direction of play.
     */
    public void reverse() {
        if(direction.equals("right")){
            direction = "left";  
        }
        else if(direction.equals("left")){
            direction = "right";
        }
    }

    /**
     * Has the next player draw a certain number of cards and miss their turn.
     * @param numCards Number of UnoCards for the next player to draw.
     */
    public void nextPlayerDraw(int numCards) {
        nextTurn();
        getCurrentPlayer().drawCards(numCards, drawPile);
    }

    /**
     * Changes the colour of the card on top of the DiscardPile.
     * @param colour String containing colour to change card to.
     */
    public void wild(String colour) {
        Colour changeColour = null;
            if(colour.equalsIgnoreCase("blue")){
                changeColour = Colour.blue;
            }
            else if(colour.equalsIgnoreCase("yellow")){
                changeColour = Colour.yellow;
            }
            else if(colour.equalsIgnoreCase("red")){
                changeColour = Colour.red;
            }
            else if(colour.equalsIgnoreCase("green")){
                changeColour = Colour.green;
            }
        UnoCard card = discardPile.showTopCard();
        card.setColour(changeColour);
    }
    
    /**
     * @return String containing Player names, their scores, and the number of
     * UnoCards in their Hands.
     */
    public String getGameStatus(){
        String status = "";
        for(Player player: getPlayers()){
            status += player.getPlayerID() + " - Score: " + player.getScore() + ", Numbers of Cards: " + player.getNumCards() + "\n" ;
        }
        return status;
    }
        
    /**
     * @return The top UnoCard on the DiscardPile.
     */
    public UnoCard getDiscardPileTop(){
        return discardPile.showTopCard();
    }
        
    /**
     * @return The DrawPile.
     */
    public DrawPile getDrawPile(){
        return drawPile;
    }
        
    /**
     * Removes the player from the game.
     * @param player Player to remove.
     */
    public void removePlayer(Player player){
        getPlayers().remove(player);
    }
        
    /**
     * @return The DiscardPile.
     */
    public DiscardPile getDiscardPile(){
        return discardPile;
    } 
    
    /**
     * @return Number of Players in the UnoGame.
     */
    public int getNumPlayers(){
        return getPlayers().size();
    }
    
    /**
     * Used for testing purposes.
     * @return direction of play
     */
    protected String getDirection(){
        return direction;
    }
}
