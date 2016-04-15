package nl.han.ica.ap.boerenbridge.speler.computer.algoritme;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import java.util.ArrayList;

/**
 * Houd bij welke kaarten zijn gespeeld.
 */
public class Kaartteller {
    private ArrayList<Kaart> gespeeldeKaarten;

    /**
     * Initialiseerd de kaartteller.
     */
    public Kaartteller() {
        this.gespeeldeKaarten = new ArrayList<Kaart>();
    }

    /**
     * Update de kaart teller met de nieuw gespeelde kaarten
     * @param bord De gespeelde kaarten. Dit mag ook een gedeelte zijn.
     */
    public void update(ArrayList<Kaart> bord) {
        for (Kaart kaart : bord)
            if (!kaartGespeeld(kaart))
                this.gespeeldeKaarten.add(kaart);
    }

    // TODO: 20160415: Waarom moeten de kaarten gereturned worden, kan deze functionaliteit niet in de kaartteller?
    /**
     * Geeft alle gespeelde kaarten terug.
     * @return Alle gespeelde kaarten.
     */
    public ArrayList<Kaart> getKaarten() {
        return this.gespeeldeKaarten;
    }

    /**
     * Controleerd of een kaart gespeeld is.
     * @param kaart De kaart die gecontroleerd dient te worden.
     * @return De aanwezigheid van de kaart.
     */
    public boolean kaartGespeeld(Kaart kaart) {
        return this.gespeeldeKaarten.contains(kaart);
    }

    // TODO: 20160415: Aantal van bepaald type moet opgevraagd kunnen worden?
    // TODO: 20160415: Mogelijk winde kaarten terug geven boven de gegeven kaart.
}
