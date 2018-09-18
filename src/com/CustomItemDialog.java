package com;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CustomItemDialog extends JDialog {
    //need a text field for name
    //need to choose a color (drop down list?)
    //need a text field for (with document filter) cost of item


    public static JDialog customItemDialog;
    private static JTextField chooseName;
    private static JTextField itemCost;
    private static JTextArea description;
    private static JComboBox typeOfItem;

    public CustomItemDialog(Frame frame, String name) {
        super(frame, name, true);
        customItemDialog = this;
        customItemDialog.setLocationRelativeTo(frame);
        customItemDialog.setSize(320,275);
        addComponents();
        customItemDialog.setVisible(true);
    }

    private void addComponents() {
        //get input from user on left, button jpanel on right for cancel/create
        JPanel input = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(15, 5, 2, 5);

        JLabel name = new JLabel("Enter name:");
        c.gridy = 0;
        input.add(name, c);

        JLabel cost = new JLabel("Enter cost (c):");
        c.gridy = 2;
        input.add(cost, c);

        JLabel type = new JLabel("Select type: ");
        c.gridy= 4;
        input.add(type, c);

        c.insets = new Insets(2, 5,15, 5);

        chooseName = new JTextField(7);
        chooseName.setText("Custom Item");
        c.gridy = 1;
        input.add(chooseName, c);

        itemCost = new JTextField(7);
        itemCost.setText("0");
        AbstractDocument document = (AbstractDocument) itemCost.getDocument();
        document.setDocumentFilter(CurrencyMenu.myGetDocumentFilter("^[0-9]+[.]?[0-9]{0,1}$"));
        c.gridy = 3;
        input.add(itemCost, c);

        typeOfItem = new JComboBox(Item.types);
        c.gridy = 5;
        input.add(typeOfItem, c);

        c.insets = new Insets(0,5,5,25);

        JPanel buttonsDesc = new JPanel(new GridBagLayout());

        description = new JTextArea("Description");
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(description);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(125, 125));
        c.gridy=0;
        buttonsDesc.add(areaScrollPane, c);

        c.insets = new Insets(10,5,5,25);

        JButton cancel = new JButton("Cancel");
        c.gridy=1;
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                customItemDialog.dispose();
            }
        });
        buttonsDesc.add(cancel,c);

        JButton create = new JButton("Create");
        c.gridy=2;
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createItem();
            }
        });
        buttonsDesc.add(create,c);


        customItemDialog.add(buttonsDesc, BorderLayout.EAST);
        customItemDialog.add(input, BorderLayout.WEST);
    }

    public void createItem() {
        Item newItem = new Item(chooseName.getText(),String.valueOf(typeOfItem.getSelectedItem()), Item.colorKey.get(typeOfItem.getSelectedItem()), Integer.parseInt(itemCost.getText()));
        MainBuildPanel.addListItem(newItem);
        customItemDialog.dispose();
    }

}
