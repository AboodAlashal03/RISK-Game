package upei.project;

/**
 Subclass of Card
 42 Territories
 6 Regions
 100 or 200 Throne Gelt value (currency)
 */

public class Territory extends Card {
    private final String region;
    private final int territoryID;
    private final int worth;
    private boolean isOccupied;
    private Unit[] units;
    private int ownerNumber;

    /**
     * Constructor for Territory
     *
     * @param name        Name of the territory
     * @param region      Region the territory belongs to
     * @param territoryID ID of the territory
     * @param worth       Worth of the territory
     */
    public Territory(String name, String region, int territoryID, int worth) {
        super(name);
        if (territoryID < 0 || territoryID > 41) {
            throw new IllegalArgumentException("Territory ID must be between 0 and 41");
        }
        if (worth != 100 && worth != 200) {
            throw new IllegalArgumentException("Territory worth must be 100 or 200");
        }
        this.units = new Unit[2];
        this.territoryID = territoryID;
        this.region = region;
        this.worth = worth;
        this.isOccupied = false;
    }

    /**
     * Getter for region
     *
     * @return region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Getter for territoryID
     *
     * @return territoryID
     */
    public int getTerritoryID() {
        return territoryID;
    }

    /**
     * Getter for worth
     *
     * @return worth
     */
    public int getWorth() {
        return worth;
    }

    /**
     * Getter for isOccupied
     *
     * @return isOccupied
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Setter for isOccupied
     *
     * @param occupied Whether the territory is occupied or not
     */
    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    /**
     * Getter for units
     *
     * @return units
     */
    public Unit[] getUnits() {
        return units;
    }

    /**
     * Setter for units
     *
     * @param units Units occupying the territory
     */
    public void setUnits(Unit[] units) {
        this.units = units;
    }

    /**
     * Getter for armyCount
     *
     * @return armyCount (Number of units occupying the territory, multiplied by 3 if the unit is a triple unit)
     */
    public int getArmyCount() {
        int armyCount = 0;
        for (Unit unit : units) {
            if (unit.getUnitType().equals("Single")) {
                armyCount += unit.getUnitCount();
            } else {
                armyCount += unit.getUnitCount() * 3;
            }
        }
        return armyCount;
    }

    /**
     * Adds a unit to the territory
     *
     * @param unitType Type of unit (Single or Triple)
     * @param amount   Number of units to add
     */
    public void addUnit(String unitType, int amount) {
        for (Unit unit : units) {
            if (unit.getUnitType().equals(unitType)) {
                unit.setUnitCount(unit.getUnitCount() + amount);
            }
        }
    }

    /**
     * Removes a unit from the territory
     *
     * @param unitType Type of unit (Single or Triple)
     * @param amount   Number of units to remove
     */
    public void removeUnit(String unitType, int amount) {
        for (Unit unit : units) {
            if (unit.getUnitType().equals(unitType)) {
                unit.setUnitCount(unit.getUnitCount() - amount);
            }
        }
    }

    /**
     * Setter for armyCount
     *
     * @param unitCount Number of units occupying the territory
     */
    public void setArmyCount(int unitCount) {
        if (unitCount < 0) {
            throw new IllegalArgumentException("Unit count must be greater than 0");
        }

        // Calculate the number of triple and single units
        int tripleCount = unitCount / 3;
        int singleCount = unitCount % 3;

        // Assuming units[0] is single and units[1] is triple
        units[0].setUnitCount(singleCount);
        units[1].setUnitCount(tripleCount);
    }

    /**
     * Getter for ownerNumber
     *
     * @return ownerNumber
     */
    public int getOwnerNumber() {
        return ownerNumber;
    }

    /**
     * Setter for ownerNumber
     *
     * @param playerNumber Number of the player who owns the territory
     */
    public void setOwnerNumber(int playerNumber) {
        this.ownerNumber = playerNumber;
    }
    // need to add triple units

    /**
     * Generates a party of units to attack a territory
     *
     * @return Unit (Party of units)
     */
    public Unit generateAttackingParty() {
        int singleCount = units[0].getUnitCount();
        if (singleCount > 0) {
            if (singleCount > 3) {
                singleCount = 3;
            }
            return units[0].copyUnit(singleCount);
        } else if (units[1].getUnitCount() > 0) {
            return units[1].copyUnit(1);
        } else {
            // In case there are no units left, return an empty single unit
            return new Unit("Single", 0, "Single", 0);
        }
    }
    // need to add triple units

    /**
     * Generates a party of units to defend a territory
     *
     * @return Unit (Party of units)
     */
    public Unit generateDefendingParty() {
        int singleCount = units[0].getUnitCount();
        if (singleCount > 0) {
            if (singleCount > 2) {
                singleCount = 2;
            }
            return units[0].copyUnit(singleCount);
        } else if (units[1].getUnitCount() > 0) {
            return units[1].copyUnit(1);
        } else {
            // In case there are no units left, return null
            return null;
        }
    }
}
