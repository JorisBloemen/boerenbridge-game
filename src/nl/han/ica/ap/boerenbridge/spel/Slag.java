package nl.han.ica.ap.boerenbridge.spel;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.kaart.KaartType;
import static nl.han.ica.ap.boerenbridge.kaart.KaartType.*;
import static nl.han.ica.ap.boerenbridge.kaart.KaartWaarde.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Vraagt spelers om kaarten en bepaald de winnaar van de slag.
 */
public class Slag {
    private ArrayList<ISpeler> spelers;
    private HashMap<ISpeler, Kaart> gespeeldeKaarten;
    private Kaart eersteKaart;

    /**
     * Initialiseert de slag, spelers dienen te worden aangeleverd in de speel
     * volgorde.
     * @param spelers De deelnemende spelers aan de slag met de beginnende
     *                speler op de eerste plek.
     */
    public Slag(ArrayList<ISpeler> spelers){
        this.spelers = spelers;
        this.gespeeldeKaarten = new HashMap<ISpeler, Kaart>();
    }

    /**
     * Speel de slag. Vraag kaarten van de spelers en bepaal de winnaar.
     * @return De speler met de beste kaart.
     */
    public ISpeler speelSlag() {
        vraagKaarten();
        return bepaalWinnaar();
    }

    // TODO: 2016-03-22: Kaart valideren. Singleton voor validatie?

    /**
     * Vraag alle spelers een kaart op te gooien en bepaald het te bekennen
     * kaarttype van de slag.
     */
    private void vraagKaarten() {
        for (ISpeler speler : this.spelers) {
            System.out.println(speler.geefNaam() + ":");
            Kaart kaart = speler.geefKaart(
                    new ArrayList<Kaart>(this.gespeeldeKaarten.values()));
            this.gespeeldeKaarten.put(speler, kaart);
            if (speler == this.spelers.get(0))
                this.eersteKaart = kaart;
        }
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
