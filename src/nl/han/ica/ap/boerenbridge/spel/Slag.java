package nl.han.ica.ap.boerenbridge.spel;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.ISpeler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static nl.han.ica.ap.boerenbridge.kaart.KaartType.SCHOPPEN;

/**
 * Vraagt spelers om kaarten en bepaald de winnaar van de slag.
 */
class Slag {
    private ArrayList<ISpeler> spelers;
    private HashMap<ISpeler, Kaart> gespeeldeKaarten;
    private Kaart eersteKaart;

    /**
     * Initialiseert de slag, spelers dienen te worden aangeleverd in de speel
     * volgorde.
     * @param spelers De deelnemende spelers aan de slag met de beginnende
     *                speler op de eerste plek.
     */
    Slag(ArrayList<ISpeler> spelers){
        this.spelers = spelers;
        this.gespeeldeKaarten = new HashMap<ISpeler, Kaart>();
    }

    /**
     * Speel de slag. Vraag kaarten van de spelers en bepaal de winnaar.
     * @return De speler met de beste kaart.
     */
    ISpeler speelSlag() {
        vraagKaarten();
        toonBordAanSpelers();
        return bepaalWinnaar();
    }

    /**
     * Laat de spelers het bord zien.
     */
    private void toonBordAanSpelers() {
        HashMap<String, Kaart> bord = new HashMap<String, Kaart>();
        for (Map.Entry<ISpeler, Kaart> entry : this.gespeeldeKaarten.entrySet())
            bord.put(entry.getKey().geefNaam(), entry.getValue());
        for (ISpeler speler : this.spelers)
            speler.bekijkBord(geefSpelerNamen(),
                    new HashMap<String, Kaart>(bord));
    }

    /**
     * Geeft de namen van de spelers terug.
     * @return De namen van de spelers.
     */
    private ArrayList<String> geefSpelerNamen() {
        ArrayList<String> spelerNamen = new ArrayList<String>();
        for (ISpeler speler : this.spelers)
            spelerNamen.add(speler.geefNaam());
        return spelerNamen;
    }

    private ArrayList<String> geefSpelerNamenDieGeweestZijn() {
        ArrayList<String> spelerNamen = new ArrayList<String>();
        for (ISpeler speler : this.spelers)
            if (this.gespeeldeKaarten.containsKey(speler))
                spelerNamen.add(speler.geefNaam());
        return spelerNamen;
    }

    private ArrayList<ISpeler> geefSpelersDieGeweestZijn() {
        ArrayList<ISpeler> spelers = new ArrayList<ISpeler>();
        for (ISpeler speler : this.spelers)
            if (this.gespeeldeKaarten.containsKey(speler))
                spelers.add(speler);
        return spelers;
    }

    private HashMap<String, Kaart> geefGespeeldeKaartenOpBasisVanNaam() {
        HashMap<String, Kaart> huidigeBord = new HashMap<String, Kaart>();
        for (ISpeler speler : geefSpelersDieGeweestZijn())
            huidigeBord.put(speler.geefNaam(),
                    this.gespeeldeKaarten.get(speler));
        return huidigeBord;
    }

    // TODO: 2016-11-4: nog testen
    // TODO: De speler moet weten of het spel de kaart accepteerd voordat hij hem uit zijn hand verwijderd.

    /**
     * Vraag alle spelers een kaart op te gooien en bepaald het te bekennen
     * kaarttype van de slag.
     */
    private void vraagKaarten() {
        for (ISpeler speler : this.spelers) {
            Kaart kaart = vraagValideKaart(speler);
            this.gespeeldeKaarten.put(speler, kaart);
            if (speler == this.spelers.get(0))
                this.eersteKaart = kaart;
        }
    }

    /**
     * Vraag een valide kaart aan de speler.
     * @param speler De speler aan wie de kaart gevraagd moet worden.
     * @return De valide tespeelde kaart.
     */
    private Kaart vraagValideKaart(ISpeler speler) {
        Kaart kaart = null;
        ArrayList<Kaart> hand = speler.toonHand();
        do {
            kaart = speler.geefKaart(
                    geefSpelerNamenDieGeweestZijn(),
                    geefGespeeldeKaartenOpBasisVanNaam());
            if(speler == this.spelers.get(0))
                return speler.verwijderKaartUitHand(kaart);
        } while (!kaartVoldoetAanBediening(hand, kaart));
        return speler.verwijderKaartUitHand(kaart);
    }

    private Boolean kaartVoldoetAanBediening(ArrayList<Kaart> hand, Kaart kaart){
        if (kaart.getKaartType() == this.eersteKaart.getKaartType())
            return true;
        for (Kaart kaartInHand : hand)
            if (kaartInHand.getKaartType() == this.eersteKaart.getKaartType())
                return false;
        return true;
    }

    /**
     * Bepaald welke speler de slag gewonnen heeft.
     * @return De speler die de beste kaart opgegooid heeft.
     */
    private ISpeler bepaalWinnaar() {
        Kaart besteKaart = this.eersteKaart;

        for (Kaart kaart : gespeeldeKaarten.values()) {
            besteKaart = bepaalBesteKaart(kaart, besteKaart);
        }

        for (Map.Entry<ISpeler, Kaart> entry
                : this.gespeeldeKaarten.entrySet()) {
            if (entry.getValue() == besteKaart)
                return entry.getKey();
        }
        return null;
    }

    /**
     * Vergelijk twee kaarten.
     * @param nieuweKaart De kaart die je wilt vergelijken met huidige beste
     *                    kaart.
     * @param besteKaart De huidige beste kaart (kaart die kaarttype bepaald).
     * @return De de beste kaart, of de nieuwe kaart indien hoger dan de beste
     *         kaart.
     */
    private Kaart bepaalBesteKaart(Kaart nieuweKaart, Kaart besteKaart) {
        if (nieuweKaart.getKaartType() == SCHOPPEN) {
            if (besteKaart.getKaartType() == SCHOPPEN) {
                if (nieuweKaart.getKaartWaarde().ordinal()
                        > besteKaart.getKaartWaarde().ordinal())
                    return nieuweKaart;
            }
            else
                return nieuweKaart;
        } else if (nieuweKaart.getKaartType() == besteKaart.getKaartType()) {
            if (nieuweKaart.getKaartWaarde().ordinal()
                    > besteKaart.getKaartWaarde().ordinal())
                return nieuweKaart;
        }
        return besteKaart;
    }
}
