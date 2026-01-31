package upei.project;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/*
42 Territories (0-41)

7 Regions (0-5)
Megaborealis (0)
Dontoria (1)
Hyperia Sector (2)
Kaelac's Bane (3)
Mortwald (4)
Oteck Hivesprawl (5)
Storvhal (6)

 This class keeps track of the state of the game which includes data on turns, wins, losses, attacks,
 defences, dice rolls, units recruited, units lost, territories conquered and territories lost for each player.
 This data is used to generate a .txt file (or CSV) with the results of the simulation.
*/

public class GameState {
    private int[] turnCount = new int[3];
    private int[] winCount = new int[3];
    private int[] lossCount = new int[3];
    private int[] attackCount = new int[3];
    private int[] successfulAttackCount = new int[3];
    private int[] failedAttackCount = new int[3];
    private int[] defenceCount = new int[3];
    private int[] successfulDefenceCount = new int[3];
    private int[] failedDefenceCount = new int[3];
    private int[] diceRollCount = new int[3];
    private int[] totalUnitsRecruitedCount = new int[3];
    private int[] totalUnitsLostCount = new int[3];
    private int[] territoriesConqueredCount = new int[3];
    private int[] territoriesLostCount = new int[3];

    public GameState() throws IOException {
        // Read the results from the previous simulation
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
                this.turnCount[playerNumber] = Integer.parseInt(values[1]);
                this.winCount[playerNumber] = Integer.parseInt(values[2]);
                this.lossCount[playerNumber] = Integer.parseInt(values[3]);
                this.attackCount[playerNumber] = Integer.parseInt(values[4]);
                this.successfulAttackCount[playerNumber] = Integer.parseInt(values[5]);
                this.failedAttackCount[playerNumber] = Integer.parseInt(values[6]);
                this.defenceCount[playerNumber] = Integer.parseInt(values[7]);
                this.successfulDefenceCount[playerNumber] = Integer.parseInt(values[8]);
                this.failedDefenceCount[playerNumber] = Integer.parseInt(values[9]);
                this.diceRollCount[playerNumber] = Integer.parseInt(values[10]);
                this.totalUnitsRecruitedCount[playerNumber] = Integer.parseInt(values[11]);
                this.totalUnitsLostCount[playerNumber] = Integer.parseInt(values[12]);
                this.territoriesConqueredCount[playerNumber] = Integer.parseInt(values[13]);
                this.territoriesLostCount[playerNumber] = Integer.parseInt(values[14]);
                line = br.readLine();

            }
        }
    }

    // Increment the number of turns for a specific player.
    public void incrementTurnCount(int playerNumber) {
        this.turnCount[playerNumber]++;
    }

    // Increment the win count for a specific player.
    public void incrementWinCount(int playerNumber) {
        this.winCount[playerNumber]++;
    }

    // Increment the loss count for a specific player.
    public void incrementLossCount(int playerNumber) {
        this.lossCount[playerNumber]++;
    }

    // Increment the number of attacks initiated by a specific player.
    public void incrementAttackCount(int playerNumber) {
        this.attackCount[playerNumber]++;
    }

    // Increment the number of successful attacks by a specific player.
    public void incrementSuccessfulAttackCount(int playerNumber) {
        this.successfulAttackCount[playerNumber]++;
    }

    // Increment the number of failed attacks by a specific player.
    public void incrementFailedAttackCount(int playerNumber) {
        this.failedAttackCount[playerNumber]++;
    }

    // Increment the number of defences performed by a specific player.
    public void incrementDefenceCount(int playerNumber) {
        this.defenceCount[playerNumber]++;
    }

    // Increment the number of successful defences by a specific player.
    public void incrementSuccessfulDefenceCount(int playerNumber) {
        this.successfulDefenceCount[playerNumber]++;
    }

    // Increment the number of failed defences by a specific player.
    public void incrementFailedDefenceCount(int playerNumber) {
        this.failedDefenceCount[playerNumber]++;
    }

    // Add to the total dice rolls count for a specific player.
    public void addDiceRollCount(int playerNumber, int diceRollCount) {
        this.diceRollCount[playerNumber] += diceRollCount;
    }

    // Add to the total units recruited count for a specific player.
    public void addTotalUnitsRecruitedCount(int playerNumber, int unitsRecruited) {
        this.totalUnitsRecruitedCount[playerNumber] += unitsRecruited;
    }

    // Increment the total units lost count for a specific player.
    public void incrementTotalUnitsLostCount(int playerNumber, int unitsLost) {
        this.totalUnitsLostCount[playerNumber] += unitsLost;
    }

    // Increment the count of territories conquered by a specific player.
    public void incrementTerritoriesConqueredCount(int playerNumber) {
        this.territoriesConqueredCount[playerNumber]++;
    }

    // Increment the count of territories lost by a specific player.
    public void incrementTerritoriesLostCount(int playerNumber) {
        this.territoriesLostCount[playerNumber]++;
    }


    // Write the results to the log file
    public void logResults() {
        try {
            FileWriter writer = new FileWriter("SimulationResults.txt");
            writer.write("Player,Turns,Wins,Losses,Attacks,Successful Attacks,Failed Attacks,Defences,Successful Defences," +
                    "Failed Defences,Dice Roll Count,Total Units Recruited,Total Units Lost,Territories Conquered,Territories Lost\n");
            writer.write("0" + "," + turnCount[0] + "," + winCount[0] + "," + lossCount[0] + "," + attackCount[0] + "," +
                    successfulAttackCount[0] + "," + failedAttackCount[0] + "," + defenceCount[0] + "," + successfulDefenceCount[0] + "," +
                    failedDefenceCount[0] + "," + diceRollCount[0] + "," + totalUnitsRecruitedCount[0] + "," + totalUnitsLostCount[0] + "," +
                    territoriesConqueredCount[0] + "," + territoriesLostCount[0] + "\n");
            writer.write("1" + "," + turnCount[1] + "," + winCount[1] + "," + lossCount[1] + "," + attackCount[1] + "," +
                    successfulAttackCount[1] + "," + failedAttackCount[1] + "," + defenceCount[1] + "," + successfulDefenceCount[1] + "," +
                    failedDefenceCount[1] + ","  + diceRollCount[1] + "," + totalUnitsRecruitedCount[1] + "," + totalUnitsLostCount[1] + "," +
                    territoriesConqueredCount[1] + "," + territoriesLostCount[1] + "\n");
            writer.write("2" + "," + turnCount[2] + "," + winCount[2] + "," + lossCount[2] + "," + attackCount[2] + "," +
                    successfulAttackCount[2] + "," + failedAttackCount[2] + "," + defenceCount[2] + "," + successfulDefenceCount[2] + "," +
                    failedDefenceCount[2] + "," + diceRollCount[2] + "," + totalUnitsRecruitedCount[2] + "," + totalUnitsLostCount[2] + "," +
                    territoriesConqueredCount[2] + "," + territoriesLostCount[2] + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }

    // Reset any leftover results from previous simulations to 0
    public static void resetResults() {
        try {
            FileWriter writer = new FileWriter("SimulationResults.txt");
            writer.write("Player,Turns,Wins,Losses,Attacks,Successful Attacks,Failed Attacks,Defences,Successful Defences," +
                    "Failed Defences,Dice Rolled,Total Units Recruited,Total Units Lost,Territories Conquered,Territories Lost\n");
            writer.write("0" + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + ","
                    + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0);
            writer.write("1" + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + ","
                    + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0);
            writer.write("2" + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + ","
                    + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}