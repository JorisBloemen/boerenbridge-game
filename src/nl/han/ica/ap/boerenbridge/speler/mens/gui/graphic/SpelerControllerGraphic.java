package nl.han.ica.ap.boerenbridge.speler.mens.gui.graphic;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.mens.ISpelerController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SpelerControllerGraphic implements ISpelerController {

    private SwingGui gui;

    public SpelerControllerGraphic(){
        this.gui = new SwingGui();
    }

    @Override
    public int vraagBod(ArrayList<Kaart> hand, ArrayList<String> spelerNamen,
                        HashMap<String, Integer> biedingen) {
        return gui.doeEenBod();
    }

    @Override
    public void toonBord(ArrayList<Kaart> bord) {

    }

    @Override
    public Kaart vraagKaart(ArrayList<String> spelerNamen,
                            HashMap<String, Kaart> bord,
                            ArrayList<Kaart> hand) {
        return null;
    }

    @Override
    public void toonTussenstandRonde(HashMap<String, Integer> tussenstand,
                                     int rondenummer) {
        gui.updateTussenstand(tussenstand, rondenummer);
    }

    @Override
    public void toonTussenstandSlag(HashMap<String, int[]> tussenstand,
                                    int slagnummer) {
        gui.updateSlagScore(tussenstand, slagnummer);
    }

    @Override
    public void bekijkBord(ArrayList<String> spelerNamen,
                           HashMap<String, Kaart> bord) {
        gui.updatePlayedCards(spelerNamen, bord);
    }
}
