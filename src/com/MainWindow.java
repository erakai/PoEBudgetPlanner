package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainWindow extends JFrame {
    public static MainWindow mainFrame;
    public static JLabel currentMoney;
    public MainWindow(String name) {
        super(name);
        setTitle(name);
    }

    private static void addComponentsTop(Container pane) {
        pane.add(addTopPanel(), BorderLayout.NORTH);
    }

    private static JPanel addTopPanel() {
        JPanel top = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        currentMoney = new JLabel("Currency: " + CurrentInfo.getCurrencyInChaos() + "c");
        currentMoney.setToolTipText("Current balance in Chaos Orbs");
        c.gridy = 0;
        c.gridx = 0;
        c.insets = new Insets(15, 5, 0, 35);
        top.add(currentMoney,c);

        JButton add = new JButton("Add Currency");
        c.gridy=1;
        c.gridx=0;
        c.insets = new Insets(0,5,0,35);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CurrencyMenu x = new CurrencyMenu(CurrentInfo.getCm(), mainFrame, "Currency Input");
            }
        });
        top.add(add, c);

        JLabel title = new JLabel("Budget Planner");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, (int)(title.getFont().getSize()*2)));
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(15, 35,0,35);
        top.add(title, c);

        JLabel placeHolder = new JLabel("                              ");
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(15, 35, 0, 5);
        top.add(placeHolder, c);
        
        return top;
    }

    public static void updateCDisplay() {
        currentMoney.setText("Currency: " + CurrentInfo.getCurrencyInChaos() + "c");
    }

    public static void init() {
        mainFrame = new MainWindow("Budget Planner");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.addComponentsTop(mainFrame.getContentPane());
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

}
