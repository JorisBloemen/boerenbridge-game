package nl.han.ica.ap.boerenbridge.speler.computer.algoritme.montecarlo;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.AKaart;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.AlgoritmeHelper;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.Kaartteller;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.Typeteller;

import java.util.ArrayList;
import java.util.List;

public class KaartBepaler extends AKaart {

    public KaartBepaler(Kaartteller kaartteller, Typeteller typeteller) {
        super(kaartteller, typeteller);
    }

    @Override
    public Kaart bepaalKaart(ArrayList<Kaart> hand,
                             ArrayList<Kaart> gespeeldeKaarten) {

        AlgoritmeHelper.bepaalWinnaar(genereerBord(hand, hand.get(0), gespeeldeKaarten));
        return null;
    }

    private List<Kaart> genereerBord(ArrayList<Kaart> hand,
                                     Kaart kaart, List<Kaart> bord) {
        List<Kaart> ongespeeld = this.kaartteller.getOngespeeldeKaarten(hand);
        int nRandomKaarten = 4 - bord.size();

        List<Kaart> random = new ArrayList<>(bord);

        random.add(kaart);
        for (int i = 0; i < nRandomKaarten; i++)
            random.add(ongespeeld.remove((int) (Math.random() * bord.size())));
        return random;
    }
}
