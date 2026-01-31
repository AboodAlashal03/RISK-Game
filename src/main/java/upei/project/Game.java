package upei.project;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.min;
public class Game {
    // Class variables
    Player player0;
    Player player1;
    Player player2;
    private int turnCount;
    private GameBoard gameBoard;
    private GameState gameState;

    // Constructor for the Game class
    public Game( Player player0, Player player1, Player player2) throws IOException {
        // Initialize the game board and state
        this.gameBoard = new GameBoard(player0, player1, player2);
        this.gameState = new GameState();
        // Initialize players and turn count
        this.player0 = player0;
        this.player1 = player1;
        this.player2 = player2;
        this.turnCount = 0;
    }

    public Player getPlayer0() {
        return player0;
    }
    public Player getPlayer1() {
        return player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public int getTurnCount() {
        return turnCount;
    }
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    public GameState getGameState() {
        return gameState;
    }
    // Getter methods for various attributes
    // Method to get the current player based on the turn count
    public Player getCurrentPlayer() {
        return switch (turnCount % 3) {
            case 0 -> player0;
            case 1 -> player1;
            case 2 -> player2;
            default -> null; // Handle unexpected cases
        };
    }

    // Helper method to roll a specified number of dice
    private int[] rollDice(int diceCount, GameState gameState){
        Random rand = new Random();
        int[] diceRolls = new int[diceCount];
        for (int i = 0; i < diceCount; i++){
            diceRolls[i] = rand.nextInt(6) + 1;
        }
        Arrays.sort(diceRolls);
        for (int i = 0; i < diceRolls.length / 2; i++) {
            int temp = diceRolls[i]; // swap numbers
            diceRolls[i] = diceRolls[diceRolls.length - 1 - i];
            diceRolls[diceRolls.length - 1 - i] = temp;
        }

        return diceRolls;
    }
    // Method to handle recruiting units for a player
    private void recruit(Player player, GameState gameState) {
        Random rand = new Random();
        int regionModifier = 0; // Extra units per turn based on regions controlled
        int recruitCount = 0; //Total units to be recruited
        // Calculate region modifier
        for (String region : player.getControlledRegions()){
            switch (region){
                case "Storvhal" -> regionModifier = 2;
                case "Mortwald", "Megaborealis" -> regionModifier = 3;
                case "Kaelac's Bane", "Hyperia" -> regionModifier = 4;
                case "Dontoria", "Oteck Hivesprawl" -> regionModifier = 5;
            }
        }
        // Calculate recruit count based on territories and region modifiers
        recruitCount = Math.floorDiv(player.getTerritoryCount(), 3) + regionModifier;
        if (recruitCount < 3){
            recruitCount = 3;
        }
        //if (rand.nextInt(2) == 0) {
        player.addUnit("Single", recruitCount);
        gameState.addTotalUnitsRecruitedCount(player.getPlayerNumber(), recruitCount);
            /*else {
                player.addUnit("Triple", Math.floorDiv(recruitCount, 3));
                recruitCount = recruitCount % 3;
                player.addUnit("Single", recruitCount);
            }*/
        //}
        player.deployUnits();
    }
    // Method to handle maneuvering units for a player
    private void maneuver(Player player){
        int highestArmyCount = -1;
        int lowestArmyCount = -1;
        Territory highestArmyTerritory = null;
        Territory lowestArmyTerritory = null;
        for (Territory t: player.getTerritories()){
            if (lowestArmyCount == -1 && highestArmyCount == -1){
                lowestArmyCount = t.getArmyCount();
                lowestArmyTerritory = t;
                highestArmyCount = t.getArmyCount();
                highestArmyTerritory = t;
            }
            if (t.getArmyCount() > highestArmyCount){
                highestArmyCount = t.getArmyCount();
                highestArmyTerritory = t;
            }
            else if (t.getArmyCount() < lowestArmyCount){
                lowestArmyCount = t.getArmyCount();
                lowestArmyTerritory = t;
            }
        }
        if ( highestArmyTerritory != null && lowestArmyTerritory != null) {
            int transferCount = highestArmyTerritory.getArmyCount() - Math.floorDiv(highestArmyTerritory.getArmyCount() + lowestArmyTerritory.getArmyCount(), 2);
            highestArmyTerritory.setArmyCount(highestArmyTerritory.getArmyCount() - transferCount);
            lowestArmyTerritory.setArmyCount(lowestArmyTerritory.getArmyCount() + transferCount);
            /*
            System.out.println("Player " + player.getPlayerNumber() + " has TRANSFERRED " + transferCount + " units from " +
                    highestArmyTerritory.getName() + " to " + lowestArmyTerritory.getName());
                    */
        }
    }
    private void attack(GameBoard gameBoard, GameState gameState, Territory attacker, Territory defender){
        while (attacker.getArmyCount() > 1 && defender.getArmyCount() > 0 && attacker.getOwnerNumber() != defender.getOwnerNumber()){
            Unit attackingParty = attacker.generateAttackingParty();
            gameState.incrementAttackCount(attacker.getOwnerNumber());
            int attackCount = attackingParty.getUnitCount();
            Unit defendingParty = defender.generateDefendingParty();
            gameState.incrementDefenceCount(defender.getOwnerNumber());
            int defenceCount = defendingParty.getUnitCount();
            int[] attackDice = rollDice(attackCount, gameState);
            gameState.addDiceRollCount(attacker.getOwnerNumber(), attackCount);
            int[] defenceDice = rollDice(defenceCount, gameState);
            gameState.addDiceRollCount(defender.getOwnerNumber(), defenceCount);
            /*
            System.out.println("Player " + attacker.getOwnerNumber() + " is attacking Player " + defender.getOwnerNumber() +
                    " from " + attacker.getName() + " with " + attackCount + " units (" + attacker.getArmyCount() +" remaining) using " + Arrays.toString(attackDice));
            System.out.println("Player " + defender.getOwnerNumber() + " is defending " + defender.getName() + " with " +
                    defendingParty.getUnitCount() + " units (" + defender.getArmyCount() +" remaining) using " + Arrays.toString(defenceDice));
                    */
            for (int i = 0; i < min(attackCount, defenceCount); i++){
                if (attackDice[i] > defenceDice[i]){
                    defendingParty.setUnitCount(defendingParty.getUnitCount() - 1);
                    defender.setArmyCount(defender.getArmyCount() - 1);
                    gameState.incrementTotalUnitsLostCount(defender.getOwnerNumber(), 1);
                    gameBoard.getPlayerByNumber(defender.getOwnerNumber()).removeUnit("Single", 1);
                }
                else{
                    attackingParty.setUnitCount(attackingParty.getUnitCount() - 1);
                    attacker.setArmyCount(attacker.getArmyCount() - 1);
                    gameState.incrementTotalUnitsLostCount(attacker.getOwnerNumber(), 1);
                    gameBoard.getPlayerByNumber(attacker.getOwnerNumber()).removeUnit("Single", 1);
                }
            }
            if (defender.getArmyCount() == 0){
                /*
                System.out.println("Player " + attacker.getOwnerNumber() + " has CONQUERED " + defender.getName() + " from Player " +
                        defender.getOwnerNumber() + " with " + attackingParty.getUnitCount() + " units remaining");
                        */
                gameState.incrementSuccessfulAttackCount(attacker.getOwnerNumber());
                gameState.incrementFailedDefenceCount(defender.getOwnerNumber());
                // Attack successful
                // Remove attacking units from attacking territory
                attacker.setArmyCount(attacker.getArmyCount() - attackingParty.getUnitCount());
                // Transfer territory ownership
                gameState.incrementTerritoriesLostCount(defender.getOwnerNumber());
                gameBoard.getPlayerByNumber(defender.getOwnerNumber()).removeTerritory(defender);
                gameBoard.getPlayerByNumber(attacker.getOwnerNumber()).addTerritory(defender);
                gameState.incrementTerritoriesConqueredCount(attacker.getOwnerNumber());
                // Add attacking units to defending territory
                defender.setArmyCount(defender.getArmyCount() + attackingParty.getUnitCount());
            }
        }
        if (attacker.getOwnerNumber() != defender.getOwnerNumber()){
            /*
            System.out.println("Player " + attacker.getOwnerNumber() + " has FAILED TO CONQUER " + defender.getName() + " from Player " +
                    defender.getOwnerNumber());
                    */
            gameState.incrementFailedAttackCount(attacker.getOwnerNumber());
            gameState.incrementSuccessfulDefenceCount(defender.getOwnerNumber());
        }
    }


    public void startGame() {
        // Add Player 0's territories and deploy units
        player0.addTerritory(gameBoard.getTerritoryByRegion("Mortwald"));
        player0.addTerritory(gameBoard.getTerritoryByRegion("Oteck Hivesprawl"));
        player0.addTerritory(gameBoard.getTerritoryByName("Mortwald-Oteck Fortwall"));
        player0.addUnit("Single", 30);
        player0.deployUnits();

        // Add Player 1's territories and deploy units
        player1.addTerritory(gameBoard.getTerritoryByRegion("Dontoria"));
        player1.addTerritory(gameBoard.getTerritoryByRegion("Megaborealis"));
        player1.addTerritory(gameBoard.getTerritoryByRegion("Storvhal"));
        player1.addTerritory(gameBoard.getTerritoryByName("Dontoria-Megaborealis Fortwall"));
        player1.addUnit("Single", 30);
        player1.deployUnits();

        // Add Player 2's territories and deploy units
        player2.addTerritory(gameBoard.getTerritoryByRegion("Kaelac's Bane"));
        player2.addTerritory(gameBoard.getTerritoryByRegion("Hyperia"));
        player2.addTerritory(gameBoard.getTerritoryByName("Kaelac's Bane-Hyperia Fortwall"));
        player2.addUnit("Single", 30);
        player2.deployUnits();

        // Main game loop
        while (player0.getTerritoryCount() < 42 && player1.getTerritoryCount() < 42 && player2.getTerritoryCount() < 42 && turnCount < 100) {
            /*
            System.out.println("----------------------------\n" + "TURN " + turnCount + "\n----------------------------");
            */
            for (Player player : gameBoard.getPlayers()) {
                if (player.getTerritoryCount() > 0) {
                    /*
                    System.out.println("Player " + player.getPlayerNumber() + "'s turn");
                    */
                    gameState.incrementTurnCount(player.getPlayerNumber());
                }
                else{
                    /*
                    System.out.println("Player " + player.getPlayerNumber() + " has been eliminated");
                    */
                    continue;
                }
                // Recruit units
                recruit(player, gameState);
                // Attack
                switch (player.getPlayerStrategy()) {
                    case "Aggressive" -> {
                        // Attacks every turn
                        aggressiveLoop:
                        for (Territory territory : player.getTerritories()) {
                            Territory[] borders = gameBoard.getBorders(territory);
                            for (Territory borderingTerritory : borders) {
                                if (!player.checkForTerritory(borderingTerritory) && territory.getArmyCount() >= 2) {
                                    attack(gameBoard, gameState, territory, borderingTerritory);
                                    break aggressiveLoop;
                                }
                            }
                        }
                    }
                    case "Opportunist" -> {
                        // Attacks if advantageous
                        opportunistLoop:
                        for (Territory territory : player.getTerritories()) {
                            Territory[] borders = gameBoard.getBorders(territory);
                            for (Territory borderingTerritory : borders) {
                                if (!player.checkForTerritory(borderingTerritory) && territory.getArmyCount() >= 2
                                        && territory.getArmyCount() > borderingTerritory.getArmyCount()) {
                                    attack(gameBoard, gameState, territory, borderingTerritory);
                                    break opportunistLoop;
                                }
                            }
                        }
                    }
                }
                // Maneuver
                maneuver(player);
                // Update game state
                //gameState.update(player);
            }
            turnCount++;
        }
        // Print the results
        //gameState.printResults();
        int highestTerritoryCount = -1;
        int winner = -1;
        for (Player player : gameBoard.getPlayers()) {
            /*
            System.out.println("----------------------------\nPlayer " + player.getPlayerNumber() +
                    " has " + player.getTerritoryCount() + " territories\n----------------------------");
                    */
            if (player.getTerritoryCount() > highestTerritoryCount){
                highestTerritoryCount = player.getTerritoryCount();
                winner = player.getPlayerNumber();
            }

        }
        gameState.incrementWinCount(winner);
        for (Player player : gameBoard.getPlayers()) {
            if (player.getPlayerNumber() != winner){
                gameState.incrementLossCount(player.getPlayerNumber());
            }
        }
        gameState.logResults();
    }
}

