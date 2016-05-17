package nl.han.ica.ap.boerenbridge.speler.mens.gui.graphic;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SwingGui {

    private JFrame window;
    private JLabel rondeNummer;
    private JTable tussenstand;
    private JPanel playedCards;
    private JLabel slag;
    private JTable slagScore;
    private JPanel panel1, panel2, panel3, panel4;

    public SwingGui() {
        // create the frame
        this.window = new JFrame("Boeren Bridge");

        // set close action
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // add background
        this.panel1 = new JPanel();
        this.panel2 = new JPanel();
        this.panel3 = new JPanel();
        this.panel4 = new JPanel();

        //declare panel 1
        this.panel1.setBackground(Color.red);
        this.panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        this.rondeNummer = new JLabel();
        this.panel1.add(this.rondeNummer);
        this.tussenstand = new JTable();
        this.panel1.add(this.tussenstand);

        //declare panel 2
        this.panel2.setBackground(Color.green);
        this.playedCards = new JPanel();
        this.playedCards.setLayout(new BoxLayout(playedCards,
                BoxLayout.LINE_AXIS));
        this.panel2.add(this.playedCards);

        //declare panel 3
        this.panel3.setBackground(Color.blue);
        this.panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
        this.slag = new JLabel();
        this.panel3.add(this.slag);
        this.slagScore = new JTable();
        this.panel3.add(this.slagScore);

        //declare panel 4
        this.panel4.setBackground(Color.orange);
        this.panel4.setLayout(new BoxLayout(this.panel4, BoxLayout.LINE_AXIS));

        this.window.getContentPane().add(this.panel1, BorderLayout.LINE_START);
        this.window.getContentPane().add(this.panel2, BorderLayout.CENTER);
        this.window.getContentPane().add(this.panel3, BorderLayout.LINE_END);
        this.window.getContentPane().add(this.panel4, BorderLayout.PAGE_END);

        // add components
        this.window.pack();
        this.window.setVisible(true);
    }

    public void updateTussenstand(HashMap<String, Integer> tussenstand,
                                  int rondenummer) {
        String columnNames[] = {"Naam", "Score"};
        String tussenstandArray[][] = new String[5][2];
        int i = 0;
        for (Map.Entry<String, Integer> e : tussenstand.entrySet()) {
            tussenstandArray[i][0] = e.getKey();
            tussenstandArray[i][1] = e.getValue() + "";
            i++;
        }
        this.tussenstand = new JTable(tussenstandArray, columnNames);
        this.rondeNummer.setText("Ronde " + rondenummer);
        this.window.repaint();
    }

    public void updateSlagScore(HashMap<String, int[]> tussenstand,
                                int slagnummer) {
        String columnNames[] = {"Naam", "Bod", "Gewonnen"};
        String tussenstandArray[][] = new String[5][3];
        int i = 0;
        for (Map.Entry<String, int[]> e : tussenstand.entrySet()) {
            tussenstandArray[i][0] = e.getKey();
            tussenstandArray[i][1] = e.getValue()[0] + "";
            tussenstandArray[i][2] = e.getValue()[1] + "";
            i++;
        }
        this.slagScore = new JTable(tussenstandArray, columnNames);
        this.slag.setText("Slag " + slagnummer);
        this.window.repaint();
    }

    public void updatePlayedCards(ArrayList<String> spelersNamen,
                                  HashMap<String, Kaart> bord) {
        this.playedCards.removeAll();
        for (String naam : spelersNamen) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.PAGE_AXIS));
            JButton but = new JButton(bord.get(naam).toString());
            card.add(but);
            JLabel lab = new JLabel(naam);
            card.add(lab);
            this.playedCards.add(card);
        }
        this.window.repaint();
    }

    public void updateHand(ArrayList<Kaart> hand) {
        this.panel4.removeAll();
        for (Kaart kaart : hand) {
            JButton b = new JButton(kaart.toString());
            panel4.add(b);
        }
        this.window.repaint();
    }

    public int doeEenBod(){
        Object[] possibilities = {0,1,2,3,4,5,6,7,8,9,10};
        return (Integer)JOptionPane.showInputDialog(
                this.window,
                "Doe een bod:\n",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                possibilities,
                0);
    }
}

