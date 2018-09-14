package com;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyMenu extends JFrame {
    private static final int textAreaColumns = 4;

    public static double currencyInChaos;
    public static JLabel currencyC;

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
            enterFields[i].setToolTipText(currencyNames[i]);
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

            //straight from StackOverflow hehehhehehe
            AbstractDocument document = (AbstractDocument) enterFields[i].getDocument();
            final int maxCharacters = 5;
            document.setDocumentFilter(new DocumentFilter() {
                public void replace(FilterBypass fb, int offs, int length,
                                    String str, AttributeSet a) throws BadLocationException {

                    String text = fb.getDocument().getText(0,
                            fb.getDocument().getLength());
                    text += str;
                    if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters
                            && text.matches("^[0-9]+[.]?[0-9]{0,1}$")) {
                        super.replace(fb, offs, length, str, a);
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                    }
                }

                public void insertString(FilterBypass fb, int offs, String str,
                                         AttributeSet a) throws BadLocationException {

                    String text = fb.getDocument().getText(0,
                            fb.getDocument().getLength());
                    text += str;
                    if ((fb.getDocument().getLength() + str.length()) <= maxCharacters
                            && text.matches("^[0-9]$")) {
                        super.insertString(fb, offs, str, a);
                    } else {
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            });

            inputFields.add(enterFields[i], con);
        }



        JPanel inputButtons = new JPanel(new GridBagLayout());
        con.insets = new Insets(30,0,0,0);

        currencyC = new JLabel("Current balance: " + currencyInChaos + "c");
        currencyC.setFont(new Font(currencyC.getFont().getName(), Font.PLAIN, (int)(currencyC.getFont().getSize()*0.9)));
        currencyC.setToolTipText("Current balance in Chaos Orbs");
        con.gridx = 3;
        con.gridy = 1;
        inputButtons.add(currencyC, con);

        con.insets = new Insets(0,0,0,0);
        JButton submit = new JButton("Add Currency");
        con.gridx = 3;
        con.gridy = 2;

        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addButtonPressed();
            }
        });

        inputButtons.add(submit, con);

        JButton other = new JButton("Other Currency");
        con.gridx = 1;
        con.gridy = 2;

        other.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               otherButtonPressed();
           }
        });

        inputButtons.add(other, con);

        pane.add(inputFields, BorderLayout.NORTH);
        pane.add(inputButtons, BorderLayout.SOUTH);
    }

    private static void addButtonPressed() {
        //update to use api
        for (JTextField i: enterFields) {
            if (!i.getText().equals("")) {
                double quant = Double.parseDouble(i.getText());
                double value = currency.get(i.getToolTipText());
                double totalValue = quant*value;
                addChaos(totalValue);
                i.setText("");
            }
        }
    }

    private static void otherButtonPressed() {
        System.out.println("Bye");
    }

    private static void addChaos(double amount) {
        currencyInChaos += amount;
        currencyC.setText("Current balance: " + currencyInChaos + "c");
    }

    public static void init(Map<String, Double> cm) {
        //setting up currency names and values
        currency = cm;

        //loading window
        CurrencyMenu currencyFrame = new CurrencyMenu("Currency Input");
        currencyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        currencyFrame.addComponents(currencyFrame.getContentPane());
        currencyFrame.pack();
        currencyFrame.setLocationRelativeTo(null);
        currencyFrame.setVisible(true);

    }





}
