package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }

    @Test
    public void etsiPelaaja() {
        String pelaaja = "Kurri";
        assertEquals(pelaaja, stats.search(pelaaja).getName());
    }

    @Test
    public void etsiPelaajaVirhe() {
        String pelaaja = "Murri";
        assertEquals(null, stats.search(pelaaja));
    }

    @Test
    public void palautaTiiminPelaajat() {
        String tiimi = "EDM";
        ArrayList<Player> playersOfTeam = new ArrayList<Player>();
        playersOfTeam.add(new Player("Semenko", "EDM", 4, 12));
        playersOfTeam.add(new Player("Kurri", "EDM", 37, 53));
        playersOfTeam.add(new Player("Gretzky", "EDM", 35, 89));
        assertEquals(playersOfTeam.toString(), stats.team(tiimi).toString());
    }

    @Test
    public void palautaParhaatPelaajat() {
        ArrayList<Player> bestPlayers = new ArrayList<Player>();
        bestPlayers.add(new Player("Gretzky", "EDM", 35, 89));
        bestPlayers.add(new Player("Lemieux", "PIT", 45, 54));
        assertEquals(bestPlayers.toString(), stats.topScorers(1).toString());
    }
}
