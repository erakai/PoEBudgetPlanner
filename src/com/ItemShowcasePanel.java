package com;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ItemShowcasePanel extends JPanel {
    private static JLabel name;
    private static JLabel image;
    private static JLabel value;
    private static JTextArea description;

    private static Item showcasing;


    public ItemShowcasePanel(LayoutManager layout) {
        super(layout);
        this.setVisible(false);

        this.showcasing = new Item("","", "", new Color(0,0,0),0);
        this.addComponents();
        this.setPreferredSize(new Dimension(250,360));
    }

    private void addComponents() {

        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(5,5,5,5);

        name = new JLabel(showcasing.getName());
        this.add(name, c);

        image = new JLabel();
        image.setIcon(CurrentInfo.getImage(showcasing.getImg()));
        c.gridy=1;
        this.add(image, c);

        value = new JLabel(showcasing.getValue() + "c");
        c.gridy=3;
        this.add(value, c);

        description = new JTextArea(showcasing.getDescription());
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        JScrollPane areaScrollPane = new JScrollPane(description);
        areaScrollPane.setPreferredSize(new Dimension(200, 125));
        description.setEditable(false);
        c.gridy=2;
        this.add(areaScrollPane, c);

    }

    public void updateDisplay(Item i) {
        showcasing = i;
        name.setText(showcasing.getName());
        name.setForeground(showcasing.getColor());
        name.setBackground(Item.backgroundKey.get(showcasing.getColor()));
        name.setOpaque(true);
        name.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        image.setIcon(CurrentInfo.getImage(showcasing.getImg()));
        value.setText(showcasing.getValue() + "c");
        value.setBackground(Item.backgroundKey.get(showcasing.getColor()));
        value.setOpaque(true);
        value.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        value.setForeground(showcasing.getColor());
        description.setText(showcasing.getDescription());
        this.setVisible(true);
    }
}
