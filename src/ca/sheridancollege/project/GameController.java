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
 * The Game Controller class - interface between the models and the GameView class.
 * @author Taryn Nelson
 * @since 2020-04-13
 */
public class GameController {
    /**
     * The current UnoGame.
     */
    private UnoGame game;
    
    /**
     * The GameView for this controller.
     */
    private GameView gameView;
    
    public GameController(){
        gameView = new GameView();
    }
    
    /**
     * Gets a game name from the player and creates and returns and UnoGame 
     * with that name.
     * @return an UnoCame
     */
    final public UnoGame createGame(){
        String gameName = "";
        while(gameName.equals("")){
           gameView.promptGameName();
           gameName = gameView.getInput();
        }
        return new UnoGame(gameName);
    }
    
    /**
     * Creates human and computerized players according to user input and stores
     * them in the UnoGame. Must have 2 - 10 players and the first player will 
     * be human.
     */
    public void createPlayers(){ 
        
        String playerType = "";
        String playerName = "";
        while(game.getPlayers().size() < 2 || (game.getPlayers().size() >= 2 && game.getPlayers().size() < 10 && !playerName.equalsIgnoreCase("x") && !playerType.equalsIgnoreCase("x"))){
            playerType = "";
            gameView.printNumPlayers(game);
            
            if(game.getPlayers().size() > 0){
                gameView.printPromptTypePlayer();       

                while(!(playerType.equalsIgnoreCase("Computer") || playerType.equalsIgnoreCase("Human") || playerType.equalsIgnoreCase("x"))){
                    playerType = gameView.getInput();
                }
            }
            else{
                gameView.printTypePlayer();
                playerType = "Human";
            }
            
            if(!playerType.equalsIgnoreCase("x")){
                gameView.promptPlayerName();
                playerName = gameView.getInput();
            }  
            
            if(!playerName.equalsIgnoreCase("x") && !playerName.equals("") && playerType.equalsIgnoreCase("human")){
                game.addHumanPlayer(playerName);
            }
            else if(!playerName.equals("x") && !playerName.equals("") && playerType.equalsIgnoreCase("computer")){
                game.addComputerizedPlayer(playerName);
            }
            else if((playerName.equalsIgnoreCase("x") || playerType.equalsIgnoreCase("x")) && game.getPlayers().size() < 2){
                gameView.promptMorePlayers();
            }
        }
    }
    
    /**
     * Sets up and completes a round of Uno with players in UnoGame. A round is 
     * over when one player has played their last card.
     */
    public void playRound(){       
        game.play();
        game.setIntialPlayer();
        specialActionInitial(game.getDiscardPileTop());
        Player player;
        while(true){
            player = game.getCurrentPlayer();
            
            gameView.hidePrevTurn();
            gameView.printGameName(game);
            
            if(player instanceof HumanPlayer){
                playTurnHuman((HumanPlayer)player);
            }
            else{
                playTurnComputer((ComputerizedPlayer)player);
            }
            
            if(player.getNumCards() == 0){
                break;
            }
            game.nextTurn();
        }
        gameView.declareRoundWinner(player);
        gameView.printLeftOver(game.getPlayers());
        game.tallyRoundPoints(player);
    }
    
    /**
     * Allows a ComputerizedPlayer to play their turn. They will play a card if 
     * they can, and draw a card if they can't. If the drawn card is playable 
     * they will play it.
     * @param player A ComputerizedPlayer.
     */
    public void playTurnComputer(ComputerizedPlayer player){
        gameView.printTurnStartText(game, player);
        gameView.printComputerTurn(player);
        UnoCard playedCard = player.playCard(game.getDiscardPile());
        specialActionComputer(playedCard);
   
        if(playedCard == null){
           UnoCard drawnCard = player.drawCards(1, game.getDrawPile());
           playedCard = player.playCard(game.getDiscardPile());
           specialActionComputer(playedCard);
        }
        
        if(playedCard == null){
            gameView.printNoCardPlayed();
        }else{
            gameView.printCardPlayed(playedCard);
        }
        
    }
    
    /**
     * Allows a HumanPlayer to play their turn. User may play one of their cards
     * if playable, draw a card then play it if it is playable, or exit the game.
     * @param player a HumanPlayer.
     */
    public void playTurnHuman(HumanPlayer player){
        String userChoice;
        
        gameView.printTurnStartText(game, player);
        
        while(true){
            
            gameView.printCardHand(player);
            userChoice = gameView.getInput();
            Hand hand = player.getHand();
            
            if(userChoice.matches("\\d+") && hand.isPlayable(Integer.parseInt(userChoice), game.getDiscardPile())){
               UnoCard playedCard = player.getHand().getCard(Integer.parseInt(userChoice) - 1);     
               player.playCard(playedCard, game.getDiscardPile()); 
               specialActionHuman(playedCard);
               break;
            }
            else if(userChoice.matches("\\d+") && !hand.isPlayable(Integer.parseInt(userChoice), game.getDiscardPile())){
                gameView.printCardNotPlayable();
            }
            else if(userChoice.equals("d")){
                UnoCard drawnCard = player.drawCards(1,game.getDrawPile());
                gameView.printCardDrawn(drawnCard);
                if(player.getHand().isPlayable(drawnCard, game.getDiscardPile())){
                    gameView.printPromptPlayCard();
                    while(!(userChoice.equals("y") || userChoice.equals("n"))){
                        userChoice = gameView.getInput();
                    }
                    if(userChoice.equals("y")){
                        player.playCard(drawnCard, game.getDiscardPile());
                    }
                }
                break;
            }
            else if(userChoice.equals("x")){
                gameView.printPromptLeave();
                while(!(userChoice.equals("y") || userChoice.equals("n"))){
                        userChoice = gameView.getInput();
                    }
                if(userChoice.equals("y")){
                    gameView.printPlayerRemoved(player);
                    game.removePlayer(player);
                    if(game.getNumPlayers() == 1){
                        gameView.printEndGame();
                        System.exit(0);
                    }
                    break;
                }          
            }
        }
        
    }
    
    /**
     * Applies the effects of specially ranked cards to the game.
     * @param playedCard The UnoCard just played.
     */
    public void specialActionHuman(UnoCard playedCard){
        switch(playedCard.getRank()){
            case wild_draw_4:
                game.nextPlayerDraw(4);
            case wild:
                String color = "";
                
                gameView.printPromptColour();
                while(!(color.equalsIgnoreCase("blue") || color.equalsIgnoreCase("green") || color.equalsIgnoreCase("red") || color.equalsIgnoreCase("yellow"))){
                    color = gameView.getInput();
                }
                
                game.wild(color);             
                break;
            case draw_2:
                game.nextPlayerDraw(2);
                break;
            case reverse:
                game.reverse();
                break;
            case skip:
                game.skip();
                break;
        }
        
    }
    
    /**
     * Applies the effects of specially ranked cards to the game.
     * @param playedCard The UnoCard just played.
     */
    public void specialActionComputer(UnoCard playedCard){
        ComputerizedPlayer player = (ComputerizedPlayer)game.getCurrentPlayer();
        if(playedCard != null){
            switch(playedCard.getRank()){
            case wild_draw_4:
                game.nextPlayerDraw(4);
            case wild:
                String color = player.chooseWildColour();      
                game.wild(color);             
                break;
            case draw_2:
                game.nextPlayerDraw(2);
                break;
            case reverse:
                game.reverse();
                break;
            case skip:
                game.skip();
                break;
            }
        }
    }
    
    public void specialActionInitial(UnoCard topCard){
        switch(topCard.getRank()){
            case draw_2:
                game.getCurrentPlayer().drawCards(2, game.getDrawPile());
                break;
            case reverse:
                game.reverse();
                game.skip();
                break;
            case skip:
                game.skip();
                break;
        }
    }
    
    /**
     * Used for testing purposes.
     * @return The UnoGame associated with this controller.
     */
    protected UnoGame getGame(){
        return game;
    }
    
    /**
     * Used for testing purposes.
     * @param game IUnoGame to associate with this controller.
     */
    protected void setGame(UnoGame game){
        this.game = game;
    }
     
    /**
     * Begins the  Uno Game.
     */
    final public void play(){
        game = createGame();
        createPlayers();
        while(!game.isWinner()){
            playRound(); 
        } 
        Player winner = game.findWinner();
        gameView.declareWinner(winner);
        gameView.closeScanner();
    }
}