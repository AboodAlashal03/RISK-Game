package upei.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
public class UnitTest {

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor() {
        Unit unit = new Unit("Primaris Intercessor", 0, "Single", 1);
        assertEquals("Primaris Intercessor", unit.getName(), "Unit constructor failed, name should be Primaris Intercessor");
        assertEquals(0, unit.getFactionID(), "Unit constructor failed, factionID should be 0");
        assertEquals("Single", unit.getUnitType(), "Unit constructor failed, unitType should be Single");
        assertEquals(1, unit.getUnitCount(), "Unit constructor failed, unitCount should be 1");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor2() {
        Unit unit = new Unit("Primaris Repulsor", 0, "Triple", 1);
        assertEquals("Primaris Repulsor", unit.getName(), "Unit constructor failed, name should be Primaris Repulsor");
        assertEquals(0, unit.getFactionID(), "Unit constructor failed, factionID should be 0");
        assertEquals("Triple", unit.getUnitType(), "Unit constructor failed, unitType should be Triple");
        assertEquals(1, unit.getUnitCount(), "Unit constructor failed, unitCount should be 1");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor3() {
        Unit unit = new Unit("Acolytes",4, "Single", 15);
        assertEquals("Acolytes", unit.getName(), "Unit constructor failed, name should be Acolytes");
        assertEquals(4, unit.getFactionID(), "Unit constructor failed, factionID should be 4");
        assertEquals("Single", unit.getUnitType(), "Unit constructor failed, unitType should be Single");
        assertEquals(15, unit.getUnitCount(), "Unit constructor failed, unitCount should be 5");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor4() {
        Unit unit = new Unit("Goliath",4, "Triple", 5);
        assertEquals("Goliath", unit.getName(), "Unit constructor failed, name should be Goliath");
        assertEquals(4, unit.getFactionID(), "Unit constructor failed, factionID should be 4");
        assertEquals("Triple", unit.getUnitType(), "Unit constructor failed, unitType should be Triple");
        assertEquals(5, unit.getUnitCount(), "Unit constructor failed, unitCount should be 5");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testSetUnitCount() {
        Unit unit = new Unit("Primaris Intercessor", 0, "Single", 1);
        unit.setUnitCount(5);
        assertEquals(5, unit.getUnitCount(), "Unit setUnitCount failed, unitCount should be 5");
        unit.setUnitCount(-1);
        assertEquals(0, unit.getUnitCount(), "Unit setUnitCount failed, unitCount should be 0");
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testCopyUnit() {
        Unit unit = new Unit("Primaris Intercessor", 0, "Single", 1);
        Unit unit2 = unit.copyUnit(1);
        assertEquals("Primaris Intercessor", unit2.getName(), "Unit copyUnit failed, name should be Primaris Intercessor");
        assertEquals(0, unit2.getFactionID(), "Unit copyUnit failed, factionID should be 0");
        assertEquals("Single", unit2.getUnitType(), "Unit copyUnit failed, unitType should be Single");
        assertEquals(1, unit2.getUnitCount(), "Unit copyUnit failed, unitCount should be 1");
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testCopyUnit2() {
        Unit unit = new Unit("Primaris Intercessor", 0, "Single", 1);
        Unit unit2 = unit.copyUnit(5);
        assertEquals("Primaris Intercessor", unit2.getName(), "Unit copyUnit failed, name should be Primaris Intercessor");
        assertEquals(0, unit2.getFactionID(), "Unit copyUnit failed, factionID should be 0");
        assertEquals("Single", unit2.getUnitType(), "Unit copyUnit failed, unitType should be Single");
        assertEquals(5, unit2.getUnitCount(), "Unit copyUnit failed, unitCount should be 5");
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testCopyUnit3() {
        Unit unit = new Unit("Primaris Intercessor", 0, "Single", 1);
        Unit unit2 = unit.copyUnit(0);
        assertEquals("Primaris Intercessor", unit2.getName(), "Unit copyUnit failed, name should be Primaris Intercessor");
        assertEquals(0, unit2.getFactionID(), "Unit copyUnit failed, factionID should be 0");
        assertEquals("Single", unit2.getUnitType(), "Unit copyUnit failed, unitType should be Single");
        assertEquals(0, unit2.getUnitCount(), "Unit copyUnit failed, unitCount should be 0");
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testInheritedProperties() {
        // Create a Unit instance
        Unit unit = new Unit("Primaris Intercessor", 0, "Single", 1);

        // Test name property inherited from Leader
        assertEquals("Primaris Intercessor", unit.getName(), "Unit inheritance failed, name should be Primaris Intercessor");

        // Test factionID property inherited from Leader
        assertEquals(0, unit.getFactionID(), "Unit inheritance failed, factionID should be 0");
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testUnitTypeNull() {
        // Create a Unit instance with null unitType
        Unit unit = new Unit("Primaris Intercessor", 0, null, 1);

        // Test unitType property for null
        assertNull(unit.getUnitType(), "UnitType should be null");
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testUnitTypeEmpty() {
        // Create a Unit instance with empty unitType
        Unit unit = new Unit("Primaris Intercessor", 0, "", 1);

        // Test unitType property for empty string
        assertEquals("", unit.getUnitType(), "UnitType should be an empty string");
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testNegativeUnitCount() {
        // Create a Unit instance with negative unitCount
        Unit unit = new Unit("Primaris Intercessor", 0, "Single", -1);

        // Test unitCount property for negative value
        assertEquals(0, unit.getUnitCount(), "UnitCount should be 0 for negative input");
    }

}
