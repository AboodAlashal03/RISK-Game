package upei.project;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * GameBoard class that is responsible for tracking all data typically associated with a game board
 */
public class GameBoard{

    private ArrayList<Territory> territories;
    private ArrayList<Player> players;

    private HashMap<Territory, Territory[]> borders;

    public GameBoard(Player player1, Player player2, Player player3){
        this.territories = new ArrayList<>();
        this.players = new ArrayList<>();
        this.borders = new HashMap<>();
        initializeTerritories();
        initializePlayers(player1, player2, player3);
        initializeBorders();
    }

    // Initialize the 3 Player Objects
    private void initializePlayers(Player player1, Player player2, Player player3){
        players.add(0, player1);
        players.add(1, player2);
        players.add(2, player3);
    }

    // Initialize all territories on the Board
    private void initializeTerritories() {
        territories.add(0, new Territory("Rejuvenated Strongport", "Mortwald", 0, 100));
        territories.add(1, new Territory("Ageless Weald", "Mortwald", 1, 200));
        territories.add(2, new Territory("Succulent Plains", "Mortwald", 2, 100));
        territories.add(3, new Territory("Ork Scrap Cities", "Mortwald", 3, 100));
        territories.add(4, new Territory("Mesha's Delta", "Dontoria", 4, 100));
        territories.add(5, new Territory("Litmus Dock", "Dontoria", 5, 100));
        territories.add(6, new Territory("New Horizon", "Dontoria", 6, 200));
        territories.add(7, new Territory("Lake Dontor", "Dontoria", 7, 200));
        territories.add(8, new Territory("Guardia Periphery", "Dontoria", 8, 200));
        territories.add(9, new Territory("The Great Choke", "Dontoria", 9, 100));
        territories.add(10, new Territory("Gork's Landing", "Dontoria", 10, 100));
        territories.add(11, new Territory("Unburnt Sprawl", "Megaborealis", 11, 100));
        territories.add(12, new Territory("The Stygian Spires", "Megaborealis", 12, 100));
        territories.add(13, new Territory("Krogzak's Spindle", "Megaborealis", 13, 200));
        territories.add(14, new Territory("The Black Levels", "Megaborealis", 14, 200));
        territories.add(15, new Territory("Ork Deffcline", "Megaborealis", 15, 100));
        territories.add(16, new Territory("Da Wheel Hub", "Storvhal", 16, 100));
        territories.add(17, new Territory("Rakkuk's Mekmaze", "Storvhal", 17, 200));
        territories.add(18, new Territory("Storvhal", "Storvhal", 18, 100));
        territories.add(19, new Territory("Skumtown", "Storvhal", 19, 100));
        territories.add(20, new Territory("Lenkotz Chain", "Oteck Hivesprawl", 20, 100));
        territories.add(21, new Territory("Mogadon Plateau", "Oteck Hivesprawl", 21, 200));
        territories.add(22, new Territory("Turingbane Datahives", "Oteck Hivesprawl", 22, 100));
        territories.add(23, new Territory("The Giants", "Oteck Hivesprawl", 23, 200));
        territories.add(24, new Territory("Aquaphobian Wastes", "Oteck Hivesprawl", 24, 100));
        territories.add(25, new Territory("Deathwatch Corden Nexus", "Oteck Hivesprawl", 25, 100));
        territories.add(26, new Territory("Solar Sentinel Hive", "Oteck Hivesprawl", 26, 100));
        territories.add(27, new Territory("Deathland Permafrost", "Kaelac's Bane", 27, 100));
        territories.add(28, new Territory("Mekstop City", "Kaelac's Bane", 28, 100));
        territories.add(29, new Territory("Venstran Impact Crater", "Kaelac's Bane", 29, 200));
        territories.add(30, new Territory("Heliostrike Impact Crater", "Kaelac's Bane", 30, 200));
        territories.add(31, new Territory("Substein Caveways", "Kaelac's Bane", 31, 100));
        territories.add(32, new Territory("The Grand Pit", "Kaelac's Bane", 32, 100));
        territories.add(33, new Territory("The Dust Docks", "Hyperia", 33, 200));
        territories.add(34, new Territory("Cape Of Lost Causes", "Hyperia", 34, 100));
        territories.add(35, new Territory("Saint's Haven", "Hyperia", 35, 200));
        territories.add(36, new Territory("Magentine Veils", "Hyperia", 36, 100));
        territories.add(37, new Territory("Vhullian Swirl", "Hyperia", 37, 100));
        territories.add(38, new Territory("Hurrikane Rekk", "Hyperia", 38, 100));
        territories.add(39, new Territory("Mortwald-Oteck Fortwall", "Mortwald/Oteck", 39, 200));
        territories.add(40, new Territory("Dontoria-Megaborealis Fortwall", "Dontoria/Megaborealis", 40, 200));
        territories.add(41, new Territory("Kaelac's Bane-Hyperia Fortwall", "Kaelac's Bane/Hyperia", 41, 200));
    }

    // Initialize the borders of each territory
    private void initializeBorders() {
        borders.put(territories.get(0), new Territory[]{territories.get(1), territories.get(3), territories.get(20), territories.get(39)});
        borders.put(territories.get(1), new Territory[]{territories.get(0), territories.get(2), territories.get(3)});
        borders.put(territories.get(2), new Territory[]{territories.get(1), territories.get(3), territories.get(5)});
        borders.put(territories.get(3), new Territory[]{territories.get(0), territories.get(1), territories.get(2), territories.get(4), territories.get(24)});
        borders.put(territories.get(4), new Territory[]{territories.get(3), territories.get(5), territories.get(7), territories.get(8)});
        borders.put(territories.get(5), new Territory[]{territories.get(2), territories.get(4), territories.get(6), territories.get(7)});
        borders.put(territories.get(6), new Territory[]{territories.get(5), territories.get(7), territories.get(9)});
        borders.put(territories.get(7), new Territory[]{territories.get(4), territories.get(5), territories.get(6), territories.get(8), territories.get(9)});
        borders.put(territories.get(8), new Territory[]{territories.get(4), territories.get(7), territories.get(9), territories.get(10)});
        borders.put(territories.get(9), new Territory[]{territories.get(6), territories.get(7), territories.get(8), territories.get(9), territories.get(16), territories.get(40)});
        borders.put(territories.get(10), new Territory[]{territories.get(8), territories.get(9), territories.get(28)});
        borders.put(territories.get(11), new Territory[]{territories.get(12), territories.get(13), territories.get(40)});
        borders.put(territories.get(12), new Territory[]{territories.get(11), territories.get(13), territories.get(14), territories.get(32)});
        borders.put(territories.get(13), new Territory[]{territories.get(11), territories.get(12), territories.get(14)});
        borders.put(territories.get(14), new Territory[]{territories.get(12), territories.get(13), territories.get(15)});
        borders.put(territories.get(15), new Territory[]{territories.get(14), territories.get(37), territories.get(38)});
        borders.put(territories.get(16), new Territory[]{territories.get(9), territories.get(17), territories.get(18)});
        borders.put(territories.get(17), new Territory[]{territories.get(16), territories.get(18), territories.get(19)});
        borders.put(territories.get(18), new Territory[]{territories.get(16), territories.get(17), territories.get(19), territories.get(37)});
        borders.put(territories.get(19), new Territory[]{territories.get(17), territories.get(18), territories.get(28)});
        borders.put(territories.get(20), new Territory[]{territories.get(0), territories.get(21), territories.get(37)});
        borders.put(territories.get(21), new Territory[]{territories.get(20), territories.get(22), territories.get(25), territories.get(26)});
        borders.put(territories.get(22), new Territory[]{territories.get(21), territories.get(23), territories.get(25), territories.get(39)});
        borders.put(territories.get(23), new Territory[]{territories.get(22), territories.get(24), territories.get(25)});
        borders.put(territories.get(24), new Territory[]{territories.get(3), territories.get(23), territories.get(28)});
        borders.put(territories.get(25), new Territory[]{territories.get(21), territories.get(22), territories.get(23), territories.get(26), territories.get(27)});
        borders.put(territories.get(26), new Territory[]{territories.get(21), territories.get(25), territories.get(32)});
        borders.put(territories.get(27), new Territory[]{territories.get(25), territories.get(28), territories.get(29), territories.get(30)});
        borders.put(territories.get(28), new Territory[]{territories.get(10), territories.get(19), territories.get(24), territories.get(27), territories.get(30)});
        borders.put(territories.get(29), new Territory[]{territories.get(27), territories.get(30)});
        borders.put(territories.get(30), new Territory[]{territories.get(27), territories.get(28), territories.get(29), territories.get(31)});
        borders.put(territories.get(31), new Territory[]{territories.get(30), territories.get(32), territories.get(41)});
        borders.put(territories.get(32), new Territory[]{territories.get(12), territories.get(26), territories.get(31), territories.get(36)});
        borders.put(territories.get(33), new Territory[]{territories.get(34), territories.get(35)});
        borders.put(territories.get(34), new Territory[]{territories.get(33), territories.get(35), territories.get(41)});
        borders.put(territories.get(35), new Territory[]{territories.get(33), territories.get(34), territories.get(36)});
        borders.put(territories.get(36), new Territory[]{territories.get(32), territories.get(35), territories.get(37)});
        borders.put(territories.get(37), new Territory[]{territories.get(15), territories.get(18), territories.get(20), territories.get(36), territories.get(38)});
        borders.put(territories.get(38), new Territory[]{territories.get(15), territories.get(37)});
        borders.put(territories.get(39), new Territory[]{territories.get(0), territories.get(22)});
        borders.put(territories.get(40), new Territory[]{territories.get(9), territories.get(11)});
        borders.put(territories.get(41), new Territory[]{territories.get(31), territories.get(34)});
    }

    // Getter method to retrieve the list of players
    public ArrayList<Player> getPlayers() {
        return players;
    }
    // Getter method to retrieve a player by their number
    public Player getPlayerByNumber(int playerNumber){
        if (playerNumber < 0 || playerNumber > 2){
            throw new IllegalArgumentException("Player number must be between 0 and 2");
        }
        return players.get(playerNumber);
    }
    // Getter method to retrieve a territory by its ID
    public Territory getTerritoryByID(int territoryID){
        if (territoryID < 0 || territoryID > 41){
            throw new IllegalArgumentException("Territory ID must be between 0 and 41");
        }
        return territories.get(territoryID);
    }
    // Getter method to retrieve a territory by its name
    public Territory getTerritoryByName(String name){
        for (Territory territory : territories) {
            if (territory.getName().equals(name)) {
                return territory;
            }
        }
        return null;
    }
    // Getter method to retrieve territories in a specific region
    public Territory[] getTerritoryByRegion(String region){
        if (region == null){
            throw new NullPointerException("Region is null");
        }
        ArrayList<Territory> territoriesByRegion = new ArrayList<>();
        for (Territory territory : territories) {
            if (territory.getRegion().equals(region)) {
                territoriesByRegion.add(territory);
            }
        }
        return territoriesByRegion.toArray(new Territory[0]);
    }
    // Getter method to retrieve territories with a specific worth
    public Territory[] getTerritoryByWorth(int worth){
        if (worth < 0 || worth > 200){
            throw new IllegalArgumentException("Worth must be between 0 and 200");
        }
        ArrayList<Territory> territoriesByWorth = new ArrayList<>();
        for (Territory territory : territories) {
            if (territory.getWorth() == worth) {
                territoriesByWorth.add(territory);
            }
        }
        return territoriesByWorth.toArray(new Territory[0]);
    }
    // Compare a territory to another territory to see if they border each other
    // Returns true if they do, false if they don't
    public boolean checkBorder(Territory territory1, Territory territory2){
        int territory1ID = territory1.getTerritoryID();
        int territory2ID = territory2.getTerritoryID();
        Territory territory1Object = territories.get(territory1ID);
        Territory territory2Object = territories.get(territory2ID);
        Territory[] borderList = borders.get(territory1Object);

        if (borderList == null){
            throw new NullPointerException("Border list is null (don't compare the addresses bozo)");
        }
        for (Territory territory : borderList) {
            if (territory2Object.equals(territory)) {
                return true;
            }
        }
        return false;
    }
    // Getter method to retrieve the borders of a territory
    public Territory[] getBorders(Territory territory){
        return borders.get(territory);
    }

}
