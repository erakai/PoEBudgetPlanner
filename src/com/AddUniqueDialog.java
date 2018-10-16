package com;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddUniqueDialog extends JDialog {
    public static JDialog addUniqueDialog;

    private JComboBox<String> itemTypeSelector;
    private FilteredListModel filteredListModel;
    private JList<String> itemList;
    private JTextField filterItems;
    private JButton select, cancel;


    public AddUniqueDialog(Frame frame, String name) {
        super(frame, name, true);
        addUniqueDialog = this;
        addUniqueDialog.setLocationRelativeTo(frame);
        addUniqueDialog.setSize(320,275);
        addComponents();
        addUniqueDialog.setVisible(true);
    }

    /*
    JComboBox at top of screen that has the list of keys from dataload
    based on what is selected the corresponding arraylist will show up in a jlist
    a jtextfield at the bottom will be able to filter the jlist (ie: whenever text updated, reload jlist with matching items
    kai remember to set foreground for unique item type
    */

    private void addComponents() {
        JPanel main = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        String[] itemTypes = new String[Dataload.items.keySet().size()];
        int i = 0;
        for(String key: Dataload.items.keySet()) {
            itemTypes[i] = key;
            i++;
        }

        c.insets = new Insets(5,5,5,5);

        c.gridwidth = 2;
        itemTypeSelector = new JComboBox<>(itemTypes);
        itemTypeSelector.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                populateList((String)itemTypeSelector.getSelectedItem());
            }
        });
        main.add(itemTypeSelector, c);

        DefaultListModel s = new DefaultListModel();
        filteredListModel = new FilteredListModel(s);
        itemList = new JList(filteredListModel);

        populateList((String)itemTypeSelector.getSelectedItem());
        itemList.setForeground(Item.colorKey.get("Unique"));
        c.gridy = 1;
        //add border
        itemList.setVisibleRowCount(8);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(itemList);
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        main.add(listScrollPane, c);

        filterItems = new JTextField(12);
        c.gridwidth = 1;
        c.gridy = 2;
        c.gridx = 1;
        filterItems.setEditable(true);
        filterItems.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                change();
            }

            public void removeUpdate(DocumentEvent e) {
                change();
            }

            public void changedUpdate(DocumentEvent e) {
                change();
            }

            public void change() {
                updateList();
            }
        });
        main.add(filterItems, c);

        JLabel filter = new JLabel("Filter: ");
        c.gridx = 0;
        main.add(filter, c);


        //two buttons
        c.gridx=1;
        select = new JButton("Select");
        select.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });
        c.gridy=3;
        main.add(select, c);

        cancel = new JButton("Cancel");
        c.gridx=0;
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addUniqueDialog.dispose();
            }
        });
        main.add(cancel, c);


        this.add(main);
    }

    private void updateList() {
        if (filteredListModel.getSize() == 1) {
            itemList.setSelectedIndex(0);
        } else {
            itemList.clearSelection();
        }


        filteredListModel.setFilter(new FilteredListModel.Filter() {
            public boolean accept(Object element) {
                return ((String)(element)).toLowerCase().contains(filterItems.getText().toLowerCase());
            }
        });
    }


    private void add() {
        if (!itemList.isSelectionEmpty()) {
            ArrayList<Item> items = Dataload.items.get(itemTypeSelector.getSelectedItem());
            for (Item i: items) {
                if (i.getName().equals(itemList.getSelectedValue())) {
                    MainBuildPanel.addListItem(i);
                    addUniqueDialog.dispose();
                }
            }
        }
    }

    private void populateList(String k) {
        filteredListModel.clear();

        ArrayList<Item> items = Dataload.items.get(k);
        String[] names = new String[items.size()];
        int i = 0;
        for (Item p: items) {
            filteredListModel.addElement(p.getName());
            i++;
        }
        //strip names of quotes

    }





}
