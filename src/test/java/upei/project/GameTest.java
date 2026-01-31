package upei.project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
public class GameTest {

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testConstructor() throws IOException {
        Player player0 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player1 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player2 = new Player(2, "Opportunist", "Autarch", 2);

        Game game = new Game(player0, player1, player2);
        assertNotNull(game);
    }

    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @Test
    void testStartGame() throws IOException {
        Player player0 = new Player(0, "Aggressive", "Marneus Calgar", 0);
        Player player1 = new Player(1, "Pacifist", "Abaddon the Despoiler", 1);
        Player player2 = new Player(2, "Opportunist", "Autarch", 2);

        Game game = new Game(player0, player1, player2);
        game.startGame();
        assertNotNull(game);
    }
}
