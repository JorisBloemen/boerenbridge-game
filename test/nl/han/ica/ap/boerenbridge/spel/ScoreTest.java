package nl.han.ica.ap.boerenbridge.spel;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ScoreTest {

    private Score score;

    @Before
    public void setUp() throws Exception {
        ISpeler speler = new ISpeler() {
            public int doeBieding() { return 0; }
            public void ontvangKaart(Kaart kaart) {}
            public Kaart geefKaart(ArrayList<Kaart> kaarten) { return null; }
            public void updateScore(int score) {}
        };

        this.score = new Score(speler, 4);
    }

    @Test
    public void berekenScoreWin() throws Exception {
        for (int i = 0; i < 4; i++)
            this.score.winSlag();
        assertEquals(18, this.score.berekenScore());
    }

    @Test
    public void berekenScoreVerlies() throws Exception {
        for (int i = 0; i < 6; i++)
            this.score.winSlag();
        assertEquals(-4, this.score.berekenScore());
    }
}
