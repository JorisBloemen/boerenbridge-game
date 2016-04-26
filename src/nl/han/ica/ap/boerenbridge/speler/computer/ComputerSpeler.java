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
    public int doeBieding() {
        return this.biedingAlgoritme.bepaalBieding(
                this.hand, new ArrayList<Integer>());
    }

    @Override
    public void ontvangKaart(Kaart kaart) {
        this.hand.add(kaart);
    }

    @Override
    public Kaart geefKaart(ArrayList<Kaart> bord) {
        kaartteller.update(bord);
        return this.kaartAlgoritme.bepaalKaart(
                this.hand, bord);
    }

    // TODO: 2016-04-26: Nog implementeren, andere strategie op basis van voor of achterstand?
    @Override
    public void updateScore(int score) { }

    @Override
    public ArrayList<Kaart> toonHand() {
        return this.hand;
    }

    @Override
    public void bekijkBord(ArrayList<Kaart> bord) {
        kaartteller.update(bord);
        typeteller.update(bord);
    }
}
