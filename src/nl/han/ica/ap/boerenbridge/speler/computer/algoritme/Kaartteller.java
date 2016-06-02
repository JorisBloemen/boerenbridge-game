package nl.han.ica.ap.boerenbridge.speler.computer.algoritme;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.kaart.KaartType;
import nl.han.ica.ap.boerenbridge.kaart.KaartWaarde;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Houd bij welke kaarten zijn gespeeld.
 */
public class Kaartteller {
    private List<Kaart> gespeeldeKaarten, ongespeeldeKaarten;

    /**
     * Initialiseerd de kaartteller.
     */
    public Kaartteller() {
        this.gespeeldeKaarten = new ArrayList<>();
        this.ongespeeldeKaarten = new ArrayList<>();
        resetKaarten();
    }

    /**
     * Update de kaart teller met de nieuw gespeelde kaarten
     * @param bord De gespeelde kaarten. Dit mag ook een gedeelte zijn.
     */
    public void update(ArrayList<Kaart> bord) {
        for (Kaart kaart : bord)
            if (!kaartGespeeld(kaart)) {
                this.gespeeldeKaarten.add(kaart);
                Optional<Kaart> kaartOptional = this.ongespeeldeKaarten
                        .stream().filter(k -> k.compareTo(kaart)).findFirst();
                if (kaartOptional.isPresent())
                    this.ongespeeldeKaarten.remove(kaartOptional.get());
            }
    }

    // TODO: 20160415: Waarom moeten de kaarten gereturned worden, kan deze functionaliteit niet in de kaartteller?
    /**
     * Geeft alle gespeelde kaarten terug.
     * @return Alle gespeelde kaarten.
     */
    public List<Kaart> getGespeeldeKaartenKaarten() {
        return new ArrayList<>(this.gespeeldeKaarten);
    }

    /**
     * Geeft alle kaarten die nog niet gespeeld zijn en niet in de hand zitten.
     * @param hand De kaarten in de hand van de speler.
     * @return Geeft alle kaarten die nog niet gespeeld zijn minus de hand van
     *         de speler. De kaarten die terug gegeven worden zijn niet dezelfde
     *         objecten als de speler in de hand heeft en dienen vergeleken te
     *         worden met de compareTo functie.
     */
    public List<Kaart> getOngespeeldeKaarten(List<Kaart> hand) {
        List<Kaart> ongespeeldeKaarten = new ArrayList<>(this.ongespeeldeKaarten);
        for (Kaart kaart : hand)
            ongespeeldeKaarten = ongespeeldeKaarten.stream()
                    .filter(k -> !k.compareTo(kaart))
                    .collect(Collectors.toList());
        return ongespeeldeKaarten;
    }

    /**
     * Maak de hele set kaarten opnieuw aan.
     */
    public void resetKaarten() {
        this.ongespeeldeKaarten.clear();
        for (KaartType type : KaartType.values())
            for (KaartWaarde waarde : KaartWaarde.values())
                this.ongespeeldeKaarten.add(new Kaart(type, waarde));
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
