package upei.project;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    //testConstructor: Tests the constructor of the Player class, checking if the player attributes are set correctly.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        assertEquals(0, player.getPlayerNumber(), "Player constructor failed, playerNumber should be 0");
        assertEquals("Aggressive", player.getPlayerStrategy(), "Player constructor failed, playerStrategy should be Aggressive");
        assertEquals(0, player.getPlayerThroneGelt(), "Player constructor failed, playerThroneGelt should be 0");
        assertEquals("Magus", player.getPlayerLeader().getName(), "Player constructor failed, playerLeader name should be Magus");
        assertEquals(4, player.getPlayerLeader().getFactionID(), "Player constructor failed, playerLeader factionID should be 4");
    }

    //testAddTerritory: Tests the addTerritory method, checking if a territory is correctly added to the player's list of territories.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testAddTerritory() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        Territory territory = new Territory("The Eyrie", "North", 0, 100);
        player.addTerritory(territory);
        assertTrue(player.checkForTerritory(territory), "addTerritory failed, should return true");
    }

    //testRemoveTerritory: Tests the removeTerritory method, checking if a territory is correctly removed from the player's list of territories.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testRemoveTerritory() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        Territory territory = new Territory("The Eyrie", "North", 0, 100);
        player.removeTerritory(territory);
        assertFalse(player.checkForTerritory(territory), "removeTerritory failed, should return false");
    }
    //testCheckForTerritory: Tests the checkForTerritory method, checking if it correctly identifies whether the player controls a specific territory.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testCheckForTerritory() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        Territory territory = new Territory("The Eyrie", "North", 0, 100);
        player.addTerritory(territory);
        assertTrue(player.checkForTerritory(territory), "checkForTerritory failed, should return true");
        player.removeTerritory(territory);
        assertFalse(player.checkForTerritory(territory), "checkForTerritory failed, should return false");
    }

    //testListTerritories: Tests the listTerritories method, checking if the output string matches the expected format.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testListTerritories() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        Territory territory = new Territory("The Eyrie", "North", 0, 100);
        Territory territory2 = new Territory("Dreg Heap", "South", 1, 200);
        player.addTerritory(territory);
        player.addTerritory(territory2);
        String out = player.listTerritories();
        assertEquals("""
                        Player 0 Territories;
                        Name: The Eyrie | Region: North | ID: 0 | Worth: 100
                        Name: Dreg Heap | Region: South | ID: 1 | Worth: 200
                        """, out,
                """
                        getTerritories failed, should return "Player 0 Territories;
                        Name: The Eyrie | Region: North | ID: 0 | Worth: 100"
                        Name: Dreg Heap | Region: South | ID: 1 | Worth: 200
                        """);
    }
    //testListTerritoriesEmpty: Tests the listTerritories method when the player has no territories.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testListTerritoriesEmpty() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        String out = player.listTerritories();
        assertEquals("Player 0 Territories;\n", out,
                "getTerritories failed, should return \"Player 0 Territories;\n\"");
    }

    //testGetTerritoryCount: Tests the getTerritoryCount method, checking if it correctly returns the number of territories the player controls.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryCount() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        Territory territory = new Territory("The Eyrie", "North", 0, 100);
        Territory territory2 = new Territory("Dreg Heap", "South", 1, 200);
        player.addTerritory(territory);
        player.addTerritory(territory2);
        assertEquals(2, player.getTerritoryCount(), "getTerritoryCount failed, should return 2");
    }

    //testGetTerritoryCountEmpty: Tests the getTerritoryCount method when the player has no territories.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryCountEmpty() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        assertEquals(0, player.getTerritoryCount(), "getTerritoryCount failed, should return 0");
    }

    //testGetTerritories: Tests the getTerritories method, checking if it returns an array of territories owned by the player.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritories() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        Territory territory = new Territory("The Eyrie", "North", 0, 100);
        Territory territory2 = new Territory("Dreg Heap", "South", 1, 200);
        player.addTerritory(territory);
        player.addTerritory(territory2);
        Territory[] territories = player.getTerritories();
        assertEquals(territory.getName(), territories[0].getName(), "getTerritories failed, should return The Eyrie");
        assertEquals(territory2.getName(), territories[1].getName(), "getTerritories failed, should return Dreg Heap");
    }

    //testGetTerritoriesEmpty: Tests the getTerritories method when the player has no territories.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoriesEmpty() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        Territory[] territories = player.getTerritories();
        assertEquals(0, territories.length, "getTerritories failed, should return 0");
    }

    //testGetUnits: Tests the getUnits method, checking if it returns an array of units owned by the player.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetUnits() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        player.addUnit("Single", 15);
        player.addUnit("Triple", 5);
        assertEquals(player.getUnits()[0].getUnitCount(), 15, "getUnits failed, should return 15");
        assertEquals(player.getUnits()[1].getUnitCount(), 5, "getUnits failed, should return 5");
    }

    //testAddUnit: Tests the addUnit method, checking if it correctly adds units to the player's unit count.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testAddUnit() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        player.addUnit("Single", 15);
        player.addUnit("Triple", 5);
        assertEquals(player.getUnits()[0].getUnitCount(), 15, "addUnit failed, should return 15");
        assertEquals(player.getUnits()[1].getUnitCount(), 5, "addUnit failed, should return 5");
    }
    //testRemoveUnit: Tests the removeUnit method, checking if it correctly removes units from the player's unit count.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testRemoveUnit() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        player.addUnit("Single", 15);
        player.addUnit("Triple", 5);
        player.removeUnit("Single", 5);
        player.removeUnit("Triple", 2);
        assertEquals(player.getUnits()[0].getUnitCount(), 10, "removeUnit failed, should return 10");
        assertEquals(player.getUnits()[1].getUnitCount(), 3, "removeUnit failed, should return 3");
    }

    //testRemoveUnit2: Tests the removeUnit method when trying to remove more units than available, ensuring the count doesn't go below zero.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testRemoveUnit2() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        player.addUnit("Single", 15);
        player.addUnit("Triple", 5);
        player.removeUnit("Single", 16);
        player.removeUnit("Triple", 6);
        assertEquals(player.getUnits()[0].getUnitCount(), 0, "removeUnit failed, should return 0");
        assertEquals(player.getUnits()[1].getUnitCount(), 0, "removeUnit failed, should return 0");
    }

    //testGetControlledRegions: Tests the getControlledRegions method, checking if it correctly identifies the regions controlled by the player.
    @Timeout(value=100, unit=TimeUnit.MILLISECONDS)
    @Test
    void testGetControlledRegions() {
        // Create a player
        Player player = new Player(1, "Aggressive", "Leader Name", 0);

        // Add sufficient territories in Mortwald to the player's control to test controlling a region
        for (int i = 0; i < 4; i++) {
            Territory territory = new Territory("Territory " + (i+1), "Mortwald", i, 200);
            territory.setOwnerNumber(player.getPlayerNumber());
            player.addTerritory(territory);
        }

        // Get the player's controlled regions
        ArrayList<String> controlledRegions = player.getControlledRegions();

        // Assert that Mortwald is a controlled region
        assertTrue(controlledRegions.contains("Mortwald"));
    }

    //testAddTerritory2: Tests the addTerritory method when adding multiple territories at once.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testAddTerritory2() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        Territory territory1 = new Territory("The Eyrie", "North", 0, 100);
        Territory territory2 = new Territory("Dreg Heap", "South", 1, 200);

        player.addTerritory(territory1, territory2);

        assertTrue(player.checkForTerritory(territory1), "addTerritory failed, should return true");
        assertTrue(player.checkForTerritory(territory2), "addTerritory failed, should return true");
        assertEquals(2, player.getTerritoryCount(), "addTerritory failed, should return 2");
    }


    //testRemoveTerritory2: Tests the removeTerritory method when removing a territory from a list of multiple territories.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testRemoveTerritory2() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        Territory territory1 = new Territory("The Eyrie", "North", 0, 100);
        Territory territory2 = new Territory("Dreg Heap", "South", 1, 200);
        player.addTerritory(territory1, territory2);

        player.removeTerritory(territory1);

        assertFalse(player.checkForTerritory(territory1), "removeTerritory failed, should return false");
        assertTrue(player.checkForTerritory(territory2), "removeTerritory failed, should return true");
        assertEquals(1, player.getTerritoryCount(), "removeTerritory failed, should return 1");
    }

    //testListTerritories2: Tests the listTerritories method when the player has multiple territories.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testListTerritories2() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        Territory territory1 = new Territory("The Eyrie", "North", 0, 100);
        Territory territory2 = new Territory("Dreg Heap", "South", 1, 200);
        player.addTerritory(territory1, territory2);

        String out = player.listTerritories();

        assertEquals("""
                    Player 0 Territories;
                    Name: The Eyrie | Region: North | ID: 0 | Worth: 100
                    Name: Dreg Heap | Region: South | ID: 1 | Worth: 200
                    """, out,
                """
                        listTerritories failed, should return "Player 0 Territories;
                        Name: The Eyrie | Region: North | ID: 0 | Worth: 100
                        Name: Dreg Heap | Region: South | ID: 1 | Worth: 200"
                        """);
    }

    //testGetTerritoryCount2: Tests the getTerritoryCount method when the player has multiple territories.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryCount2() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        Territory territory1 = new Territory("The Eyrie", "North", 0, 100);
        Territory territory2 = new Territory("Dreg Heap", "South", 1, 200);
        player.addTerritory(territory1, territory2);

        assertEquals(2, player.getTerritoryCount(), "getTerritoryCount failed, should return 2");
    }


    //testGetUnits2: Tests the getUnits method when the player has multiple units.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetUnits2() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        player.addUnit("Single", 15);
        player.addUnit("Triple", 5);

        assertEquals(player.getUnits()[0].getUnitCount(), 15, "getUnits failed, should return 15");
        assertEquals(player.getUnits()[1].getUnitCount(), 5, "getUnits failed, should return 5");
    }

    //testAddUnit2: Tests the addUnit method when adding units to the player with multiple units.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testAddUnit2() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        player.addUnit("Single", 15);
        player.addUnit("Triple", 5);

        assertEquals(player.getUnits()[0].getUnitCount(), 15, "addUnit failed, should return 15");
        assertEquals(player.getUnits()[1].getUnitCount(), 5, "addUnit failed, should return 5");
    }

    //testRemoveUnit3: Tests the removeUnit method when removing units from the player with multiple units.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testRemoveUnit3() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        player.addUnit("Single", 15);
        player.addUnit("Triple", 5);
        player.removeUnit("Single", 5);
        player.removeUnit("Triple", 2);

        assertEquals(player.getUnits()[0].getUnitCount(), 10, "removeUnit failed, should return 10");
        assertEquals(player.getUnits()[1].getUnitCount(), 3, "removeUnit failed, should return 3");
    }

    //testGetArmyCount: Tests the getArmyCount method, checking if it correctly calculates the total army count.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetArmyCount() {
        Player player = new Player(0, "Aggressive", "Magus", 4);
        player.addUnit("Single", 5);
        player.addUnit("Triple", 2);

        // Assuming each Single unit is worth 1 and each Triple unit is worth 3
        assertEquals(5 + 2 * 3, player.getArmyCount(), "getArmyCount failed, should return the total army count");
    }

    //testDeployUnits: Tests the deployUnits method, checking if it correctly deploys units to the player's territories.
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testDeployUnits() {
        // Create a player
        Player player = new Player(1, "Aggressive", "Leader Name", 0);

        // Create and add units to the player
        player.addUnit("Single", 10);

        // Create and add territories to the player
        Territory territory1 = new Territory("Territory 1", "Mortwald", 0, 200);
        Territory territory2 = new Territory("Territory 2", "Mortwald", 1, 200);
        territory1.setOwnerNumber(player.getPlayerNumber());
        territory2.setOwnerNumber(player.getPlayerNumber());
        player.addTerritory(territory1);
        player.addTerritory(territory2);

        // Deploy units
        player.deployUnits();

        // Check if the units have been deployed correctly
        int territory1UnitCount = territory1.getArmyCount();
        int territory2UnitCount = territory2.getArmyCount();

        assertTrue(territory1UnitCount > 0);
        assertTrue(territory2UnitCount > 0);
        assertEquals(territory1UnitCount + territory2UnitCount, 10);
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testDeployUnits_noTerritories() {
        // Create a player and add units
        Player player = new Player(1, "Aggressive", "Leader Name", 0);
        player.addUnit("Single", 10);

        // Call deployUnits
        player.deployUnits();

        // Verify that the units have not been deployed
        assertEquals(player.getUnits()[0].getUnitCount(), 10);
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testDeployUnits_noUnits() {
        // Create a player and add a territory
        Player player = new Player(1, "Aggressive", "Leader Name", 0);
        Territory territory = new Territory("Territory 1", "Mortwald", 0, 200);
        territory.setOwnerNumber(player.getPlayerNumber());
        player.addTerritory(territory);

        // Call deployUnits
        player.deployUnits();

        // Verify that no units have been deployed
        assertEquals(territory.getArmyCount(), 0);
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testDeployUnits_oneTerritory() {
        // Create a player, add units, and add a territory
        Player player = new Player(1, "Aggressive", "Leader Name", 0);
        player.addUnit("Single", 10);
        Territory territory = new Territory("Territory 1", "Mortwald", 0, 200);
        territory.setOwnerNumber(player.getPlayerNumber());
        player.addTerritory(territory);

        // Call deployUnits
        player.deployUnits();

        // Verify that all units have been deployed to the single territory
        assertEquals(territory.getArmyCount(), 10);
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetControlledRegions_noControl() {
        // Create a player without any controlled regions
        Player player = new Player(1, "Aggressive", "Leader Name", 0);

        // Call getControlledRegions
        ArrayList<String> controlledRegions = player.getControlledRegions();

        // Verify the player controls no regions
        assertTrue(controlledRegions.isEmpty());
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetControlledRegions_partialControl() {
        // Assume a game with territories belonging to the same region
        Territory territory1 = new Territory("Territory 1", "Region 1", 0, 200);
        Territory territory2 = new Territory("Territory 2", "Region 1", 1, 200);

        // Create a player who controls some territories in a region
        Player player = new Player(1, "Aggressive", "Leader Name", 0);
        player.addTerritory(territory1);

        // Call getControlledRegions
        List<String> controlledRegions = player.getControlledRegions();

        // Verify the player does not control the region
        assertTrue(controlledRegions.isEmpty());
    }

}