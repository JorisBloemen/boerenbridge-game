package nl.han.ica.ap.boerenbridge.spel;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import static nl.han.ica.ap.boerenbridge.kaart.KaartType.*;
import static nl.han.ica.ap.boerenbridge.kaart.KaartWaarde.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SlagTest {

    private ISpeler speler1, speler2, speler3, speler4, speler5;
    private ArrayList<ISpeler> spelers;

    @Before
    public void setUp() throws Exception {
        this.speler1 = new ISpeler() {
            public int doeBieding() { return 0; }
            public void ontvangKaart(Kaart kaart) { }
            public Kaart geefKaart(ArrayList<Kaart> kaarten)
            { return new Kaart(SCHOPPEN, VIER); }
            public void updateScore(int score) { }
        };

        this.speler2 = new ISpeler() {
            public int doeBieding() { return 0; }
            public void ontvangKaart(Kaart kaart) { }
            public Kaart geefKaart(ArrayList<Kaart> kaarten)
            { return new Kaart(SCHOPPEN, ACHT); }
            public void updateScore(int score) { }
        };

        this.speler3 = new ISpeler() {
            public int doeBieding() { return 0; }
            public void ontvangKaart(Kaart kaart) { }
            public Kaart geefKaart(ArrayList<Kaart> kaarten)
            { return new Kaart(HARTEN, VIER); }
            public void updateScore(int score) { }
        };

        this.speler4 = new ISpeler() {
            public int doeBieding() { return 0; }
            public void ontvangKaart(Kaart kaart) { }
            public Kaart geefKaart(ArrayList<Kaart> kaarten)
            { return new Kaart(RUITEN, VIER); }
            public void updateScore(int score) { }
        };

        this.speler5 = new ISpeler() {
            public int doeBieding() { return 0; }
            public void ontvangKaart(Kaart kaart) { }
            public Kaart geefKaart(ArrayList<Kaart> kaarten)
            { return new Kaart(RUITEN, ZEVEN); }
            public void updateScore(int score) { }
        };

        this.spelers = new ArrayList<ISpeler>();
    }

    @Test
    public void speelSlagMetTroefLaagEerst() throws Exception {
        this.spelers.add(this.speler4); // ruiten 4
        this.spelers.add(this.speler3); // harten 4
        this.spelers.add(this.speler5); // ruiten 7
        this.spelers.add(this.speler1); // schoppen 4
        this.spelers.add(this.speler2); // schoppen 8

        Slag slag = new Slag(this.spelers);
        assertEquals(this.speler2, slag.speelSlag());
    }

    @Test
    public void speelSlagMetTroefHoogEerst() throws Exception {
        this.spelers.add(this.speler4); // ruiten 4
        this.spelers.add(this.speler3); // harten 4
        this.spelers.add(this.speler5); // ruiten 7
        this.spelers.add(this.speler2); // schoppen 8
        this.spelers.add(this.speler1); // schoppen 4

        Slag slag = new Slag(this.spelers);
        assertEquals(this.speler2, slag.speelSlag());
    }

    @Test
    public void speelSlagZonderTroefLaagEerst() throws Exception {
        this.spelers.add(this.speler4); // ruiten 4
        this.spelers.add(this.speler3); // harten 4
        this.spelers.add(this.speler5); // ruiten 7

        Slag slag = new Slag(this.spelers);
        assertEquals(this.speler5, slag.speelSlag());
    }

    @Test
    public void speelSlagZonderTroefHoogEerst() throws Exception {
        this.spelers.add(this.speler5); // ruiten 7
        this.spelers.add(this.speler4); // ruiten 4
        this.spelers.add(this.speler3); // harten 4

        Slag slag = new Slag(this.spelers);
        assertEquals(this.speler5, slag.speelSlag());
    }
}
