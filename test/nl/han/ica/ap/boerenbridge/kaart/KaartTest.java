package nl.han.ica.ap.boerenbridge.kaart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// import enums
import static nl.han.ica.ap.boerenbridge.kaart.KaartType.*;
import static nl.han.ica.ap.boerenbridge.kaart.KaartWaarde.*;

import static org.junit.Assert.*;

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
