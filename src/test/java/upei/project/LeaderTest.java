package upei.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
public class LeaderTest {

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor() {
        Leader leader = new Leader("Magus", 4);
        assertEquals("Magus", leader.getName(), "Leader constructor failed, name should be Magus");
        assertEquals(4, leader.getFactionID(), "Leader constructor failed, factionID should be 4");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor2() {
        try{
            Leader leader = new Leader("Magus", 42);
        } catch (IllegalArgumentException e){
            assertEquals("Faction ID must be between 0 and 4",
                    e.getMessage(), "Faction ID outside expected bounds, should throw IllegalArgumentException");
        }
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetName() {
        Leader leader = new Leader("Magus", 4);
        assertEquals("Magus", leader.getName(), "Leader getName failed, name should be Magus");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetFactionID() {
        Leader leader = new Leader("Magus", 4);
        assertEquals(4, leader.getFactionID(), "Leader getFactionID failed, factionID should be 42");
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetFactionID2() {
        Leader leader = new Leader("Magus", 4);
        assertNotEquals(3, leader.getFactionID(), "Leader getFactionID failed, factionID should be 42");
    }
    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor3() {
        Leader leader = new Leader("Marneus Calgar", 0);
        assertEquals("Marneus Calgar", leader.getName(), "Leader constructor failed, name should be Marneus Calgar");
        assertEquals(0, leader.getFactionID(), "Leader constructor failed, factionID should be 0");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetName2() {
        Leader leader = new Leader("Abaddon the Despoiler", 1);
        assertEquals("Abaddon the Despoiler", leader.getName(), "Leader getName failed, name should be Abaddon the Despoiler");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetFactionID3() {
        Leader leader = new Leader("Abaddon the Despoiler", 1);
        assertEquals(1, leader.getFactionID(), "Leader getFactionID failed, factionID should be 1");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetFactionID4() {
        Leader leader = new Leader("Autarch", 2);
        assertEquals(2, leader.getFactionID(), "Leader getFactionID failed, factionID should be 2");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetFactionID5() {
        Leader leader = new Leader("Warboss", 3);
        assertEquals(3, leader.getFactionID(), "Leader getFactionID failed, factionID should be 3");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetFactionID6() {
        Leader leader = new Leader("Magus", 4);
        assertEquals(4, leader.getFactionID(), "Leader getFactionID failed, factionID should be 4");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetFactionID7() {
        Leader leader = new Leader("Magus", 4);
        assertNotEquals(0, leader.getFactionID(), "Leader getFactionID failed, factionID should not be 0");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor4() {
        Leader leader = new Leader("Marneus Calgar", 0);
        assertEquals("Marneus Calgar", leader.getName(), "Leader constructor failed, name should be Marneus Calgar");
        assertEquals(0, leader.getFactionID(), "Leader constructor failed, factionID should be 0");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor5() {
        Leader leader = new Leader("Autarch", 2);
        assertEquals("Autarch", leader.getName(), "Leader constructor failed, name should be Autarch");
        assertEquals(2, leader.getFactionID(), "Leader constructor failed, factionID should be 2");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor6() {
        Leader leader = new Leader("Warboss", 3);
        assertEquals("Warboss", leader.getName(), "Leader constructor failed, name should be Warboss");
        assertEquals(3, leader.getFactionID(), "Leader constructor failed, factionID should be 3");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor7() {
        Leader leader = new Leader("Abaddon the Despoiler", 1);
        assertEquals("Abaddon the Despoiler", leader.getName(), "Leader constructor failed, name should be Abaddon the Despoiler");
        assertEquals(1, leader.getFactionID(), "Leader constructor failed, factionID should be 1");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testConstructor8() {
        Leader leader = new Leader("Unknown Leader", 0);
        assertEquals("Unknown Leader", leader.getName(), "Leader constructor failed, name should be Unknown Leader");
        assertEquals(0, leader.getFactionID(), "Leader constructor failed, factionID should be 0");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetName3() {
        Leader leader = new Leader("Warboss", 3);
        assertEquals("Warboss", leader.getName(), "Leader getName failed, name should be Warboss");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetFactionID8() {
        Leader leader = new Leader("Abaddon the Despoiler", 1);
        assertEquals(1, leader.getFactionID(), "Leader getFactionID failed, factionID should be 1");
    }

    @Timeout(value=100, unit= TimeUnit.MILLISECONDS)
    @Test
    void testGetFactionID9() {
        Leader leader = new Leader("Ultramarines Leader", 0);
        assertEquals(0, leader.getFactionID(), "Leader getFactionID failed, factionID should be 0");
    }

}