package upei.project;

/**
 Subclass of Card
 5 Leaders/Factions

 *   Ultramarines (0)
 Marneus Calgar - Add +1 to highest attack and defence die

 *   Chaos Space Marines (1)
 Abaddon the Despoiler - Reroll all 1s

 *   Aeldari Craftworlds (2)
 Autarch - Add +1 to all attack dice

 *   Orks (3)
 Warboss - The final army in the leaders territory needs to be defeated twice before it and the leader are removed

 *   Genestealer Cults (4)
 Magus - Add +1 to all defence dice
 */

public class Leader extends Card{
    private final int factionID;

    /**
     * Constructor for Leader
     * @param name Name of the leader
     * @param factionID ID of the faction the leader belongs to
     */
    public Leader(String name, int factionID){
        super(name);
        if (factionID < 0 || factionID > 4) {
            throw new IllegalArgumentException("Faction ID must be between 0 and 4");
        }
        this.factionID = factionID;
    }

    /**
     * Getter for factionID
     * @return factionID
     */
    public int getFactionID() {
        return factionID;
    }

}
