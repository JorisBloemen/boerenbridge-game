package nl.han.ica.ap.boerenbridge.speler.mens;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.ISpeler;
import nl.han.ica.ap.boerenbridge.speler.mens.gui.console.SpelerControllerConsole;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Implementatie van de menselijke speler.
 */
public class Speler implements ISpeler {

    private String naam;
    private ArrayList<Kaart> hand;
    private ISpelerController spelerController;

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
        return this.spelerController.vraagBod(this.hand);
    }

    @Override
    public void ontvangKaart(Kaart kaart) {
        this.hand.add(kaart);
        sorteerKaarten(this.hand);
    }

    @Override
    public Kaart geefKaart(ArrayList<Kaart> bord) {
        return this.spelerController.vraagKaart(bord, this.hand);
    }

    @Override
    public void verwijderKaartUitHand(Kaart kaart) {
        this.hand.remove(this.hand.indexOf(kaart));
    }

    @Override
    public void updateTussenstand(HashMap<String, Integer> tussenstand) {
        this.spelerController.toonTussenstandRonde(tussenstand);
    }

    @Override
    public ArrayList<Kaart> toonHand() {
        return this.hand;
    }

    @Override
    public void bekijkBord(ArrayList<Kaart> bord) {
        this.spelerController.bekijkBord(bord);
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
