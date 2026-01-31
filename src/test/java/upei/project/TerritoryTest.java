package upei.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class TerritoryTest {

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        assertEquals("Terra", territory.getName(), "Territory constructor failed, name should be Terra");
        assertEquals("Sol", territory.getRegion(), "Territory constructor failed, region should be Sol");
        assertEquals(0, territory.getTerritoryID(), "Territory constructor failed, territoryID should be 0");
        assertEquals(100, territory.getWorth(), "Territory constructor failed, worth should be 100");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetName() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        assertEquals("Terra", territory.getName(), "Territory getName failed, name should be Terra");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testTerritoryID() {
        try{
            Territory territory = new Territory("Terra", "Sol", 42, 100);
        } catch (IllegalArgumentException e){
            assertEquals("Territory ID must be between 0 and 41",
                    e.getMessage(), "Territory ID outside expected bounds, should throw IllegalArgumentException");
        }
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testWorth() {
        try{
            Territory territory = new Territory("Terra", "Sol", 0, 300);
        } catch (IllegalArgumentException e){
            assertEquals("Territory worth must be 100 or 200",
                    e.getMessage(), "Territory worth outside expected bounds, should throw IllegalArgumentException");
        }
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetRegion() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        assertEquals("Sol", territory.getRegion(), "Territory getRegion failed, region should be Sol");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryID() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        assertEquals(0, territory.getTerritoryID(), "Territory getTerritoryID failed, territoryID should be 0");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetWorth() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        assertEquals(100, territory.getWorth(), "Territory getWorth failed, worth should be 100");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testIsOccupied() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        assertFalse(territory.isOccupied(), "Territory isOccupied failed, should return false");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testIsOccupied2() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.setOccupied(true);
        assertTrue(territory.isOccupied(), "Territory isOccupied failed, should return true");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testSetOccupied() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.setOccupied(true);
        assertTrue(territory.isOccupied(), "Territory setOccupied failed, should return true");
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testSetOccupied2() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.setOccupied(false);
        assertFalse(territory.isOccupied(), "Territory setOccupied failed, should return false");
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetUnits() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        assertEquals(2, territory.getUnits().length, "Territory getUnits failed, should return array of length 2");
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetUnits2() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        assertEquals(null, territory.getUnits()[0], "Territory getUnits failed, should return null");
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetUnits3() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        assertEquals(null, territory.getUnits()[1], "Territory getUnits failed, should return null");
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetUnits4() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.getUnits()[0] = new Unit("Primaris Intercessor", 0, "Single", 1);
        assertEquals("Primaris Intercessor", territory.getUnits()[0].getName(), "Territory getUnits failed, should return Primaris Intercessor");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    public void testAddRemoveUnit() {
        // Create a new player
        Player player = new Player(0, "Aggressive", "Captain Titus", 0);

        // Create a new territory
        Territory territory = new Territory("Mortwald", "Region1", 0, 100);

        // Add territory to player's territories
        player.addTerritory(territory);

        // Set player as owner of the territory
        territory.setOwnerNumber(player.getPlayerNumber());

        // Add units to the territory
        territory.addUnit("Single", 5);
        territory.addUnit("Triple", 2);

        // Test that the units were added correctly
        assertEquals(5, territory.getUnits()[0].getUnitCount());
        assertEquals(2, territory.getUnits()[1].getUnitCount());

        // Remove units from the territory
        territory.removeUnit("Single", 2);
        territory.removeUnit("Triple", 1);

        // Test that the units were removed correctly
        assertEquals(3, territory.getUnits()[0].getUnitCount());
        assertEquals(1, territory.getUnits()[1].getUnitCount());
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testSetGetOwnerNumber() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.setOwnerNumber(1);

        assertEquals(1, territory.getOwnerNumber(), "Territory setOwnerNumber failed, owner number should be 1");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test void testSetGetOwnerNumber2() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.setOwnerNumber(2);

        assertEquals(2, territory.getOwnerNumber(), "Territory setOwnerNumber failed, owner number should be 2");
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test void testSetGetOwnerNumber3() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.setOwnerNumber(3);

        assertEquals(3, territory.getOwnerNumber(), "Territory setOwnerNumber failed, owner number should be 3");
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test void testSetGetOwnerNumber4() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.setOwnerNumber(4);

        assertEquals(4, territory.getOwnerNumber(), "Territory setOwnerNumber failed, owner number should be 4");
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test void testSetGetOwnerNumber5() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.setOwnerNumber(5);

        assertEquals(5, territory.getOwnerNumber(), "Territory setOwnerNumber failed, owner number should be 5");
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test void testSetGetOwnerNumber6() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.setOwnerNumber(6);

        assertEquals(6, territory.getOwnerNumber(), "Territory setOwnerNumber failed, owner number should be 6");
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test void testSetGetOwnerNumber7() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.setOwnerNumber(7);

        assertEquals(7, territory.getOwnerNumber(), "Territory setOwnerNumber failed, owner number should be 7");
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test void testSetGetOwnerNumber8() {
        Territory territory = new Territory("Terra", "Sol", 0, 100);
        territory.setOwnerNumber(8);

        assertEquals(8, territory.getOwnerNumber(), "Territory setOwnerNumber failed, owner number should be 8");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    public void testGenerateDefendingParty() {
        // Create a new player
        Player player = new Player(0, "Aggressive", "Captain Titus", 0);

        // Create a new territory
        Territory territory = new Territory("Mortwald", "Region1", 0, 100);

        // Add territory to player's territories
        player.addTerritory(territory);

        // Set player as owner of the territory
        territory.setOwnerNumber(player.getPlayerNumber());

        // Create a territory with 4 "Single" units and 2 "Triple" units
        territory.addUnit("Single", 4);
        territory.addUnit("Triple", 2);

        // Generate the defending party
        Unit defendingParty = territory.generateDefendingParty();

        // Check that the defending party is a copy of the "Single" unit
        assertEquals("Single", defendingParty.getUnitType());

        // Check that the defending party has the correct unit count
        assertEquals(2, defendingParty.getUnitCount());

        // Change the "Single" unit count in the territory to 1
        territory.removeUnit("Single", 3);

        // Generate the defending party again
        defendingParty = territory.generateDefendingParty();

        // Check that the defending party still has the correct unit count
        assertEquals(1, defendingParty.getUnitCount());

        // Remove all "Single" units
        territory.removeUnit("Single", 1);

        // Generate the defending party again
        defendingParty = territory.generateDefendingParty();

        // Check that the defending party is a copy of the "Triple" unit
        assertEquals("Triple", defendingParty.getUnitType());

        // Check that the defending party has the correct unit count
        assertEquals(1, defendingParty.getUnitCount());
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    public void testSetUnits() {
        // Create a new player
        Player player = new Player(0, "Aggressive", "Captain Titus", 0);

        // Create a new territory
        Territory territory = new Territory("Mortwald", "Region1", 0, 100);

        // Add territory to player's territories
        player.addTerritory(territory);

        // Set player as owner of the territory
        territory.setOwnerNumber(player.getPlayerNumber());

        // Create a territory with 4 "Single" units and 2 "Triple" units
        territory.addUnit("Single", 4);
        territory.addUnit("Triple", 2);

        // Create a new array of units
        Unit[] units = new Unit[2]; {
            new Unit("Captain Titus",0,"Single", 3);
            new Unit("Captain Titus",0,"Triple", 2);
        };

        // Set the units for the territory
        territory.setUnits(units);

        // Get the units from the territory
        Unit[] returnedUnits = territory.getUnits();

        // Check that the units returned are the same as the ones we set
        assertArrayEquals(units, returnedUnits);
    }


    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    public void testGetArmyCount(){
        // Create a new player
        Player player = new Player(0, "Aggressive", "Captain Titus", 0);

        // Create a new territory
        Territory territory = new Territory("Mortwald", "Region1", 0, 100);

        // Add territory to player's territories
        player.addTerritory(territory);

        // Set player as owner of the territory
        territory.setOwnerNumber(player.getPlayerNumber());

        // Create a territory with 4 "Single" units and 2 "Triple" units
        territory.addUnit("Single", 4);
        territory.addUnit("Triple", 2);

        // Check that the army count is 10
        assertEquals(10, territory.getArmyCount());
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    public void testSetArmyCount(){
        // Create a new player
        Player player = new Player(0, "Aggressive", "Captain Titus", 0);

        // Create a new territory
        Territory territory = new Territory("Mortwald", "Region1", 0, 100);

        // Add territory to player's territories
        player.addTerritory(territory);

        // Set player as owner of the territory
        territory.setOwnerNumber(player.getPlayerNumber());

        // Create a territory with 4 "Single" units and 2 "Triple" units
        territory.addUnit("Single", 4);
        territory.addUnit("Triple", 2);

        // Set the army count to 5
        territory.setArmyCount(5);

        // Check that the army count is 5
        assertEquals(5, territory.getArmyCount());
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    public void testGenerateAttackingParty() {
        // Create a new player
        Player player = new Player(0, "Aggressive", "Captain Titus", 0);

        // Create a new territory
        Territory territory = new Territory("Mortwald", "Region1", 0, 100);

        // Add territory to player's territories
        player.addTerritory(territory);

        // Set player as owner of the territory
        territory.setOwnerNumber(player.getPlayerNumber());

        // Create a territory with 4 "Single" units and 2 "Triple" units
        territory.addUnit("Single", 4);
        territory.addUnit("Triple", 2);

        // Generate the attacking party
        Unit attackingParty = territory.generateAttackingParty();

        // Check that the attacking party is a copy of the "Single" unit
        assertEquals("Single", attackingParty.getUnitType());

        // Check that the attacking party has the correct unit count
        assertEquals(3, attackingParty.getUnitCount());

        // Now, simulate an attack by reducing the units in the territory
        territory.removeUnit("Single", 3);

        // Generate the attacking party again
        attackingParty = territory.generateAttackingParty();

        // Check that the attacking party still has the correct unit count
        assertEquals(1, attackingParty.getUnitCount());

        // Now, another attack, removing the last "Single" unit
        territory.removeUnit("Single", 1);

        // Generate the attacking party
        attackingParty = territory.generateAttackingParty();

        // Check that the attacking party is a copy of the "Triple" unit
        assertEquals("Triple", attackingParty.getUnitType());

        // Check that the attacking party has the correct unit count
        assertEquals(1, attackingParty.getUnitCount());
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    public void setOwnerNumber(){
        // Create a new player
        Player player = new Player(0, "Aggressive", "Captain Titus", 0);

        // Create a new territory
        Territory territory = new Territory("Mortwald", "Region1", 0, 100);

        // Add territory to player's territories
        player.addTerritory(territory);

        // Set player as owner of the territory
        territory.setOwnerNumber(player.getPlayerNumber());

        // Check that the owner number is 0
        assertEquals(0, territory.getOwnerNumber());
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    public void testGenerateDefendingParty2() {
        // Create a new player
        Player player = new Player(0, "Aggressive", "Captain Titus", 0);

        // Create a new territory
        Territory territory = new Territory("Mortwald", "Region1", 0, 100);

        // Add territory to player's territories
        player.addTerritory(territory);

        // Set player as owner of the territory
        territory.setOwnerNumber(player.getPlayerNumber());

        // Scenario: Exactly 2 "Single" units

        territory.addUnit("Single", 2);

        // Generate the defending party
        Unit defendingParty = territory.generateDefendingParty();

        // Check that the defending party is a copy of the "Single" unit
        assertEquals("Single", defendingParty.getUnitType());

        // Check that the defending party has the correct unit count
        assertEquals(2, defendingParty.getUnitCount());


    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    public void testGenerateDefendingParty3(){
        // Create a new player
        Player player = new Player(0, "Aggressive", "Captain Titus", 0);

        // Create a new territory
        Territory territory = new Territory("Mortwald", "Region1", 0, 100);

        // Add territory to player's territories
        player.addTerritory(territory);

        // Set player as owner of the territory
        territory.setOwnerNumber(player.getPlayerNumber());

        // Scenario: Exactly 1 "Triple" unit

        territory.addUnit("Triple", 1);

        // Generate the defending party
        Unit defendingParty = territory.generateDefendingParty();

        // Check that the defending party is a copy of the "Triple" unit
        assertEquals("Triple", defendingParty.getUnitType());

        // Check that the defending party has the correct unit count
        assertEquals(1, defendingParty.getUnitCount());
    }
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    public void testGenerateDefendingParty4(){
        // Create a new player
        Player player = new Player(0, "Aggressive", "Captain Titus", 0);

        // Create a new territory
        Territory territory = new Territory("Mortwald", "Region1", 0, 100);

        // Add territory to player's territories
        player.addTerritory(territory);

        // Set player as owner of the territory
        territory.setOwnerNumber(player.getPlayerNumber());
        // Scenario: No units available
        Unit defendingParty = territory.generateDefendingParty();

        // Check that the defending party is null
        assertNull(defendingParty, "Defending party should be null when no units are available");

        // Scenario: Exactly 2 "Triple" units, no "Single" units

        territory.addUnit("Triple", 2);

        // Generate the defending party
        defendingParty = territory.generateDefendingParty();

        // Only proceed if the defendingParty isn't null
        if (defendingParty != null) {
            // Check that the defending party is a copy of the "Triple" unit
            assertEquals("Triple", defendingParty.getUnitType(), "Defending party should be a Triple unit when only Triple units are available");

            // Check that the defending party has the correct unit count
            assertEquals(1, defendingParty.getUnitCount(), "Defending party should have 1 unit when there are exactly 2 Triple units");
        } else {
            fail("Defending party should not be null when there are units available");
        }
    }

}