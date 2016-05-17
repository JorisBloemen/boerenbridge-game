package nl.han.ica.ap.boerenbridge.spel;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.ISpeler;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ScoreTest {

    private Score score;

    @Before
    public void setUp() throws Exception {
        ISpeler speler = new ISpeler() {
            public String geefNaam() { return ""; }
            public int doeBieding(
                    ArrayList<String> spelerNamen,
                    HashMap<String, Integer> biedingen)
            { return 0; }
            public void ontvangKaart(Kaart kaart) {}
            public Kaart geefKaart(
                    ArrayList<String> spelers,
                    HashMap<String, Kaart> bord) { return null; }
            public Kaart verwijderKaartUitHand(Kaart kaart) { return null; }
            public void updateRondeTussenstand(
                    HashMap<String, Integer> tussenstand) { }
            public void updateSlagTussenstand(
                    HashMap<String, int[]> tussenstand) { }
            public void updateScore(int score) {}
            public ArrayList<Kaart> toonHand() { return null; }
            public void bekijkBord(ArrayList<String> spelerNamen,
                                   HashMap<String, Kaart> bord) {}
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
