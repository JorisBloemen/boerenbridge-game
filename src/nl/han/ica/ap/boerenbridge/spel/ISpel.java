package nl.han.ica.ap.boerenbridge.spel;

import nl.han.ica.ap.boerenbridge.speler.ISpeler;

/**
 * Interface voor het boerenbridge spel.
 * Een speler moet zich kunnen aanmelden bij het spel.
 */
public interface ISpel {
    /**
     * Voeg een speler toe aan het spel, start het spel bij voldoende spelers.
     * @param speler Deelnemer aan het spel.
     */
    void neemDeel(ISpeler speler);
}
