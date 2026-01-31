package upei.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
public class CardTest {

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testSubUnits() {
        Unit unit = new Unit("Unit", 0, "Single", 0);
        Leader leader = new Leader("Leader", 0);
        Territory territory = new Territory("Territory", "Mortwald", 0, 100);

        assertEquals("Unit", unit.getName(), "Card constructor failed, Unit name not set correctly");
        assertEquals("Leader", leader.getName(), "Card constructor failed, Leader name not set correctly");
        assertEquals("Territory", territory.getName(), "Card constructor failed, Territory name not set correctly");
    }

}
