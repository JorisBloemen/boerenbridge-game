package nl.han.ica.ap.boerenbridge.speler.mens.gui.graphic;

import com.sun.xml.internal.bind.v2.TODO;
import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.kaart.KaartType;
import nl.han.ica.ap.boerenbridge.kaart.KaartWaarde;
import nl.han.ica.ap.boerenbridge.speler.mens.ISpelerController;
import nl.han.ica.ap.boerenbridge.speler.mens.gui.console.SpelerControllerConsole;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SpelerControllerGraphic implements ISpelerController {

    private SwingGui gui;
    private int rondeTeller;

    /**
     * Constructor voor SpelerControllerGraphic.
     * Initialiseerd tondeTeller, slagTeller een de drawEngine.
     */
    public SpelerControllerGraphic() {
        this.rondeTeller = 1;
        this.gui = new SwingGui();
    }

    @Override
    public int vraagBod(ArrayList<Kaart> hand, ArrayList<String> spelerNamen,
                        HashMap<String, Integer> biedingen) {
        gui.updateHand(hand);
        return gui.doeEenBod(spelerNamen, biedingen, hand.size());
    }

    @Override
    public void toonBord(ArrayList<Kaart> bord) {
    }

    @Override
    public Kaart vraagKaart(ArrayList<String> spelerNamen,
                            HashMap<String, Kaart> bord,
                            ArrayList<Kaart> hand) {
        gui.updatePlayedCards(spelerNamen, bord);
        gui.updateHand(hand);
        return gui.getGeselecteerdeKaart(hand);
    }

    @Override
    public void toonTussenstandRonde(HashMap<String, Integer> tussenstand) {
        gui.updateTussenstand(tussenstand, this.rondeTeller++);
    }

    @Override
    public void toonTussenstandSlag(HashMap<String, int[]> tussenstand) {
        gui.updateSlagScore(tussenstand);
    }

    @Override
    public void bekijkBord(ArrayList<String> spelerNamen,
                           HashMap<String, Kaart> bord) {
        gui.updatePlayedCards(spelerNamen, bord);
        gui.wachtOpGebruiker("volgende slag");
    }

    @Override
    public void verwijderKaartUitHand(ArrayList<Kaart> hand) {
        this.gui.updateHand(hand);
    }
}
