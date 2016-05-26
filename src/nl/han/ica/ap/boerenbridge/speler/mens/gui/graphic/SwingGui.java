package nl.han.ica.ap.boerenbridge.speler.mens.gui.graphic;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static javax.swing.SwingConstants.CENTER;

class SwingGui {

    private Color bordKleur;
    private JFrame window;
    private JLabel rondeNummer;
    private Color labelColor;
    private Border border;
    private JTable tussenstand;
    private JPanel playedCards;
    private JLabel slag;
    private JTable slagScore;
    private JPanel panel1, panel2, panel3, panel4, panel5;

    private Kaart geselecteerdeKaart;
    private boolean wachtOpGebruiker;

    /**
     * Initialiseerd de gui, tekend het initiele scherm.
     */
    SwingGui() {
        this.geselecteerdeKaart = null;
        this.bordKleur = new Color(0, 77, 0);
        this.labelColor = Color.white;
        this.wachtOpGebruiker = false;
        this.border = BorderFactory.createMatteBorder(0, 0, 0, 0, this.bordKleur);

        // create the frame
        this.window = new JFrame("Boeren Bridge");

        // set close action
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // add background
        this.panel1 = new JPanel();
        this.panel2 = new JPanel();
        this.panel3 = new JPanel();
        this.panel4 = new JPanel();
        this.panel5 = new JPanel();

        //declare panel 1
        this.panel1.removeAll();
        this.panel1.setBackground(this.bordKleur);
        this.panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        this.rondeNummer = new JLabel();
        this.tussenstand = new JTable();
        HashMap<String, Integer> rondeDummy = new HashMap<String, Integer>();
        for(int i = 1; i < 6; i++){
            rondeDummy.put("" + i, 0);
        }
        updateTussenstand(rondeDummy, 1);

        //declare panel 2
        this.panel2.setBackground(this.bordKleur);
        this.playedCards = new JPanel();
        this.playedCards.setLayout(new BoxLayout(playedCards,
                BoxLayout.LINE_AXIS));
        this.panel2.add(this.playedCards);

        //declare panel 3
        this.panel3.setBackground(this.bordKleur);
        this.panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
        this.slag = new JLabel();
        this.panel3.add(this.slag);
        this.slagScore = new JTable();
        HashMap<String, int[]> slagDummy = new HashMap<String, int[]>();
        for(int i = 1; i < 6; i++){
            slagDummy.put("" + i, new int[2]);
        }
        updateSlagScore(slagDummy);

        //declare panel 4
        this.panel4.setBackground(this.bordKleur);
        this.panel4.setLayout(new BoxLayout(this.panel4, BoxLayout.LINE_AXIS));

        //declare panel 5
        this.panel5.setBackground(this.bordKleur);
        this.panel5.setPreferredSize(new Dimension(1,35));

        this.window.getContentPane().add(this.panel1, BorderLayout.LINE_START);
        this.window.getContentPane().add(this.panel2, BorderLayout.CENTER);
        this.window.getContentPane().add(this.panel3, BorderLayout.LINE_END);
        this.window.getContentPane().add(this.panel4, BorderLayout.PAGE_END);
        this.window.getContentPane().add(this.panel5, BorderLayout.PAGE_START);

        this.window.setPreferredSize(new Dimension(1280, 720));
        // add components
        //this.window.pack();
        this.window.repaint();
        this.window.setVisible(true);
    }

    /**
     * Update de tussenstand score.
     * @param tussenstand De score per speler.
     * @param rondenummer De ronde waarin deze tussenstand gelt.
     */
    void updateTussenstand(HashMap<String, Integer> tussenstand, int rondenummer) {
        String columnNames[] = {"Naam", "Score"};
        String tussenstandArray[][] = new String[5][2];
        int i = 0;
        for (Map.Entry<String, Integer> e : tussenstand.entrySet()) {
            tussenstandArray[i][0] = e.getKey();
            tussenstandArray[i][1] = e.getValue() + "";
            i++;
        }
        this.panel1.removeAll();
        this.panel1.setBorder(BorderFactory.createEmptyBorder());
        this.rondeNummer.setText("Stand na ronde " + rondenummer);
        this.rondeNummer.setForeground(this.labelColor);
        this.panel1.add(rondeNummer);
        this.tussenstand = new JTable(tussenstandArray, columnNames);
        JScrollPane helper = new JScrollPane(this.tussenstand);
        helper.getViewport().setBackground(this.bordKleur);
        helper.setBorder(this.border);
        helper.setViewportBorder(this.border);
        this.tussenstand.setBorder(this.border);
        helper.setPreferredSize(new Dimension(150, -280));
        this.panel1.add(new JScrollPane(helper));
        this.panel1.add(Box.createVerticalGlue());
        this.window.pack();
        this.window.repaint();
    }

    /**
     * Update de tussenstand van de slag.
     * @param tussenstand Het bod en tot nu toe gewonnen slagen per speler.
     */
    void updateSlagScore(HashMap<String, int[]> tussenstand) {
        String columnNames[] = {"Naam", "Bod", "Gewonnen"};
        String tussenstandArray[][] = new String[5][3];
        int i = 0;
        int slagnummer = 0;
        for (Map.Entry<String, int[]> e : tussenstand.entrySet()) {
            tussenstandArray[i][0] = e.getKey();
            tussenstandArray[i][1] = e.getValue()[0] + "";
            tussenstandArray[i][2] = e.getValue()[1] + "";
            slagnummer += e.getValue()[1];
            i++;
        }
        this.panel3.removeAll();
        this.panel3.setBorder(BorderFactory.createEmptyBorder());
        this.slag.setText("Score na slag " + slagnummer);
        this.slag.setForeground(this.labelColor);
        this.panel3.add(this.slag);
        this.slagScore = new JTable(tussenstandArray, columnNames);
        JScrollPane helper2 = new JScrollPane(this.slagScore);
        helper2.getViewport().setBackground(this.bordKleur);
        helper2.setBorder(this.border);
        helper2.setViewportBorder(this.border);
        this.slagScore.setBorder(this.border);
        helper2.setPreferredSize(new Dimension(200,-280));
        this.panel3.add(new JScrollPane(helper2));
        this.panel3.add(Box.createVerticalGlue());
        this.window.pack();
        this.window.repaint();
    }

    /**
     * Callback voor wacht op gebruiker knop (volgende slag).
     */
    private void setWachtOpGebruiker() {
        this.panel5.removeAll();
        this.panel5.setPreferredSize(new Dimension(1,35));
        this.window.pack();
        this.window.repaint();
        this.wachtOpGebruiker = false;
    }

    /**
     * Maakt een knop die wacht tot de gebruiker de knop in drukt.
     */
    void wachtOpGebruiker(String msg) {
        this.wachtOpGebruiker = true;

        this.panel5.removeAll();
        this.panel5.add(Box.createHorizontalGlue());
        JButton volgedeSlag = new JButton(msg);
        volgedeSlag.addActionListener(e -> setWachtOpGebruiker());
        this.panel5.add(volgedeSlag);
        this.panel5.add(Box.createHorizontalGlue());
        this.window.pack();
        this.window.repaint();
        while (this.wachtOpGebruiker) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Update het bord met kaarten die door andere spelers zijn opgegooit.
     * @param spelersNamen De namen van de andere spelers (op volgorde van opgooien).
     * @param bord De kaarten die de spelers hebben opgegooit.
     */
    void updatePlayedCards(ArrayList<String> spelersNamen,
                                  HashMap<String, Kaart> bord) {
        this.playedCards.removeAll();
        for (String naam : spelersNamen) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.PAGE_AXIS));
            card.add(new KaartKnop(bord.get(naam)));
            JLabel lab = new JLabel(naam);
            lab.setForeground(this.labelColor);
            card.add(lab);
            card.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            card.setBackground(this.bordKleur);
            this.playedCards.add(card);
        }
        this.window.pack();
        this.window.repaint();
    }

    /**
     * Repaint de hand.
     * @param hand Kaarten die een speler in de hand heeft.
     */
    void updateHand(ArrayList<Kaart> hand) {
        this.panel4.removeAll();
        this.panel4.add(Box.createHorizontalGlue());
        for (Kaart kaart : hand) {
            JButton b = new KaartKnop(kaart);
            panel4.add(b);
            this.panel4.add(Box.createHorizontalGlue());
        }
        this.window.pack();
        this.window.repaint();
    }

    /**
     * Klasse om een knop aan te maken voor een kaart.
     */
    private class KaartKnop extends JButton {
        KaartKnop(Kaart kaart) {
            super();
            String path = "/resources/Kaart/" + kaart.toString() + ".png";
            try {
                Image img = ImageIO.read(getClass().getResource(path));
                img = img.getScaledInstance(100, 150, Image.SCALE_DEFAULT);
                this.setIcon(new ImageIcon(img));
                this.setPreferredSize(new Dimension(100, 150));
                this.setBorder(BorderFactory.createEmptyBorder());
                this.setBackground(bordKleur);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        KaartKnop(Kaart kaart, ActionListener actionListener) {
            this(kaart);
            this.addActionListener(actionListener);
        }
    }

    /**
     * Verranderd de waarde van geslecteerde kaart naar de kaart waar de
     * gebruiker op gedrukt heeft.
     * @param kaart De kaart waarop de gebruiker heeft geklikt.
     */
    private void reactieOpKnop(Kaart kaart, ArrayList<Kaart> hand){
        this.updateHand(hand);
        this.geselecteerdeKaart = kaart;
    }

    /**
     * Vraag aan de gebruiker welke kaart hij op wil gooien.
     * @param hand De huidige hand van de speler.
     * @return De kaart die de gebruiker heeft aangeklikt.
     */
    Kaart getGeselecteerdeKaart(ArrayList<Kaart> hand) {
        this.geselecteerdeKaart = null;
        this.panel4.removeAll();
        this.panel4.add(Box.createHorizontalGlue());
        for (Kaart kaart : hand) {
            KaartKnop b = new KaartKnop(kaart, e -> reactieOpKnop(kaart, hand));
            panel4.add(b);
            this.panel4.add(Box.createHorizontalGlue());
        }
        this.window.pack();
        this.window.repaint();
        while(this.geselecteerdeKaart == null) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.geselecteerdeKaart;
    }

    /**
     * Vraag de speler een bod te doen.
     * @return Het bod van de speler.
     */
    int doeEenBod(ArrayList<String> spelerNamen, HashMap<String, Integer> biedingen, int maxBod){
        int antiBod = maxBod;
        if (spelerNamen.size() == 4)
            for (Integer bod : biedingen.values())
                antiBod -= bod;
        ArrayList<Integer> possibilities = new ArrayList<Integer>();
        for (int i = 0; i <= maxBod; i++)
            if (spelerNamen.size() != 4 || i != antiBod)
                possibilities.add(i);
        String message = "Voorgaande biedingen\n";
        for (String spelerNaam : spelerNamen)
            message = message + spelerNaam + ": " + biedingen.get(spelerNaam) + "\n";
        message = message + "Doe een bieding\n";
        return possibilities.get(JOptionPane.showOptionDialog(
                this.window,
                message,
                "Doe een bieding",
                JOptionPane.NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                possibilities.toArray(),
                0));
    }
}

