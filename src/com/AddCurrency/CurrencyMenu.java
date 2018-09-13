package com.AddCurrency;

import com.Dataload;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CurrencyMenu extends JFrame {
    private static final int textAreaColumns = 4;

    public static double currencyInChaos;
    public static String[] currencyNames = {
            "Exalted Orb", "Chaos Orb", "Vaal Orb", "Orb of Regret",
            "Orb of Alchemy", "Orb of Fusing", "Cartographer's Chisel", "Jeweller's Orb",
    };
    public static String[] shorterCurrencyNames = {
            "Exalts", "Chaos", "Vaals", "Regrets",
            "Alchs", "Fusings", "Chisels", "Jewellers",
    };
    public static JTextField[] enterFields = new JTextField[currencyNames.length];
    public static JLabel[] labelFields = new JLabel[currencyNames.length];

    public static Map<String, Double> currency = new HashMap<>();

    public CurrencyMenu(String name) {
        super(name);
        setTitle("Add Currency");
    }

    public void addComponents(Container pane) {
        JPanel inputFields = new JPanel(new GridBagLayout());
      //  inputFields.setPreferredSize(new Dimension(100  ,100));

        //add currency icons from api as JLabels instead of text

        GridBagConstraints con = new GridBagConstraints();


        int toGY = 0;
        int toGX = 0;
        int maxAcross = 4;
        for (int i = 0; i < enterFields.length; i++) {
            enterFields[i] = new JTextField(textAreaColumns);
            labelFields[i] = new JLabel(shorterCurrencyNames[i]);
            labelFields[i].setToolTipText(currencyNames[i]);

            //JLabels:
            con.gridx = toGX;
            con.gridy = toGY*2;
            con.insets = new Insets(10,8,0,8);

            labelFields[i].setFont(new Font(labelFields[i].getFont().getName(), Font.PLAIN, (int)(labelFields[i].getFont().getSize()*0.8)));
            inputFields.add(labelFields[i], con);

            //JTextFields:
            con.insets = new Insets(0,10,0,10);
            con.gridy = con.gridy*2+1;
            toGX++;
            if (toGX % (maxAcross) == 0 && toGX != 0) {
                toGY++;
                toGX = 0;
            }
            inputFields.add(enterFields[i], con);
        }



        JPanel inputButtons = new JPanel(new GridBagLayout());
        con.insets = new Insets(30,0,0,0);

        JLabel currencyC = new JLabel("Current balance: " + currencyInChaos + "c");
        currencyC.setFont(new Font(currencyC.getFont().getName(), Font.PLAIN, (int)(currencyC.getFont().getSize()*0.9)));
        currencyC.setToolTipText("Current balance in Chaos Orbs");
        con.gridx = 3;
        con.gridy = 1;
        inputButtons.add(currencyC, con);

        con.insets = new Insets(0,0,0,0);
        JButton submit = new JButton("Add Currency");
        con.gridx = 3;
        con.gridy = 2;
        inputButtons.add(submit, con);

        JButton other = new JButton("Other Currency");
        con.gridx = 1;
        con.gridy = 2;
        inputButtons.add(other, con);

        pane.add(inputFields, BorderLayout.NORTH);
        pane.add(inputButtons, BorderLayout.SOUTH);
    }


    public static void init() {
        try {
            currency = Dataload.getCurrencyMap();
        } catch (IOException ex) { ex.printStackTrace(); }

        CurrencyMenu currencyFrame = new CurrencyMenu("Currency Input");
        currencyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currencyFrame.addComponents(currencyFrame.getContentPane());
        currencyFrame.pack();
        currencyFrame.setLocationRelativeTo(null);
        currencyFrame.setVisible(true);

    }





}
