package upei.project;

/**
 Subclass of Leader
 Single or Triple unit (worth one army or three armies respectively)

 *   Ultramarines (0)
 Single unit - Primaris Intercessor
 Triple unit - Primaris Repulsor

 *   Chaos Space Marines (1)
 Single unit - Chaos Space Marine
 Triple unit - Heldrake

 *   Aeldari Craftworlds (2)
 Single unit - Eldar Guardians
 Triple unit - Eldar Falcon

 *   Orks (3)
 Single unit - Ork Boyz
 Triple unit - Battlewagon

 *   Genestealer Cults (4)
 Single unit - Acolytes
 Triple unit - Goliath
 */

public class Unit extends Leader{
    private final String unitType;
    private int unitCount;

    /**
     * Constructor for Unit
     * @param name Name of the unit
     * @param factionID ID of the faction the unit belongs to
     * @param unitType Type of unit (Single or Triple)
     * @param unitCount Number of units
     */
    public Unit(String name, int factionID, String unitType, int unitCount){
        super(name, factionID);
        this.unitType = unitType;
        this.unitCount = Math.max(unitCount, 0);
    }

    /**
     * Getter for unitType
     * @return unitType
     */
    public String getUnitType() {
        return unitType;
    }
    /**
     * Getter for unitCount
     * @return unitCount
     */
    public int getUnitCount() {
        return unitCount;
    }
    /**
     * Setter for unitCount
     * @param unitCount Number of units
     */
    public void setUnitCount(int unitCount) {
        this.unitCount = unitCount;
        if (this.unitCount < 0) {
            this.unitCount = 0;
        }
    }
    /**
     * Returns an identical copy of the unit with a specified unit count
     * @param count Number of units
     * @return Unit
     */
    public Unit copyUnit(int count){
        return new Unit(this.getName(), this.getFactionID(), this.unitType, count);
    }
}
