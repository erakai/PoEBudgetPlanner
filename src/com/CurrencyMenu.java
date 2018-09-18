package com;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.CurrentInfo.currentBuild;

public class CurrencyMenu extends JDialog {
    private static final int maxCharacters = 5;
    private static final int maxNumber = 9999999;
    private static final int textAreaColumns = 4;

    public static JDialog currencyDialog;
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

    public CurrencyMenu(Map<String, Double> cm, Frame app, String name) {
        super(app, name, true);
        currencyDialog = this;
        currency = cm;
        setTitle("Add Currency");
        currencyDialog.setLocationRelativeTo(app);
        currencyDialog.setSize(400, 200);
        addComps();
        currencyDialog.setVisible(true);
    }

    public void addComps() {
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

            AbstractDocument document = (AbstractDocument) enterFields[i].getDocument();
            document.setDocumentFilter(myGetDocumentFilter());

            inputFields.add(enterFields[i], con);
        }



        JPanel inputButtons = new JPanel(new GridBagLayout());
        con.insets = new Insets(50,5,0,5);

        currencyC = new JLabel("Current balance: " + currentBuild.getCurrencyInChaos() + "c");
        currencyC.setFont(new Font(currencyC.getFont().getName(), Font.PLAIN, (int)(currencyC.getFont().getSize()*0.9)));
        currencyC.setToolTipText("Current balance in Chaos Orbs");
        con.gridx = 3;
        con.gridy = 1;
        inputButtons.add(currencyC, con);

        JButton clearCurrency = new JButton(" Clear Balance ");
        con.gridx = 1;
        con.gridy = 1;
        clearCurrency.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearButtonPressed();
            }
        });
        inputButtons.add(clearCurrency, con);

        con.insets = new Insets(5,5,10,5);
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

        currencyDialog.add(inputFields, BorderLayout.NORTH);
        currencyDialog.add(inputButtons, BorderLayout.SOUTH);
    }

    //straight from StackOverflow hehehhehehe, makes sure they can only enter up to 5 numbers
    private static DocumentFilter myGetDocumentFilter() {
        return new DocumentFilter() {
            public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a) throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length() - length) <= maxCharacters && text.matches("[0-9]+")) {
                    super.replace(fb, offs, length, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                text += str;
                if ((fb.getDocument().getLength() + str.length()) <= maxCharacters && text.matches("[0-9]+")) {
                    super.insertString(fb, offs, str, a);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };
    }

    private static void clearButtonPressed() {
        addChaos((-1 * addChaos(0)));
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
        initOtherDialog();
    }

    private static void initOtherDialog() {
        JDialog dialog = new JDialog(currencyDialog, "Other Currency",true);
        JPanel dialogPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,0,0,25);

        JLabel instruct = new JLabel("Enter amount:");
        c.gridx=0;
        c.gridy=0;
        dialogPanel.add(instruct, c);

        c.insets = new Insets(5,15,0,0);

        JLabel instruct2 = new JLabel("Select type:");
        c.gridx=1;
        c.gridy=0;
        dialogPanel.add(instruct2,c);

        c.insets = new Insets(0,0,5,10);

        JTextField amount = new JTextField(textAreaColumns);
        c.gridx=0;
        c.gridy=1;
        amount.setText("0");
        AbstractDocument document = (AbstractDocument) amount.getDocument();
        document.setDocumentFilter(myGetDocumentFilter());
        dialogPanel.add(amount, c);

        c.insets = new Insets(0,10,5,0);

        //getting the list of currencies
        ArrayList<String> tempC = new ArrayList<String>(currency.keySet());
        String[] currencies = new String[tempC.size()];
        for (int i = 0; i< tempC.size();i++) {
            currencies[i] = tempC.get(i);
        }
        JComboBox currencyList = new JComboBox(currencies);
        c.gridx=1;
        c.gridy=1;
        //currencyList.setEditable(true); enable later when i can make it more specific
        dialogPanel.add(currencyList,c);

        JLabel thisCurrencyCounter = new JLabel("Current balance: " + currentBuild.getCurrencyInChaos() + "c");
        c.gridx=0;
        c.gridy=3;
        dialogPanel.add(thisCurrencyCounter, c);

      //  JPanel buttonSelect = new JPanel(new GridBagLayout());

        JButton addCurrency = new JButton("Add Currency");
        c.gridx=1;
        c.gridy=2;
        addCurrency.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                thisCurrencyCounter.setText("Current balance: " + String.valueOf(addChaos((Double.parseDouble(amount.getText()) * currency.get(currencyList.getSelectedItem())))) + "c");
            }
        });
        dialogPanel.add(addCurrency,c);

        JButton toClose = new JButton("Close");
        c.gridx=1;
        c.gridy=3;
        toClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialogPanel.add(toClose,c);

       // dialog.add(buttonSelect, BorderLayout.SOUTH);
        dialog.add(dialogPanel, BorderLayout.NORTH);
        dialog.setSize(480,170);
        dialog.setLocationRelativeTo(currencyDialog);
        dialog.setVisible(true);

        //JOptionPane otherCurrencyPopup = new JOptionPane()
    }

    private static double addChaos(double amount) {
        currentBuild.currencyInChaos += amount;

        if (currentBuild.currencyInChaos > maxNumber) {
            currentBuild.currencyInChaos = maxNumber;
        }

        //round to hundredths place
        currentBuild.currencyInChaos = (double)Math.round(currentBuild.currencyInChaos * 100d) / 100d;

        currencyC.setText("Current balance: " + currentBuild.currencyInChaos + "c");
        MainWindow.updateCDisplay();

        return currentBuild.currencyInChaos;
    }


}
