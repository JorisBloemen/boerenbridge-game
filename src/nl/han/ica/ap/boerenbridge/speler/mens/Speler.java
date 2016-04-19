package nl.han.ica.ap.boerenbridge.speler.mens;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.ISpeler;

import java.util.ArrayList;

/**
 * Implementatie van de menselijke speler.
 */
public class Speler implements ISpeler {

    private String naam;
    private ArrayList<Kaart> hand;
    private SpelerControllerConsole spelerController;

    /**
     * Initialiseert de speler
     * @param naam De indentificatie van de speler.
     */
    public Speler(String naam) {
        this.naam = naam;
        this.hand = new ArrayList<Kaart>();
        this.spelerController = new SpelerControllerConsole();
    }

    @Override
    public String geefNaam() {
        return this.naam;
    }

    @Override
    public int doeBieding() {
        spelerController.toonKaarten(this.hand);
        System.out.print("Doe een bod: ");
        return spelerController.vraagInputInteger();
    }

    @Override
    public void ontvangKaart(Kaart kaart) {
        this.hand.add(kaart);
        sorteerKaarten(this.hand);
    }

    @Override
    public Kaart geefKaart(ArrayList<Kaart> kaarten) {
        System.out.println("\nGespeelde kaarten:");
        spelerController.toonKaarten(kaarten);
        System.out.println("\nKaarten in hand:");
        spelerController.toonKaartenMetIndex(this.hand);
        System.out.print("\nKies een kaart: ");
        int index = spelerController.vraagInputInteger();
        Kaart kaart = this.hand.get(index);
        this.hand.remove(index);
        return kaart;
    }

    @Override
    public void updateScore(int score) {
        System.out.println(this.naam + ": " + score);
    }

    @Override
    public ArrayList<Kaart> toonHand() {
        return this.hand;
    }

    @Override
    public void bekijkBord(ArrayList<Kaart> bord) {

    }

    /**
     *  Soteer kaarten op basis van type en waarde.
     * @param kaarten De te sorteren set van kaarten.
     */
    private void sorteerKaarten(ArrayList<Kaart> kaarten) {
        kaarten.sort((k1, k2) ->
                k1.getKaartWaarde().ordinal() - k2.getKaartWaarde().ordinal());
        kaarten.sort((k1, k2) ->
                k1.getKaartType().ordinal() - k2.getKaartType().ordinal());
    }

    /**
     * Print een lijst met kaarten.
     * @param kaarten Kaarten die moeten worden geprint.
     */
    private void printKaarten(ArrayList<Kaart> kaarten) {
        for (Kaart kaart : kaarten)
            System.out.println(kaart.getKaartType().toString() + " " +
                    kaart.getKaartWaarde().toString());
    }

    /**
     * Print een lijst met kaarten geprefixed met de index.
     * @param kaarten Kaarten die moeten worden geprint.
     */
    private void printKaartenMetIndex(ArrayList<Kaart> kaarten) {
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

        while(bodstr == null) {
            try {
                bodstr = br.readLine();
            } catch (java.io.IOException ioe) {
                ioe.printStackTrace();
            }
        }

        return Integer.parseInt(bodstr);
    }
}
