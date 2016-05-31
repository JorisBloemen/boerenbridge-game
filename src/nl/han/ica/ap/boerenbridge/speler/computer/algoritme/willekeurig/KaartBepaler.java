package nl.han.ica.ap.boerenbridge.speler.computer.algoritme.willekeurig;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;
import nl.han.ica.ap.boerenbridge.kaart.KaartType;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.AKaart;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.Kaartteller;
import nl.han.ica.ap.boerenbridge.speler.computer.algoritme.Typeteller;

import java.util.ArrayList;

/**
 * Het algoritme dat een willekeurige geldige kaart bepaald.
 */
public class KaartBepaler extends AKaart {

    public KaartBepaler(Kaartteller kaartteller, Typeteller typeteller) {
        super(kaartteller, typeteller);
    }

    /**
     * Kiest een kaart die aan de voorwaarden van het spel voldoet.
     * @param hand De kaarten die de speler in zijn hand heeft.
     * @param gespeeldeKaarten De kaarten die door andere spelers zijn
     *                         opgegooid.
     * @return De meest random kaart die aan de spelregels voldoet.
     */
    @Override
    public Kaart bepaalKaart(
            ArrayList<Kaart> hand, ArrayList<Kaart> gespeeldeKaarten,
            int nogTeWinnen) {
        if (gespeeldeKaarten.size() == 0)
            return getWillekeurigeKaart(hand);

        KaartType teBekennenType = getTeBekennenKaartType(gespeeldeKaarten);
        ArrayList<Kaart> legitimeKaarten = getLegitimeKaarten(
                teBekennenType, hand);

        Kaart teSpelenKaart = getWillekeurigeKaart(legitimeKaarten);
        if (teSpelenKaart != null)
            return teSpelenKaart;

        return getWillekeurigeKaart(hand);
    }

    /**
     * Haalt het te bekennen kaart type uit de lijst gespeelde kaarten.
     * @param gespeeldeKaarten De tot nu toe opgegooide kaarten.
     * @return Het kaarttype dat bekend dient te worden.
     */
    private KaartType getTeBekennenKaartType(ArrayList<Kaart> gespeeldeKaarten)
    {
        if (gespeeldeKaarten.isEmpty())
            return null;
        return gespeeldeKaarten.get(0).getKaartType();
    }

    /**
     * Haalt de kaarten die opgegooit mogen worden uit de gegeven set.
     * @param teBekennenType Het type kaart dat bekend dient te worden.
     * @param hand De kaarten uit de hand van de speler.
     * @return Een sub-set van de gegeven lijst die aan het te bekennen type
     *         voldoet.
     */
    private ArrayList<Kaart> getLegitimeKaarten(KaartType teBekennenType,
                                                ArrayList<Kaart> hand)
    {
        ArrayList<Kaart> legitimeKaarten = new ArrayList<>();
        for (Kaart kaart : hand)
            if (kaart.getKaartType() == teBekennenType)
                legitimeKaarten.add(kaart);
        return legitimeKaarten;
    }

    /**
     * Zoekt een willikeurige kaart uit de gegeven set.
     * @param kaarten De set kaarten waar een kaart uit moet worden getrokken.
     * @return Een willekeurige kaart uit de gegeven set.
     */
    private Kaart getWillekeurigeKaart(ArrayList<Kaart> kaarten)
    {
        if (kaarten.size() == 0)
            return null;
        return kaarten.get((int) (Math.random() * kaarten.size()));
    }
}
