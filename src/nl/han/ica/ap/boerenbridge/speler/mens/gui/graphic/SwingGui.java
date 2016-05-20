package nl.han.ica.ap.boerenbridge.speler.mens.gui.graphic;

import nl.han.ica.ap.boerenbridge.kaart.Kaart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    private Kaart geselecteerdeKaart = null;

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
        HashMap<String, Integer> rondeDummy = new HashMap<String, Integer>();
        for(int i = 1; i < 6; i++){
            rondeDummy.put("" + i, 0);
        }
        updateTussenstand(rondeDummy, 1);
        this.panel1.add(new JScrollPane(this.tussenstand));

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
        this.panel3.add(new JScrollPane(this.slagScore));
        HashMap<String, int[]> slagDummy = new HashMap<String, int[]>();
        for(int i = 1; i < 6; i++){
            slagDummy.put("" + i, new int[2]);
        }
        updateSlagScore(slagDummy, 1);

        //declare panel 4
        this.panel4.setBackground(Color.orange);
        this.panel4.setLayout(new BoxLayout(this.panel4, BoxLayout.LINE_AXIS));

        this.window.getContentPane().add(this.panel1, BorderLayout.LINE_START);
        this.window.getContentPane().add(this.panel2, BorderLayout.CENTER);
        this.window.getContentPane().add(this.panel3, BorderLayout.LINE_END);
        this.window.getContentPane().add(this.panel4, BorderLayout.PAGE_END);


        //this.window.setSize(800, 600);
        // add components
        this.window.pack();
        this.window.setVisible(true);
    }

    public void updateTussenstand(HashMap<String, Integer> tussenstand, int rondenummer) {
        String columnNames[] = {"Naam", "Score"};
        String tussenstandArray[][] = new String[5][2];
        int i = 0;
        for (Map.Entry<String, Integer> e : tussenstand.entrySet()) {
            tussenstandArray[i][0] = e.getKey();
            tussenstandArray[i][1] = e.getValue() + "";
            i++;
        }
        this.panel1.removeAll();
        this.rondeNummer.setText("Stand na ronde " + rondenummer);
        this.panel1.add(rondeNummer);
        this.tussenstand = new JTable(tussenstandArray, columnNames);
        this.panel1.add(new JScrollPane(this.tussenstand));
        this.window.pack();
        this.window.repaint();
    }

    public void updateSlagScore(HashMap<String, int[]> tussenstand, int slagnummer) {
        String columnNames[] = {"Naam", "Bod", "Gewonnen"};
        String tussenstandArray[][] = new String[5][3];
        int i = 0;
        for (Map.Entry<String, int[]> e : tussenstand.entrySet()) {
            tussenstandArray[i][0] = e.getKey();
            tussenstandArray[i][1] = e.getValue()[0] + "";
            tussenstandArray[i][2] = e.getValue()[1] + "";
            i++;
        }
        this.panel3.removeAll();
        this.slag.setText("Slag " + slagnummer);
        this.panel3.add(this.slag);
        this.slagScore = new JTable(tussenstandArray, columnNames);
        this.panel3.add(new JScrollPane(this.slagScore));
        this.window.pack();
        this.window.repaint();
    }

    public void updatePlayedCards(ArrayList<String> spelersNamen,
                                  HashMap<String, Kaart> bord) {
        this.playedCards.removeAll();
        for (String naam : spelersNamen) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.PAGE_AXIS));
            JButton but = new JButton(bord.get(naam).getKaartType() +
            " " + bord.get(naam).getKaartWaarde());
            card.add(but);
            JLabel lab = new JLabel(naam);
            card.add(lab);
            this.playedCards.add(card);
        }
        this.window.pack();
        this.window.repaint();
    }

    public void updateHand(ArrayList<Kaart> hand) {
        this.panel4.removeAll();
        for (Kaart kaart : hand) {
            JButton b = new KaartKnop(this, kaart, false);
            panel4.add(b);
        }
        this.window.pack();
        this.window.repaint();
    }

    private class KaartKnop extends JButton{
        public KaartKnop(SwingGui gui, Kaart kaart, boolean actie) {
            super(kaart.getKaartType() + " " + kaart.getKaartWaarde());

            if(actie){
                this.addActionListener(new KaartActie(gui, kaart));
            }
        }
    }

    private class KaartActie implements ActionListener {
        private SwingGui gui;
        private Kaart kaart;

        public KaartActie(SwingGui gui, Kaart kaart){
            this.gui = gui;
            this.kaart = kaart;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.gui.reactieOpKnop(this.kaart);
        }
    }

    public void reactieOpKnop(Kaart kaart){
        this.geselecteerdeKaart = kaart;
        System.out.println(this.geselecteerdeKaart.getKaartType() + " " +
        this.geselecteerdeKaart.getKaartWaarde());
    }

    public Kaart getGeselecteerdeKaart(ArrayList<Kaart> hand) {
        this.panel4.removeAll();
        for (Kaart kaart : hand) {
            KaartKnop b = new KaartKnop(this, kaart, true);
            panel4.add(b);
        }
        this.window.pack();
        this.window.repaint();
        JOptionPane.showMessageDialog(this.window, "Kies de te spelen kaart");
        while(geselecteerdeKaart == null);
        this.updateHand(hand);
        return geselecteerdeKaart;
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

