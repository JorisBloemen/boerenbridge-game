package nl.han.ica.ap.boerenbridge.kaart;

import org.junit.Before;
import org.junit.Test;

import static nl.han.ica.ap.boerenbridge.kaart.KaartType.HARTEN;
import static nl.han.ica.ap.boerenbridge.kaart.KaartWaarde.VIER;
import static org.junit.Assert.assertEquals;

// import enums

public class KaartTest {

    private Kaart kaart;

    @Before
    public void setUp() throws Exception {
        this.kaart = new Kaart(HARTEN, VIER);
    }

    @Test
    public void testGetKaartType() throws Exception {
        assertEquals(HARTEN, kaart.getKaartType());
    }

    @Test
    public void testGetKaartWaarde() throws Exception {
        assertEquals(VIER, kaart.getKaartWaarde());
    }
}
