package nl.han.ica.ap.boerenbridge.speler.mens;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.spel.ISpeler;

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
}
