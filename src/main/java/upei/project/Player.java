package upei.project;

import java.util.ArrayList;
import java.util.HashMap;

/**
 Basic Player class that handles data involving the player.
 * 3 Players (0-2)
 - Aggressive (0)
 - Pacifist (1)
 - Opportunist (2)

 * 5 Factions (0-4)
 - Ultramarines (0)
 - Chaos Space Marines (1)
 - Aeldari Craftworlds (2)
 - Orks (3)
 - Genestealer Cults (4)
 */

public class Player {
    private final int playerNumber;
    private final String playerStrategy;
    private int playerThroneGelt;
    private Leader playerLeader;
    private Unit[] playerUnits;
    private int deployedSingleCount;
    private int deployedTripleCount;
    private ArrayList<Territory> playerTerritories;
    private HashMap<String, Integer> regionControl = new HashMap<>(){{
        put("Mortwald", 0);
        put("Oteck Hivesprawl", 0);
        put("Dontoria", 0);
        put("Megaborealis", 0);
        put("Kaelac's Bane", 0);
        put("Hyperia", 0);
        put("Storvhal", 0);
    }};

    /**
     * Constructor for Player
     * @param playerNumber Number of the player
     * @param playerStrategy Strategy of the player
     * @param leaderName Name of the player's leader
     * @param factionID ID of the player's faction
     */
    public Player(int playerNumber, String playerStrategy, String leaderName, int factionID) {
        this.playerNumber = playerNumber;
        this.playerStrategy = playerStrategy;
        this.playerThroneGelt = 0;
        this.playerLeader = new Leader(leaderName, factionID);
        this.playerUnits = new Unit[2];
        this.deployedSingleCount = 0;
        this.deployedTripleCount = 0;
        this.playerTerritories = new ArrayList<>();
        switch (factionID) {
            case 0 -> {
                playerUnits[0] = new Unit("Primaris Intercessor", 0, "Single", 0);
                playerUnits[1] = new Unit("Primaris Repulsor", 0, "Triple", 0);
            }
            case 1 -> {
                playerUnits[0] = new Unit("Chaos Space Marine", 1, "Single", 0);
                playerUnits[1] = new Unit("Heldrake", 1, "Triple", 0);
            }
            case 2 -> {
                playerUnits[0] = new Unit("Eldar Guardians", 2, "Single", 0);
                playerUnits[1] = new Unit("Eldar Falcon", 2, "Triple", 0);
            }
            case 3 -> {
                playerUnits[0] = new Unit("Ork Boyz", 3, "Single", 0);
                playerUnits[1] = new Unit("Battlewagon", 3, "Triple", 0);
            }
            case 4 -> {
                playerUnits[0] = new Unit("Acolytes", 4, "Single", 0);
                playerUnits[1] = new Unit("Goliath", 4, "Triple", 0);
            }
        }
    }

    /**
     * Getter for playerNumber
     * @return int - playerNumber; Number of the player
     */
    public int getPlayerNumber() {
        return playerNumber;
    }
    /**
     * Getter for playerStrategy
     * @return String - playerStrategy; Strategy of the player
     */
    public String getPlayerStrategy() {
        return playerStrategy;
    }
    /**
     * Getter for playerThroneGelt
     * @return int - playerThroneGelt; Amount of Throne Gelt the player has
     */
    public int getPlayerThroneGelt() {
        return playerThroneGelt;
    }
    /**
     * Getter for playerLeader
     * @return Leader - playerLeader; Leader of the players respective faction
     */
    public Leader getPlayerLeader() {
        return playerLeader;
    }
    /**
     * Getter for controlledRegions
     * If player controls all territories in a region, the region is added to a list of controlled regions
     * @see HashMap - regionControl; Keeps track of how many territories the player controls in each region
     * @see ArrayList - controlledRegions; List of regions the player controls
     * @return ArrayList<String> - controlledRegions; ArrayList of regions the player controls
     */
    public ArrayList<String> getControlledRegions(){
        ArrayList<String> controlledRegions = new ArrayList<>();
        for (String region : regionControl.keySet()){
            switch (region) {
                case "Mortwald": {if (regionControl.get("Mortwald") == 4) {controlledRegions.add("Mortwald");}}
                case "Dontoria": {if (regionControl.get("Dontoria") == 7) {controlledRegions.add("Dontoria");}}
                case "Megaborealis": {if (regionControl.get("Megaborealis") == 5) {controlledRegions.add("Megaborealis");}}
                case "Storvhal": {if (regionControl.get("Storvhal") == 4) {controlledRegions.add("Storvhal");}}
                case "Oteck Hivesprawl": {if (regionControl.get("Oteck Hivesprawl") == 7) {controlledRegions.add("Oteck Hivesprawl");}}
                case "Kaelac's Bane": {if (regionControl.get("Kaelac's Bane") == 6) {controlledRegions.add("Kaelac's Bane");}}
                case "Hyperia": {if (regionControl.get("Hyperia") == 6) {controlledRegions.add("Hyperia");}}
            }
        }
        return controlledRegions;
    }

    /**
     * Checks if the player controls a territory
     * @param territory - Territory to check
     * @return boolean - True if the player controls the territory, false if not
     */
    public boolean checkForTerritory(Territory territory){
        return playerTerritories.contains(territory);
    }

    /**
     * Adds a territory to the player's list of territories
     * @param territory - Territory to add
     * Sets the owner number of the territory to the player's number
     * Sets the territory to occupied
     * Adds a copy of the players faction units to the territory
     * Adds the territory to an ArrayList of player territories
     * Adds the territory's worth to the player's throne gelt
     * Adds to the player's region control of the given territory's region
     */
    public void addTerritory(Territory ... territory){
        for(Territory t : territory){
            t.setOwnerNumber(playerNumber);
            t.setOccupied(true);
            Unit[] units = new Unit[2];
            for (int i = 0; i < 2; i++){
                units[i] = playerUnits[i].copyUnit(0);
            }
            t.setUnits(units);
            playerTerritories.add(t);
            playerThroneGelt+= t.getWorth();
            switch(t.getRegion()){
                case "Mortwald" -> regionControl.put("Mortwald", regionControl.get("Mortwald") + 1);
                case "Oteck Hivesprawl" -> regionControl.put("Oteck Hivesprawl", regionControl.get("Oteck Hivesprawl") + 1);
                case "Dontoria" -> regionControl.put("Dontoria", regionControl.get("Dontoria") + 1);
                case "Megaborealis" -> regionControl.put("Megaborealis", regionControl.get("Megaborealis") + 1);
                case "Kaelac's Bane" -> regionControl.put("Kaelac's Bane", regionControl.get("Kaelac's Bane") + 1);
                case "Hyperia" -> regionControl.put("Hyperia", regionControl.get("Hyperia") + 1);
                case "Storvhal" -> regionControl.put("Storvhal", regionControl.get("Storvhal") + 1);
            }
        }
    }
    /**
     * Removes a territory from the player's list of territories
     * @param territory - Territory to remove
     * Sets the owner number of the territory to -1
     * Sets the territory to unoccupied
     * Removes the territory from the player's list of territories
     * Removes the territory's worth from the player's throne gelt
     * Removes from the player's region control of the given territory's region
     */
    public void removeTerritory(Territory ... territory){
        for(Territory t : territory) {
            playerThroneGelt -= t.getWorth();
            playerTerritories.remove(t);
            t.setUnits(null);
            t.setOccupied(false);
            t.setOwnerNumber(-1);
            switch (t.getRegion()) {
                case "Mortwald" -> regionControl.put("Mortwald", regionControl.get("Mortwald") - 1);
                case "Oteck Hivesprawl" -> regionControl.put("Oteck Hivesprawl", regionControl.get("Oteck Hivesprawl") - 1);
                case "Dontoria" -> regionControl.put("Dontoria", regionControl.get("Dontoria") - 1);
                case "Megaborealis" -> regionControl.put("Megaborealis", regionControl.get("Megaborealis") - 1);
                case "Kaelac's Bane" -> regionControl.put("Kaelac's Bane", regionControl.get("Kaelac's Bane") - 1);
                case "Hyperia" -> regionControl.put("Hyperia", regionControl.get("Hyperia") - 1);
                case "Storvhal" -> regionControl.put("Storvhal", regionControl.get("Storvhal") - 1);
            }
        }
    }
    /**
     * Getter for playerTerritories
     * @return Territory[] - playerTerritories; List of territories the player controls
     */
    public Territory[] getTerritories(){
        Territory[] territories = new Territory[playerTerritories.size()];
        for(int i = 0; i < playerTerritories.size(); i++){
            territories[i] = playerTerritories.get(i);
        }
        return territories;
    }
    /**
     * Lists the territories under the players control
     * @return String - List of territories the player controls
     */
    public String listTerritories(){
        StringBuilder builder = new StringBuilder();
        builder.append("Player ");
        builder.append(playerNumber);
        builder.append(" Territories;\n");
        for(Territory territory : playerTerritories){
            builder.append("Name: ");
            builder.append(territory.getName());
            builder.append(" | Region: ");
            builder.append(territory.getRegion());
            builder.append(" | ID: ");
            builder.append(territory.getTerritoryID());
            builder.append(" | Worth: ");
            builder.append(territory.getWorth());
            builder.append("\n");
        }
        return builder.toString();
    }
    /**
     * Getter for playerTerritoryCount
     * @return int - playerTerritoryCount; Number of territories the player controls
     */
    public int getTerritoryCount(){
        return playerTerritories.size();
    }
    /**
     * Adds a specified type and amount of units to the player's list of units
     * @param unitType Type of unit (Single or Triple)
     * @param amount Number of units to add
     */
    public void addUnit(String unitType, int amount){
        for (Unit unit : playerUnits) {
            if (unit.getUnitType().equals(unitType)) {
                unit.setUnitCount(unit.getUnitCount() + amount);
            }
        }
    }
    /**
     * Removes a specified type and amount of units from the player's list of units
     * @param unitType Type of unit (Single or Triple)
     * @param amount Number of units to remove
     * Subtracts the amount of units removed from the deployed unit count
     */
    public void removeUnit(String unitType, int amount){
        for (Unit unit : playerUnits) {
            if (unit.getUnitType().equals(unitType)) {
                unit.setUnitCount(unit.getUnitCount() - amount);
                if (unit.getUnitType().equals("Single")){
                    deployedSingleCount -= amount;
                }
                else{
                    deployedTripleCount -= amount;
                }
            }
        }
    }
    /**
     * Getter for playerUnits
     * @return Unit[] - playerUnits; List of units the player has
     */
    public Unit[] getUnits(){
        return playerUnits;
    }
    /**
     * Deploys any un-deployed units the players territories
     * Adds a single unit to each territory until all single units are deployed
     * Adds a triple unit to each territory until all triple units are deployed (Not implemented)
     */
    public void deployUnits(){
        deployLoop:
        while (deployedSingleCount < playerUnits[0].getUnitCount() || deployedTripleCount < playerUnits[1].getUnitCount()){
            if (playerTerritories.size() == 0){
                break;
            }
            for (Territory territory : playerTerritories){
                if (deployedSingleCount < playerUnits[0].getUnitCount()) {
                    territory.addUnit("Single", 1);
                    deployedSingleCount++;
                }
                else{
                    break deployLoop;
                }
                /*else if (deployedTripleCount < playerUnits[1].getUnitCount()){
                    territory.addUnit("Triple", 1);
                    deployedTripleCount++;
                }*/
            }
        }
    }
    /**
     * Getter for players total army count
     * @return int - armyCount; Number of armies the player has (multiplied by 3 if the unit is a triple unit)
     */
    public int getArmyCount(){
        int armyCount = 0;
        for (Unit unit : playerUnits){
            if(unit.getUnitType().equals("Single")){
                armyCount += unit.getUnitCount();
            } else{
                armyCount += unit.getUnitCount() * 3;
            }
        }
        return armyCount;
    }
}
