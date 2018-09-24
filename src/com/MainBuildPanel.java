package com;

import javax.swing.*;
import java.awt.*;

public class MainBuildPanel extends JPanel {
    private JPanel showcasePanel;
    private JPanel listPanel;
    private JPanel optionPanel;

    private static DefaultListModel<String> currentList;
    private static JList itemList;
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
        listScrollPane.setPreferredSize(new Dimension(240, 360));
        listPanel.add(listScrollPane,c);

        c.gridy= 1;
        totalC = new JLabel("Total Price: " +  String.valueOf(CurrentInfo.currentBuild.getTotalCost()));
        listPanel.add(totalC,c);

        updateList();

        return listPanel;
    }


    public static void addListItem(Item item) {
        CurrentInfo.currentBuild.addItem(item);
        updateList();
    }

    public static void remListItem(Item item) {
        CurrentInfo.currentBuild.remItem(item);
        updateList();
    }

    public static void updateList() {
        totalC.setText("Total Price: " + String.valueOf(CurrentInfo.currentBuild.getTotalCost()));

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

            setForeground(CurrentInfo.getCurrentBuild().getItem(String.valueOf(value).split(":")[0]).getColor());
            setBackground(new Color(255,255,255));
            if(isSelected) {
                setBackground(new Color(17,128,240));
            }

            return this;
        }
    }


}
