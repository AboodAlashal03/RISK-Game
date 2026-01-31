package upei.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 - RULES -
 Risk Warhammer 40,000
 3-5 Players (3 Players for this project)

 - MODIFIED WIN STATE -
 Player with the most territories at the end of 100 turns wins.
 This was chosen because the implemented strategies eventually reach a stalemate, regardless of how many turns are played
 (100 turns is enough to see the strategies play out).
 For example, the Opportunist Strategy only attacks if it is advantageous, so if an enemy (such as the pacifist which never attacks)
 has a numerical advantage, the game will stop progressing as the two players continually recruit units and never attack.

 - MODIFIED GAMEPLAY -
 Excludes Objective and Reward cards (because they aren't available online).
 Every Territory must have at least one army occupying it at all times.
 Recruiting is based off the original Risk rules:

 "Territories. At the beginning of every turn (including your first), count the
 number of territories you currently occupy, then divide the total by three
 (ignore any fraction). The answer is the number of armies you receive. Place
 the new armies on any territory you already occupy.

 Example: 11 territories = 3 armies
 14 territories = 4 armies
 17 territories = 5 armies

 You will always receive at least 3 armies on a turn, even if you occupy fewer
 than 9 territories.

 Continents. In addition, at the beginning of your turn you will receive
 armies for each continent you control. (To control a continent, you must
 occupy all its territories at the start of your turn.) To find the exact number
 of armies you’ll receive for each continent, look at the chart in the lower
 left-hand corner of the game board."

 Region Modifiers (AKA Continents) are as follows:

 Storvhal -> regionModifier = 2;
 Mortwald -> regionModifier = 3;
 Megaborealis -> regionModifier = 3;
 Kaelac's Bane -> regionModifier = 4;
 Hyperia -> regionModifier = 4;
 Dontoria -> regionModifier = 5;
 Oteck Hivesprawl -> regionModifier = 5;

 - STRATEGIES -
 Aggressive - Must attack a territory every turn regardless of advantage or disadvantage
 Pacifist - Will only defend, never attack
 Opportunist - Only attacks if it is advantageous

 LINK TO 40K RULES: <a href="https://boardgamegeek.com/thread/2577893/rules-online">CLICK HERE</a>
 LINK TO ORIGINAL RULES: <a href="https://www.hasbro.com/common/instruct/risk.pdf">CLICK HERE</a>
 */

public class SimulationExperiment {
    /**
     * The main method to run the simulation.
     * @param args command line arguments
     * @throws IOException if there's a problem with input/output
     */
    public static void main(String[] args) throws IOException {
        GameState.resetResults();
        // Run the simulation
        runSimulation();
    }

    /**
     * This method runs the game simulation.
     * It creates three players with different strategies (Aggressive, Pacifist, and Opportunist),
     * initializes the game, and starts it. The process is repeated 60 times.
     * Results from simulation are written to a log file (SimulationResults.txt).
     * @throws IOException if there's a problem with input/output
     */
    private static void runSimulation() throws IOException {
        // Play the game 60 times
        for (int i = 0; i < 60; i++) {
            // Create Players
            Player player0 = new Player(0, "Aggressive", "Marneus Calgar", 0);
            Player player1 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
            Player player2 = new Player(2, "Opportunist", "Autarch", 2);
            // Create Game
            Game game = new Game(player0, player1, player2);
            // Start Game
            game.startGame();
        }
        // Variables to store the results of the simulation
        int[] turnCount = new int[3];
        int[] winCount = new int[3];
        int[] lossCount = new int[3];
        int[] attackCount = new int[3];
        int[] successfulAttackCount = new int[3];
        int[] failedAttackCount = new int[3];
        int[] defenceCount = new int[3];
        int[] successfulDefenceCount = new int[3];
        int[] failedDefenceCount = new int[3];
        int[] diceRollCount = new int[3];
        int[] totalUnitsRecruitedCount = new int[3];
        int[] totalUnitsLostCount = new int[3];
        int[] territoriesConqueredCount = new int[3];
        int[] territoriesLostCount = new int[3];
        // Read the results from the simulation line by line
        try (BufferedReader br = new BufferedReader(new FileReader("SimulationResults.txt"))) {
            String line = br.readLine();
            while (line != null) {
                if (line.equals("Player,Turns,Wins,Losses,Attacks,Successful Attacks,Failed Attacks,Defences,Successful Defences," +
                        "Failed Defences,Dice Roll Count,Total Units Recruited,Total Units Lost,Territories Conquered,Territories Lost")) {
                    line = br.readLine();
                }
                // Split the line by commas
                String[] values = line.split(",");
                // Parse the values to integers
                int playerNumber = Integer.parseInt(values[0]);
                turnCount[playerNumber] = Integer.parseInt(values[1]);
                winCount[playerNumber] = Integer.parseInt(values[2]);
                lossCount[playerNumber] = Integer.parseInt(values[3]);
                attackCount[playerNumber] = Integer.parseInt(values[4]);
                successfulAttackCount[playerNumber] = Integer.parseInt(values[5]);
                failedAttackCount[playerNumber] = Integer.parseInt(values[6]);
                defenceCount[playerNumber] = Integer.parseInt(values[7]);
                successfulDefenceCount[playerNumber] = Integer.parseInt(values[8]);
                failedDefenceCount[playerNumber] = Integer.parseInt(values[9]);
                diceRollCount[playerNumber] = Integer.parseInt(values[10]);
                totalUnitsRecruitedCount[playerNumber] = Integer.parseInt(values[11]);
                totalUnitsLostCount[playerNumber] = Integer.parseInt(values[12]);
                territoriesConqueredCount[playerNumber] = Integer.parseInt(values[13]);
                territoriesLostCount[playerNumber] = Integer.parseInt(values[14]);
                line = br.readLine();

            }
        }
        // Print the results of the simulation
        System.out.println("--SIMULATION RESULTS AFTER 60 GAMES--");
        System.out.println("- Player 0 -");
        System.out.println("Strategy: Aggressive" + "\nTotal Turns: " + turnCount[0] +"\nWins: " + winCount[0] + "\nLosses: "
                + lossCount[0] + "\nAttacks: " + attackCount[0] + "\nSuccessful Attacks: " + successfulAttackCount[0]
                + "\nFailed Attacks: " + failedAttackCount[0] + "\nDefences: " + defenceCount[0] + "\nSuccessful Defences: "
                + successfulDefenceCount[0] + "\nFailed Defences: " + failedDefenceCount[0] + "\nDice Rolls: "
                + diceRollCount[0] + "\nTotal Units Recruited: " + totalUnitsRecruitedCount[0] + "\nTotal Units Lost: "
                + totalUnitsLostCount[0] + "\nTerritories Conquered: " + territoriesConqueredCount[0] + "\nTerritories Lost: "
                + territoriesLostCount[0] + "\n");
        System.out.println("- Player 1 -");
        System.out.println("Strategy: Pacifist" + "\nTotal Turns: " + turnCount[1] + "\nWins: " + winCount[1] + "\nLosses: "
                + lossCount[1] + "\nAttacks: " + attackCount[1] + "\nSuccessful Attacks: " + successfulAttackCount[1]
                + "\nFailed Attacks: " + failedAttackCount[1] + "\nDefences: " + defenceCount[1] + "\nSuccessful Defences: "
                + successfulDefenceCount[1] + "\nFailed Defences: " + failedDefenceCount[1] + "\nDice Rolls: "
                + diceRollCount[1] + "\nTotal Units Recruited: " + totalUnitsRecruitedCount[1] + "\nTotal Units Lost: "
                + totalUnitsLostCount[1] + "\nTerritories Conquered: " + territoriesConqueredCount[1] + "\nTerritories Lost: "
                + territoriesLostCount[1] + "\n");
        System.out.println("- Player 2 -");
        System.out.println("Strategy: Opportunist" + "\nTotal Turns: " + turnCount[2] + "\nWins: " + winCount[2] + "\nLosses: "
                + lossCount[2] + "\nAttacks: " + attackCount[2] + "\nSuccessful Attacks: " + successfulAttackCount[2]
                + "\nFailed Attacks: " + failedAttackCount[2] + "\nDefences: " + defenceCount[2] + "\nSuccessful Defences: "
                + successfulDefenceCount[2] + "\nFailed Defences: " + failedDefenceCount[2] + "\nDice Rolls: "
                + diceRollCount[2] + "\nTotal Units Recruited: " + totalUnitsRecruitedCount[2] + "\nTotal Units Lost: "
                + totalUnitsLostCount[2] + "\nTerritories Conquered: " + territoriesConqueredCount[2] + "\nTerritories Lost: "
                + territoriesLostCount[2] + "\n");
    }
}
