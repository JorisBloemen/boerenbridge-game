package nl.han.ica.ap.boerenbridge.spel;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.kaart.KaartType;
import nl.han.ica.ap.boerenbridge.kaart.KaartWaarde;
import org.junit.Before;
import org.junit.Test;

import static nl.han.ica.ap.boerenbridge.kaart.KaartType.SCHOPPEN;
import static nl.han.ica.ap.boerenbridge.kaart.KaartWaarde.TWEE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class KaartspelTest {

    private Kaartspel kaartspel;

    @Before
    public void setUp() throws Exception {
        this.kaartspel = new Kaartspel();
    }

    @Test
    public void testSchud() throws Exception {
        this.kaartspel.schud();

        for (KaartType kaartType : KaartType.values()) {
            for (KaartWaarde kaartWaarde : KaartWaarde.values()) {
                Kaart kaart = this.kaartspel.trekKaart();
                if (kaart.getKaartType() != kaartType
                        || kaart.getKaartWaarde() != kaartWaarde) {
                    assertTrue(true);
                    return;
                }
            }
        }
        assertTrue(false);
    }

    @Test
    public void testTrekKaart() throws Exception {
        Kaart kaart = this.kaartspel.trekKaart();
        assertEquals(SCHOPPEN, kaart.getKaartType());
        assertEquals(TWEE, kaart.getKaartWaarde());
    }
}
