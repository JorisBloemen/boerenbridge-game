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

public class BodBepalerTest {

    private BodBepaler bodBepaler;
    private ArrayList<Kaart> hand;

    @Before
    public void setUp() throws Exception {
        this.bodBepaler = new BodBepaler(new Kaartteller(), new Typeteller(5));
        this.hand = new ArrayList<>();
        this.hand.add(new Kaart(SCHOPPEN, VIJF));
        this.hand.add(new Kaart(KLAVEREN, AAS));
        this.hand.add(new Kaart(RUITEN, HEER));
        this.hand.add(new Kaart(SCHOPPEN, ACHT));
        this.hand.add(new Kaart(SCHOPPEN, TIEN));
        this.hand.add(new Kaart(HARTEN, BOER));
        this.hand.add(new Kaart(HARTEN, TIEN));
    }

    @Test
    public void bepaalBieding() throws Exception {
        assertEquals(2, this.bodBepaler.bepaalBieding(
                this.hand, new ArrayList<>()));
    }

}