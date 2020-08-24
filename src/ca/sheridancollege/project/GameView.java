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
import java.util.Scanner;

/**
 * The view class for the game.
 * @author Taryn Nelson
 * @since 2020-04-17
 */
public class GameView {
    private Scanner input;
    
    /**
     * 
     */
    public GameView(){
        input = new Scanner(System.in);
    }
    
    public void closeScanner(){
        input.close();
    }
    
    public void promptGameName(){
        System.out.println("Title your game:"); 
    }
    
    /**
     * Takes in user input
     * @return String containing user input.
     */
    public String getInput(){
        return input.nextLine();
    }
    
    public void hidePrevTurn(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
    }
    
    /**
     * Declares a player to winner of the round.
     * @param player round winner
     */
    public void declareRoundWinner(Player player){
        System.out.println("\nThe winner if this round is: " + player.getPlayerID());
    }
    
    public void printNoCardPlayed(){
        System.out.println("No card played.");
    }
    
    public void printCardPlayed(UnoCard playedCard){
        System.out.println("Card played: " + playedCard.toString());
    }
    
    /**
     * Prints the game status which is all the player name, their scores, and 
     * the number of cards in their hands.
     * @param game UnoGame
     * @param player Player whose turn it is.
     */
    public void printTurnStartText(UnoGame game, Player player){
        System.out.println("Turn for Player: " + player.getPlayerID());
        System.out.println("Current status:\n");
        System.out.println(game.getGameStatus());
        System.out.println("Card at top of discard pile:");
        System.out.println(game.getDiscardPileTop().toString());
    }
    
    /**
     * Prints the cards in the player's hand.
     * @param player Player whose turn it is
     */
    public void printCardHand(Player player){
        System.out.println("Choose a card from your hand to play."); 
        System.out.println("Current cards in hand: \n" + player.getHand().toString());
        System.out.println("Choose a card number to play it. Type 'd' to draw a card and 'x' to exit the game.");
    }
    
    /**
     * Prints the card that the player just drew from the DrawPile.
     * @param drawnCard UnoCard just drawn from DrawPile.
     */
    public void printCardDrawn(UnoCard drawnCard){
        System.out.print("You drew the following card:");
        System.out.println(drawnCard.toString());
    }
    
    public void printPromptPlayCard(){
        System.out.println("Would you like to play this card (y/n)?");
    }
    
    public void printPromptLeave(){
        System.out.println("Are you sure you want to leave the game(y/n)?");
    }
    
    /**
     * Prints a message verifying provided player was removed form the game.
     * @param player player to be removed
     */
    public void printPlayerRemoved(Player player){
        System.out.println("Player " + player.getPlayerID() + " removed from game.");
    }
    
    public void printPromptColour(){
        System.out.println("Select a colour for the discard pile card:");
    }
    
    /**
     * Prints the number fo players registered to the UnoGame.
     * @param game UnoGame
     */
    public void printNumPlayers(UnoGame game){
        System.out.println("You may have 2-10 players. Current number of Players: " + game.getPlayers().size());
    }
    
    public void printPromptTypePlayer(){
        System.out.println("What kind of player are you making: human or computer? Type x to stop making players.");    
    }
    
    public void printTypePlayer(){
        System.out.println("Your first player will be human.");
    }
    
    public void promptPlayerName(){
        System.out.println("Enter a player name, type x to stop creating players.");
    }
    
    public void promptMorePlayers(){
        System.out.println("You must have at least two players.");
    }
    
    public void printCardNotPlayable(){
        System.out.println("Card not playable.");
    }
    
    /**
     * Prints The winner of the game and their score.
     * @param player winning player
     */
    public void declareWinner(Player player){
        System.out.println("");
        System.out.println("The winner of the game is: " + player.getPlayerID());
        System.out.println("Final score of winner: " + player.getScore() + " points");
    }
    
    public void printEndGame(){
        System.out.println("There is only one player remaining, the game cannot continue.");
    }
    
    /**
     * Prints which player's turn it is.
     * @param player a player
     */
    public void printComputerTurn(Player player){
        System.out.println("Player turn: " + player.getPlayerID());
    }
    
    /**
     * Prints the name of the UnoGame.
     * @param game UnoGame
     */
    public void printGameName(UnoGame game){
        System.out.println("Game name: " + game.getGameName());
    }
    
    /**
     * Prints all remaining cards in each player's hand.
     * @param players The ArrayList of players in the game.
     */
    public void printLeftOver(ArrayList<Player> players){
        System.out.println("\nCards remaining:\n");
        for(Player player: players){
            System.out.println("Player: " + player.getPlayerID());
            for(UnoCard card: player.getHand().getCards()){
                System.out.println(card.toString());
            }
            System.out.println();
        }
    }
    
    /**
     * Creates the GameController and begins play.
     * @param args command line arguments, unused. 
     */
    public static void main(String[] args) {
        GameController gameApp = new GameController();  
        gameApp.play();  
    }
}
