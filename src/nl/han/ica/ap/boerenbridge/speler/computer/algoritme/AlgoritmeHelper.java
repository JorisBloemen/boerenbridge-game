package nl.han.ica.ap.boerenbridge.speler.computer.algoritme;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import java.util.List;

import static nl.han.ica.ap.boerenbridge.kaart.KaartType.SCHOPPEN;

public class AlgoritmeHelper {

    /**
     * Bepaald welke speler de slag gewonnen heeft.
     * @param bord Het bord met de gespeelde kaarten.
     * @return De speler die de beste kaart opgegooid heeft.
     */
    static public int bepaalWinnaar(List<Kaart> bord) {
        Kaart besteKaart = bord.get(0);

        for (Kaart kaart : bord)
            besteKaart = bepaalBesteKaart(kaart, besteKaart);

        return bord.indexOf(besteKaart);
    }

    /**
     * Vergelijk twee kaarten.
     * @param nieuweKaart De kaart die je wilt vergelijken met huidige beste
     *                    kaart.
     * @param besteKaart De huidige beste kaart (kaart die kaarttype bepaald).
     * @return De de beste kaart, of de nieuwe kaart indien hoger dan de beste
     *         kaart.
     */
    static private Kaart bepaalBesteKaart(Kaart nieuweKaart, Kaart besteKaart) {
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
