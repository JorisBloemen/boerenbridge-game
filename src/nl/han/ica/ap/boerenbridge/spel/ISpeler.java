package nl.han.ica.ap.boerenbridge.spel;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Interface voor spelers van het spel.
 */
public interface ISpeler {
    int doeBieding();
    void ontvangKaart(Kaart kaart);
    Kaart geefKaart(ArrayList<Kaart> kaarten);
    // TODO: 2016-03-22: Speler moet de hand terug geven zodat het spel kan controleren of het een valide kaart is.
    // ArrayList<Kaart> toonHand();
    // TODO: 2016-03-22: Speler kan nu alleen zijn eigen score zien, niet die van zijn medespelers.
    void updateScore(int score);
}
