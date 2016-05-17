package nl.han.ica.ap.boerenbridge.spel;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.ISpeler;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static nl.han.ica.ap.boerenbridge.kaart.KaartType.*;
import static nl.han.ica.ap.boerenbridge.kaart.KaartWaarde.*;
import static org.junit.Assert.assertEquals;

public class RondeTest {

    private ISpeler speler1, speler2, speler3, speler4, speler5;
    private ArrayList<ISpeler> spelers;
    private HashMap<ISpeler, Integer> resultaat;

    @Before
    public void setUp() throws Exception {
        this.speler1 = new ISpeler() {
            Kaart kaart = new Kaart(SCHOPPEN, VIER);
            public String geefNaam() { return ""; }
            public int doeBieding(
                    ArrayList<String> spelerNamen,
                    HashMap<String, Integer> biedingen)
            { return 1; }
            public void ontvangKaart(Kaart kaart) { }
            public Kaart geefKaart(
                    ArrayList<String> spelers,
                    HashMap<String, Kaart> bord) { return kaart; }
            public Kaart verwijderKaartUitHand(Kaart kaart) { return kaart; }
            public void updateRondeTussenstand(
                    HashMap<String, Integer> tussenstand) { }
            public void updateSlagTussenstand(
                    HashMap<String, int[]> tussenstand) { }
            public void updateScore(int score) { }
            public ArrayList<Kaart> toonHand() {
                ArrayList<Kaart> hand = new ArrayList<Kaart>();
                hand.add(kaart);
                return hand;
            }
            public void bekijkBord(ArrayList<String> spelerNamen,
                                   HashMap<String, Kaart> bord) {}
        };

        this.speler2 = new ISpeler() {
            Kaart kaart = new Kaart(SCHOPPEN, ACHT);
            public String geefNaam() { return ""; }
            public int doeBieding(
                    ArrayList<String> spelerNamen,
                    HashMap<String, Integer> biedingen)
            { return 1; }
            public void ontvangKaart(Kaart kaart) { }
            public Kaart geefKaart(
                    ArrayList<String> spelers,
                    HashMap<String, Kaart> bord) { return kaart; }
            public Kaart verwijderKaartUitHand(Kaart kaart) { return kaart; }
            public void updateRondeTussenstand(
                    HashMap<String, Integer> tussenstand) { }
            public void updateSlagTussenstand(
                    HashMap<String, int[]> tussenstand) { }
            public void updateScore(int score) { }
            public ArrayList<Kaart> toonHand() {
                ArrayList<Kaart> hand = new ArrayList<Kaart>();
                hand.add(kaart);
                return hand;
            }
            public void bekijkBord(ArrayList<String> spelerNamen,
                                   HashMap<String, Kaart> bord) {}
        };

        this.speler3 = new ISpeler() {
            Kaart kaart = new Kaart(HARTEN, VIER);
            public String geefNaam() { return ""; }
            public int doeBieding(
                    ArrayList<String> spelerNamen,
                    HashMap<String, Integer> biedingen)
            { return 0; }
            public void ontvangKaart(Kaart kaart) { }
            public Kaart geefKaart(
                    ArrayList<String> spelers,
                    HashMap<String, Kaart> bord) { return kaart; }
            public Kaart verwijderKaartUitHand(Kaart kaart) { return kaart; }
            public void updateRondeTussenstand(
                    HashMap<String, Integer> tussenstand) { }
            public void updateSlagTussenstand(
                    HashMap<String, int[]> tussenstand) { }
            public void updateScore(int score) { }
            public ArrayList<Kaart> toonHand() {
                ArrayList<Kaart> hand = new ArrayList<Kaart>();
                hand.add(kaart);
                return hand;
            }
            public void bekijkBord(ArrayList<String> spelerNamen,
                                   HashMap<String, Kaart> bord) {}
        };

        this.speler4 = new ISpeler() {
            Kaart kaart = new Kaart(RUITEN, VIER);
            public String geefNaam() { return ""; }
            public int doeBieding(
                    ArrayList<String> spelerNamen,
                    HashMap<String, Integer> biedingen)
            { return 0; }
            public void ontvangKaart(Kaart kaart) { }
            public Kaart geefKaart(
                    ArrayList<String> spelers,
                    HashMap<String, Kaart> bord) { return kaart; }
            public Kaart verwijderKaartUitHand(Kaart kaart) { return kaart; }
            public void updateRondeTussenstand(
                    HashMap<String, Integer> tussenstand) { }
            public void updateSlagTussenstand(
                    HashMap<String, int[]> tussenstand) { }
            public void updateScore(int score) { }
            public ArrayList<Kaart> toonHand() {
                ArrayList<Kaart> hand = new ArrayList<Kaart>();
                hand.add(kaart);
                return hand;
            }
            public void bekijkBord(ArrayList<String> spelerNamen,
                                   HashMap<String, Kaart> bord) {}
        };

        this.speler5 = new ISpeler() {
            Kaart kaart = new Kaart(RUITEN, ZEVEN);
            public String geefNaam() { return ""; }
            public int doeBieding(
                    ArrayList<String> spelerNamen,
                    HashMap<String, Integer> biedingen)
            { return 0; }
            public void ontvangKaart(Kaart kaart) { }
            public Kaart geefKaart(
                    ArrayList<String> spelers,
                    HashMap<String, Kaart> bord) { return kaart; }
            public Kaart verwijderKaartUitHand(Kaart kaart) { return kaart; }
            public void updateRondeTussenstand(
                    HashMap<String, Integer> tussenstand) { }
            public void updateSlagTussenstand(
                    HashMap<String, int[]> tussenstand) { }
            public void updateScore(int score) { }
            public ArrayList<Kaart> toonHand() {
                ArrayList<Kaart> hand = new ArrayList<Kaart>();
                hand.add(kaart);
                return hand;
            }
            public void bekijkBord(ArrayList<String> spelerNamen,
                                   HashMap<String, Kaart> bord) {}
        };

        this.spelers = new ArrayList<ISpeler>();
        this.resultaat = new HashMap<ISpeler, Integer>();
    }

    @Test
    public void speelRondeEenSlagMetTroef() throws Exception {
        this.spelers.add(this.speler4); // ruiten 4
        this.spelers.add(this.speler3); // harten 4
        this.spelers.add(this.speler5); // ruiten 7
        this.spelers.add(this.speler1); // schoppen 4
        this.spelers.add(this.speler2); // schoppen 8

        Ronde ronde = new Ronde(this.spelers, 1);
        this.resultaat = ronde.speelRonde();

        assertEquals(-2, (long) this.resultaat.get(this.speler1));
        assertEquals(12, (long) this.resultaat.get(this.speler2));
        assertEquals(10, (long) this.resultaat.get(this.speler3));
        assertEquals(10, (long) this.resultaat.get(this.speler4));
        assertEquals(10, (long) this.resultaat.get(this.speler5));
    }

    @Test
    public void speelRondeEenSlagZonderTroef() throws Exception {
        this.spelers.add(this.speler4); // ruiten 4
        this.spelers.add(this.speler3); // harten 4
        this.spelers.add(this.speler5); // ruiten 7

        Ronde ronde = new Ronde(this.spelers, 1);
        this.resultaat = ronde.speelRonde();

        assertEquals(10, (long) this.resultaat.get(this.speler3));
        assertEquals(10, (long) this.resultaat.get(this.speler4));
        assertEquals(-2, (long) this.resultaat.get(this.speler5));
    }
}
