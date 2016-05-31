package nl.han.ica.ap.boerenbridge.speler.computer.algoritme.willekeurig;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.Kaartteller;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.Typeteller;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static nl.han.ica.ap.boerenbridge.kaart.KaartType.*;
import static nl.han.ica.ap.boerenbridge.kaart.KaartWaarde.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class KaartBepalerTest {

    private KaartBepaler kaartBepaler;
    private ArrayList<Kaart> hand;
    private ArrayList<Kaart> gespeeldeKaarten;

    @Before
    public void setUp() throws Exception {
        this.kaartBepaler = new KaartBepaler(new Kaartteller(),
                new Typeteller(5));

        this.hand = new ArrayList<>();
        this.hand.add(new Kaart(SCHOPPEN, AAS));
        this.hand.add(new Kaart(HARTEN, HEER));
        this.hand.add(new Kaart(KLAVEREN, TWEE));

        this.gespeeldeKaarten = new ArrayList<>();
    }

    @Test
    public void bekenKaart() throws Exception {
        this.gespeeldeKaarten.add(new Kaart(HARTEN, DRIE));
        assertEquals(HARTEN, this.kaartBepaler.bepaalKaart(this.hand,
                this.gespeeldeKaarten, 0).getKaartType());
    }

    @Test
    public void kanNietBekennen() throws Exception {
        this.gespeeldeKaarten.add(new Kaart(RUITEN, DRIE));
        assertNotEquals(RUITEN, this.kaartBepaler.bepaalKaart(this.hand,
                this.gespeeldeKaarten, 0).getKaartType());
    }

    @Test
    public void eersteSpeler() throws Exception {
        ArrayList<Kaart> hand = new ArrayList<>();
        hand.add(new Kaart(RUITEN, BOER));
        assertEquals(RUITEN, this.kaartBepaler.bepaalKaart(hand,
                this.gespeeldeKaarten, 0).getKaartType());
    }
}
