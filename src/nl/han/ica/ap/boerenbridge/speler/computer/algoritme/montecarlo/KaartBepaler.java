package nl.han.ica.ap.boerenbridge.speler.computer.algoritme.montecarlo;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.AKaart;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.AlgoritmeHelper;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.Kaartteller;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.Typeteller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KaartBepaler extends AKaart {

    final private static int AANTAL_KEER_PER_KAART = 100000;

    public KaartBepaler(Kaartteller kaartteller, Typeteller typeteller) {
        super(kaartteller, typeteller);
    }

    @Override
    public Kaart bepaalKaart(ArrayList<Kaart> hand,
                             ArrayList<Kaart> gespeeldeKaarten) {
        List<Kaart> ongespeeldeKaarten = this.kaartteller.getOngespeeldeKaarten(hand);

        List<Kaart> toegestaneKaarten = AlgoritmeHelper.toegestaneKaarten(hand, gespeeldeKaarten);
        int aantalGewonnen[] = new int[toegestaneKaarten.size()];

        // Voor elke kaart in de toegestaneKaarten tellen hoevaak deze wint.
        for (int i = 0; i < toegestaneKaarten.size(); i++) {
            Kaart kaart = toegestaneKaarten.get(i);
            for (int j = 0; j < AANTAL_KEER_PER_KAART; j++) {
                List<Kaart> mogelijkBord = genereerBord(kaart,
                                                        gespeeldeKaarten,
                                                        ongespeeldeKaarten);
                if (AlgoritmeHelper.bepaalWinnaar(mogelijkBord, kaart))
                    aantalGewonnen[i]++;
            }
        }

        // Bepaal de beste kaart aan de hand van het aatal keer gewonnen.
        int iBesteKaart = 0;
        for (int i = 0; i < aantalGewonnen.length; i++)
            if (aantalGewonnen[i] >= aantalGewonnen[iBesteKaart])
                iBesteKaart = i;

        return toegestaneKaarten.get(iBesteKaart);
    }

    /**
     * Vult een incompleet bord met de gegeven kaart en vult hierbij
     * willekeurige kaarten voor de overige tegenstander is.
     * @param teSpelenKaart De kaart die gespeeld dient te worden.
     * @param huidigBord Het huidige bord.
     * @param ongespeeldeKaarten De kaarten die nog niet uit het spel zijn.
     * @return Een mogelijk eindsituatie van een slag.
     */
    private List<Kaart> genereerBord(Kaart teSpelenKaart,
                                     List<Kaart> huidigBord,
                                     List<Kaart> ongespeeldeKaarten) {
        ongespeeldeKaarten = new ArrayList<>(ongespeeldeKaarten);
        List<Kaart> mogelijkBord = new ArrayList<>(huidigBord);
        mogelijkBord.add(teSpelenKaart);

        int nRandomKaarten = 4 - huidigBord.size();

        for (int i = 0; i < nRandomKaarten; i++) {
                int rIndex = (int) (Math.random() * ongespeeldeKaarten.size());
                mogelijkBord.add(ongespeeldeKaarten.remove(rIndex));
        }

        return mogelijkBord;
    }
}
