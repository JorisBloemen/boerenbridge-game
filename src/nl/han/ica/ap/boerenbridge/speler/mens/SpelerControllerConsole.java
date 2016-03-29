package nl.han.ica.ap.boerenbridge.speler.mens;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Zorgt voor interactie tussen speler klasse en de gebruiker.
 */
public class SpelerControllerConsole implements ISpelerController {

    /**
     * Laat de kaarten aan de gebruiker zien.
     * @param kaarten Kaarten die getoond moeten worden.
     */
    private void toonKaarten(ArrayList<Kaart> kaarten) {
        for (Kaart kaart : kaarten)
            System.out.println(kaart.getKaartType().toString() + " " +
                    kaart.getKaartWaarde().toString());
    }

    /**
     * Laat de kaarten aan de gebruiker zien waaruit een keuze gemaakt kan
     * worden.
     * @param kaarten Kaarten waaruit de gebruiker kan kiezen.
     */
    private void toonKaartenMetIndex(ArrayList<Kaart> kaarten) {
        for (int i = 0; i < kaarten.size(); i++) {
            System.out.println(i + ") " +
                    kaarten.get(i).getKaartType().toString() + " " +
                    kaarten.get(i).getKaartWaarde().toString());
        }
    }

    /**
     * Vraag de speler een integer op te geven.
     * @return De opgegeven integer.
     */
    private int vraagInputInteger() {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));

        String bodstr = null;
        try {
            bodstr = br.readLine();
        } catch (java.io.IOException ioe) {
            System.err.println("io error: " + ioe.toString());
        }

        return Integer.parseInt(bodstr);
    }
}
