package nl.han.ica.ap.boerenbridge.speler.mens;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    private void printHashMapPlayerScore(HashMap<String, Integer> score) {
        for (Map.Entry<String, Integer> e : score.entrySet())
            System.out.println(e.getKey() + ": " + e.getValue());
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

    @Override
    public int vraagBod(ArrayList<Kaart> hand) {
        toonKaarten(hand);
        System.out.println("Doe een bod:");
        return vraagInputInteger();
    }

    @Override
    public void toonBord(ArrayList<Kaart> bord) {
        toonKaarten(bord);
    }

    @Override
    public Kaart vraagKaart(ArrayList<Kaart> bord, ArrayList<Kaart> hand) {
        toonKaarten(bord);
        toonKaartenMetIndex(hand);
        System.out.println("Geef de index van de kaart die je wilt spelen:");
        return hand.get(vraagInputInteger());
        // TODO: 20160419: verwijder uit lijst. Na verificatie van spel.
    }

    @Override
    public void toonTussenstandRonde(HashMap<String, Integer> tussenstand) {
        // TODO: 20160419: Ook het nummer van de ronde laten zien?
        System.out.println("Tussenstand ronde:");
        printHashMapPlayerScore(tussenstand);
    }

    @Override
    public void toonTussenstandSlag(HashMap<String, Integer> tussenstand) {
        // TODO: 20160419: Ook het bod van de speler laten zien?
        System.out.println("Tussenstand gewonnen slagen:");
        printHashMapPlayerScore(tussenstand);
    }
}
