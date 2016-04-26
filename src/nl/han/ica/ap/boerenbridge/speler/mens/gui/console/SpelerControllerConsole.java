package nl.han.ica.ap.boerenbridge.speler.mens.gui.console;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.mens.ISpelerController;

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
            ioe.printStackTrace();
            // Crash
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
        for (Map.Entry<String, Integer> e : tussenstand.entrySet())
            System.out.println(e.getKey() + ": " + e.getValue());
    }

    @Override
    public void toonTussenstandSlag(HashMap<String, int[]> tussenstand) {
        // TODO: 20160419: Ook het bod van de speler laten zien?
        System.out.println("Tussenstand gewonnen slagen:");
        System.out.println("Speler: bod | score");
        for (Map.Entry<String, int[]> e : tussenstand.entrySet())
            System.out.println(
                    e.getKey() + ": "
                            + e.getValue()[0] + " | " + e.getValue()[1]);
    }

    @Override
    public void bekijkBord(ArrayList<Kaart> bord) {
        System.out.println("Gespeelde kaarten:");
        toonKaarten(bord);
    }
}
