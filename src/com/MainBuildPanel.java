package com;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class MainBuildPanel extends JPanel {
    private ItemShowcasePanel showcasePanel;
    private JPanel listPanel;
    private JPanel optionPanel;

    private static DefaultListModel<String> currentList;
    public static JList itemList;
    private static JLabel totalC;

    public MainBuildPanel() {
        super(new GridBagLayout());
        this.addComponents();
    }

    public void addComponents() {
        GridBagConstraints c = new GridBagConstraints();

        c.gridx=0;
        this.add(addListPanel(),c);
        c.gridx=1;
        c.insets = new Insets(5,10,30,5);
        this.add(addShowcasePanel(),c);
    }

    private JPanel addListPanel() {
        listPanel = new JPanel(new GridBagLayout());
       // listPanel.setPreferredSize(new Dimension(120,200));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5,5,5,5);

        currentList = new DefaultListModel<>();

        c.gridy=0;
        itemList = new JList(currentList);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setSelectedIndex(-1);
        //itemList.addListSelectionListener();
        itemList.setVisibleRowCount(5);
        itemList.setCellRenderer(new CellRendererForType());
        JScrollPane listScrollPane = new JScrollPane(itemList);
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        itemList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!itemList.isSelectionEmpty()) {
                    showcasePanel.updateDisplay(CurrentInfo.currentBuild.getItem((String) (itemList.getSelectedValue())));
                } else {
                    if (itemList.getModel().getSize() == 0) {
                        showcasePanel.setVisible(false);
                    } else {
                        showcasePanel.updateDisplay(CurrentInfo.currentBuild.getItem((String) (itemList.getModel().getElementAt(itemList.getLastVisibleIndex()))));
                    }
                }
            }
        });
        listScrollPane.setPreferredSize(new Dimension(240, 360));
        listPanel.add(listScrollPane,c);

        c.gridy= 1;
        totalC = new JLabel("Total Price: " +  String.valueOf(CurrentInfo.currentBuild.getTotalCost()) + "c");
        listPanel.add(totalC,c);

        updateList();

        return listPanel;
    }

    public static void updateTotalPrice() {
        totalC.setText("Total Price: " +  String.valueOf(CurrentInfo.currentBuild.getTotalCost()) + "c");
    }


    public static void addListItem(Item item) {
        CurrentInfo.currentBuild.items.add(item);
        updateList();
    }

    public static void remListItem(Item item) {
        CurrentInfo.currentBuild.items.remove(item);
        updateList();
    }

    public static void updateList() {
        totalC.setText("Total Price: " + String.valueOf(CurrentInfo.currentBuild.getTotalCost()) + "c");

        currentList.clear();
        for (Item i: CurrentInfo.currentBuild.items) {
            currentList.addElement(i.getName());
        }
    }

    private JPanel addShowcasePanel() {
        showcasePanel = new ItemShowcasePanel(new GridBagLayout());

        return showcasePanel;
    }



    //call from inside addShowcasePanel();
    private JPanel addItemOptionPanel() {
        optionPanel = new JPanel();

        return optionPanel;
    }


    private class CellRendererForType extends JLabel implements ListCellRenderer {

        public CellRendererForType() {
            setOpaque(true);
        }

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

            setText(value.toString());

            setForeground(Item.colorKey.get(CurrentInfo.getCurrentBuild().getItem(String.valueOf(value)).getType()));
            setBackground(new Color(255,255,255));
            if(isSelected) {
                setBackground(new Color(17,128,240));
            }

            return this;
        }
    }


}
