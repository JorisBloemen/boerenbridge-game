package nl.han.ica.ap.boerenbridge.speler.computer;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.ISpeler;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.IBieding;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.IKaart;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.Kaartteller;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.Typeteller;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.willekeurig.BodBepaler;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.willekeurig.KaartBepaler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * De computerspeler beslist zelf een bod en welke kaarten gespeeld worden aan
 * de hand van het onderliggende algoritme.
 */
public class ComputerSpeler implements ISpeler {

    private String naam;
    private ArrayList<Kaart> hand;
    private IKaart kaartAlgoritme;
    private IBieding biedingAlgoritme;
    private Kaartteller kaartteller;
    private Typeteller typeteller;

    public ComputerSpeler(String naam)
    {
        this.naam = naam;
        this.hand = new ArrayList<Kaart>();
        this.kaartAlgoritme = new KaartBepaler();
        this.biedingAlgoritme = new BodBepaler();
        this.kaartteller = new Kaartteller();
        this.typeteller = new Typeteller(5);
    }

    @Override
    public String geefNaam() {
        return this.naam;
    }

    @Override
    public int doeBieding(ArrayList<String> spelerNamen,
                          HashMap<String, Integer> biedingen) {
        return this.biedingAlgoritme.bepaalBieding(
                this.hand, new ArrayList<Integer>());
    }

    @Override
    public void ontvangKaart(Kaart kaart) {
        this.hand.add(kaart);
        this.biedingAlgoritme.resetBodCounter();
    }

    @Override
    public Kaart geefKaart(ArrayList<String> spelers,
                           HashMap<String, Kaart> bord) {
        ArrayList<Kaart> gespeeldeKaarten = new ArrayList<Kaart>();
        for (String speler : spelers)
            gespeeldeKaarten.add(bord.get(speler));
        kaartteller.update(gespeeldeKaarten);
        return this.kaartAlgoritme.bepaalKaart(
                this.hand, gespeeldeKaarten);
    }

    @Override
    public Kaart verwijderKaartUitHand(Kaart kaart) {
        return this.hand.remove(this.hand.indexOf(kaart));
    }

    // TODO: 2016-04-26: Stratigie aanpassen op basis van de tussenstand.
    @Override
    public void updateRondeTussenstand(HashMap<String, Integer> tussenstand) { }

    // TODO: 2016-04-26: Stratigie aanpassen op basis van de tussenstand.
    @Override
    public void updateSlagTussenstand(HashMap<String, int[]> tussenstand) { }

    @Override
    public ArrayList<Kaart> toonHand() {
        return this.hand;
    }

    @Override
    public void bekijkBord(ArrayList<String> spelerNamen,
                           HashMap<String, Kaart> bord) {
        ArrayList<Kaart> gespeeldeKaarten = new ArrayList<Kaart>();
        for (String spelerNaam : spelerNamen)
            gespeeldeKaarten.add(bord.get(spelerNaam));
        kaartteller.update(gespeeldeKaarten);
        typeteller.update(gespeeldeKaarten);
    }
}
