package nl.han.ica.ap.boerenbridge.speler.computer;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.ISpeler;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * De computerspeler beslist zelf een bod en welke kaarten gespeeld worden aan
 * de hand van het onderliggende algoritme.
 */
public class ComputerSpeler implements ISpeler {

    private String naam;
    private ArrayList<Kaart> hand;
    private ABieding biedingAlgoritme;
    private AKaart kaartAlgoritme;
    private Kaartteller kaartteller;
    private Typeteller typeteller;

    public ComputerSpeler(String naam) {
        this(naam, "willekeurig", "willekeurig");
    }

    public ComputerSpeler(String naam, String bodBepaler, String kaartBepaler)
    {
        this.naam = naam;
        this.hand = new ArrayList<>();
        this.kaartteller = new Kaartteller();
        this.typeteller = new Typeteller(5);

        switch (bodBepaler) {
//            case "montecarlo":
//                this.biedingAlgoritme = new nl.han.ica.ap.boerenbridge.speler.computer.algoritme.montecarlo.BodBepaler(this.kaartteller, this.typeteller);
            default:
                this.biedingAlgoritme = new nl.han.ica.ap.boerenbridge.speler.computer.algoritme.willekeurig.BodBepaler(this.kaartteller, this.typeteller);
        }

        switch (kaartBepaler) {
            case "montecarlo":
                this.kaartAlgoritme = new nl.han.ica.ap.boerenbridge.speler.computer.algoritme.montecarlo.KaartBepaler(this.kaartteller, this.typeteller);
            default:
                this.kaartAlgoritme = new nl.han.ica.ap.boerenbridge.speler.computer.algoritme.willekeurig.KaartBepaler(this.kaartteller, this.typeteller);
        }
    }

    @Override
    public String geefNaam() {
        return this.naam;
    }

    @Override
    public int doeBieding(ArrayList<String> spelerNamen,
                          HashMap<String, Integer> biedingen) {
        return this.biedingAlgoritme.bepaalBieding(
                this.hand, new ArrayList<>());
    }

    @Override
    public void ontvangKaart(Kaart kaart) {
        this.hand.add(kaart);
        this.biedingAlgoritme.resetBodCounter();
    }

    @Override
    public Kaart geefKaart(ArrayList<String> spelers,
                           HashMap<String, Kaart> bord) {
        ArrayList<Kaart> gespeeldeKaarten = new ArrayList<>();
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
        ArrayList<Kaart> gespeeldeKaarten = new ArrayList<>();
        for (String spelerNaam : spelerNamen)
            gespeeldeKaarten.add(bord.get(spelerNaam));
        kaartteller.update(gespeeldeKaarten);
        typeteller.update(gespeeldeKaarten);
    }
}
