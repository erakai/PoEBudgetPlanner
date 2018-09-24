package com;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class ItemShowcasePanel extends JPanel {

    public ItemShowcasePanel(LayoutManager layout) {
        super(layout);
        this.addComponents();
    }

    private void addComponents() {
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        GridBagConstraints c = new GridBagConstraints();


    }
}
