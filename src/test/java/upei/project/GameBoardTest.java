package upei.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.function.Executable;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
public class GameBoardTest {

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testConstructor() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);
        assertNotNull(gameBoard, "GameBoard constructor failed, gameBoard should not be null");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByID() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);
        Territory territory = new Territory("Rejuvenated Strongport", "Mortwald", 0, 100);
        assertEquals(territory.getName(), gameBoard.getTerritoryByID(0).getName(),
                "getTerritoryByID failed, territory name should be Rejuvenated Strongport");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByName() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);
        Territory territory = new Territory("Rejuvenated Strongport", "Mortwald", 0, 100);
        assertEquals(territory.getName(), gameBoard.getTerritoryByName("Rejuvenated Strongport").getName(),
                "getTerritoryByName failed, territory name should be Rejuvenated Strongport");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByRegion() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);
        Territory territory = new Territory("Rejuvenated Strongport", "Mortwald", 0, 100);
        Territory[] tRegion = gameBoard.getTerritoryByRegion("Mortwald");
        if (tRegion.length == 0) {
            fail("No territories found");
        } else if (tRegion.length == 1) {
            assertEquals(territory.getRegion(), tRegion[0].getRegion(),
                    "getTerritoryByRegion failed, territory region should be Mortwald");
        } else {
            for (Territory t : tRegion) {
                assertEquals(territory.getRegion(), t.getRegion(),
                        "getTerritoryByRegion failed, territory region should be Mortwald");
            }
        }
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByWorth() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);
        Territory territory = new Territory("Rejuvenated Strongport", "Mortwald", 0, 100);
        Territory[] tWorth = gameBoard.getTerritoryByWorth(100);
        if (tWorth.length == 0) {
            fail("No territories found");
        } else if (tWorth.length == 1) {
            assertEquals(territory.getWorth(), tWorth[0].getWorth(),
                    "getTerritoryByWorth failed, territory worth should be 100");
        } else {
            for (Territory t : tWorth) {
                assertEquals(territory.getWorth(), t.getWorth(),
                        "getTerritoryByWorth failed, territory worth should be 100");
            }
        }
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByWorth2() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);
        Territory territory = new Territory("Rejuvenated Strongport", "Mortwald", 0, 200);
        Territory[] tWorth = gameBoard.getTerritoryByWorth(200);
        if (tWorth.length == 0) {
            fail("No territories found");
        } else if (tWorth.length == 1) {
            assertEquals(territory.getWorth(), tWorth[0].getWorth(),
                    "getTerritoryByWorth failed, territory worth should be 200");
        } else {
            for (Territory t : tWorth) {
                assertEquals(territory.getWorth(), t.getWorth(),
                        "getTerritoryByWorth failed, territory worth should be 200");
            }
        }
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testCheckBorder() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);
        Territory territory1 = new Territory("Rejuvenated Strongport", "Mortwald", 0, 100);
        Territory territory2 = new Territory("Ageless Weald", "Mortwald", 1, 100);
        assertTrue(gameBoard.checkBorder(territory1, territory2), "checkBorder failed, should return true");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testCheckBorder2() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);
        Territory territory1 = new Territory("Rejuvenated Strongport", "Mortwald", 0, 100);
        Territory territory2 = new Territory("Kaelac's Bane-Hyperia Fortwall", "Kaelac's Bane/Hyperia", 41, 100);
        assertFalse(gameBoard.checkBorder(territory1, territory2), "checkBorder failed, should return false");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByIDEdgeCase() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);

        // Test with the first territory (ID = 0)
        assertEquals("Rejuvenated Strongport", gameBoard.getTerritoryByID(0).getName(),
                "getTerritoryByID failed, territory name should be Rejuvenated Strongport");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByNameNotFound() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);

    // Test with a non-existent territory name
    assertNull(gameBoard.getTerritoryByName("NonExistentTerritory"),
                "getTerritoryByName failed, should return null for non-existent territory");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByRegionEmpty() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);

        // Test with a region that has no territories
        assertEquals(0, gameBoard.getTerritoryByRegion("NonExistentRegion").length,
                "getTerritoryByRegion failed, should return an empty array for non-existent region");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testCheckBorderNullTerritory() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);
        GameBoard gameBoard = new GameBoard(player1, player2, player3);

        // Test with a null territory
        assertThrows(NullPointerException.class, () -> gameBoard.checkBorder(null, gameBoard.getTerritoryByID(1)),
                "checkBorder failed, should throw NullPointerException for null territory");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testCheckBorderNullTerritory2() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        GameBoard gameBoard = new GameBoard(player1, null, null);

        // Test with a null territory
        assertThrows(NullPointerException.class, () -> gameBoard.checkBorder(gameBoard.getTerritoryByID(1), null),
                "checkBorder failed, should throw NullPointerException for null territory");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByIDNegativeID() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        GameBoard gameBoard = new GameBoard(player1, null, null);

        // Test with a negative territory ID
        assertThrows(IllegalArgumentException.class, () -> gameBoard.getTerritoryByID(-1),
                "getTerritoryByID failed, should throw IllegalArgumentException \"Territory ID must be between 0 and 41\"");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByIDNegativeID2() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        GameBoard gameBoard = new GameBoard(player1, null, null);

        // Test with a negative territory ID
        assertThrows(IllegalArgumentException.class, () -> gameBoard.getTerritoryByID(-100),
                "getTerritoryByID failed, should throw IllegalArgumentException \"Territory ID must be between 0 and 41\"");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByIDOutOfBoundsID() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        GameBoard gameBoard = new GameBoard(player1, null, null);

        // Test with an out of bounds territory ID
        assertThrows(IllegalArgumentException.class, () -> gameBoard.getTerritoryByID(42),
                "getTerritoryByID failed, should throw IllegalArgumentException for out of bounds territory ID");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByIDOutOfBoundsID2() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        GameBoard gameBoard = new GameBoard(player1, null, null);

        // Test with an out of bounds territory ID
        assertThrows(IllegalArgumentException.class, () -> gameBoard.getTerritoryByID(100),
                "getTerritoryByID failed, should throw IllegalArgumentException for out of bounds territory ID");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByRegionNullRegion() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        GameBoard gameBoard = new GameBoard(player1, null, null);

        // Test with a null region
        assertThrows(NullPointerException.class, () -> gameBoard.getTerritoryByRegion(null),
                "getTerritoryByRegion failed, should throw NullPointerException for null region");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByWorthNegativeWorth() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        GameBoard gameBoard = new GameBoard(player1, null, null);

        // Test with a negative territory worth
        assertThrows(IllegalArgumentException.class, () -> gameBoard.getTerritoryByWorth(-1),
                "getTerritoryByWorth failed, should throw IllegalArgumentException \"Territory worth must be between 0 and 200\"");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByWorthNegativeWorth2() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        GameBoard gameBoard = new GameBoard(player1, null, null);

        // Test with a negative territory worth
        assertThrows(IllegalArgumentException.class, () -> gameBoard.getTerritoryByWorth(-100),
                "getTerritoryByWorth failed, should throw IllegalArgumentException \"Territory worth must be between 0 and 200\"1");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByWorthOutOfBoundsWorth() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        GameBoard gameBoard = new GameBoard(player1, null, null);

        // Test with an out of bounds territory worth
        assertThrows(IllegalArgumentException.class, () -> gameBoard.getTerritoryByWorth(300),
                "getTerritoryByWorth failed, should throw IllegalArgumentException \"Territory worth must be between 0 and 200\"");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetTerritoryByWorthOutOfBoundsWorth2() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        GameBoard gameBoard = new GameBoard(player1, null, null);

        // Test with an out of bounds territory worth
        assertThrows(IllegalArgumentException.class, () -> gameBoard.getTerritoryByWorth(1000),
                "getTerritoryByWorth failed, should throw IllegalArgumentException \"Territory worth must be between 0 and 200\"");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testBordersRetrieval() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Roboute Guilliman", 0);
        Player player3 = new Player(2, "Opportunist", "Jaghatai Khan", 0);

        GameBoard gameBoard = new GameBoard(player1, player2, player3);

        Territory territory = gameBoard.getTerritoryByID(7);
        Territory[] borders = gameBoard.getBorders(territory);

        assertNotNull(borders, "Borders should not be null for territory 7");
        assertEquals(5, borders.length, "Territory 7 should have 5 neighboring territories");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testPlayerInitialization() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Roboute Guilliman", 0);
        Player player3 = new Player(2, "Opportunist", "Jaghatai Khan", 0);

        GameBoard gameBoard = new GameBoard(player1, player2, player3);
        // Test that players are initialized correctly
        assertNotNull(player1, "Player 0 should be initialized");
        assertEquals("Aggressive", player1.getPlayerStrategy(), "Player 0 should have Aggressive strategy");


        assertNotNull(player2, "Player 1 should be initialized");
        assertEquals("Pacifist", player2.getPlayerStrategy(), "Player 1 should have Pacifist strategy");

        assertNotNull(player3, "Player 1 should be initialized");
        assertEquals("Opportunist", player3.getPlayerStrategy(), "Player 2 should have Pacifist strategy");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetPlayer() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player2 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player3 = new Player(2, "Opportunist", "Autarch", 2);


        GameBoard gameBoard = new GameBoard(player1, player2, player3);
        assertEquals(3, gameBoard.getPlayers().size(), "Number of players should be 3");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetPlayerByNumber() {
        Player player0 = new Player(0, "Aggressive", "Marneus Calgar", 0);

        GameBoard gameBoard = new GameBoard(player0, null, null);
        assertEquals(player0, gameBoard.getPlayerByNumber(0), "Player 0 should be returned");
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testGetPlayerByNumber2() {
        Player player1 = new Player(0, "Aggressive", "Marneus Calgar", 0);

        GameBoard gameBoard = new GameBoard(player1, null, null);
        assertThrows(IllegalArgumentException.class, () -> gameBoard.getPlayerByNumber(-1),
                "IllegalArgumentException \"Player number must be between 0 and 2\" should be thrown");
    }
}

