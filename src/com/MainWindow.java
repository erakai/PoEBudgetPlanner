package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.CurrentInfo.currentBuild;

public class MainWindow extends JFrame {
    public static MainWindow mainFrame;
    public static MainButtonPanel buttonPanel;
    public static MainBuildPanel buildPanel;
    public static JLabel currentMoney;
    public static JButton add;
    public static JLabel currentBuildName;

    public MainWindow(String name) {
        super(name);
        setTitle(name);
    }

    private static void addComponentsTop(Container pane) {
        pane.add(addTopPanel(), BorderLayout.NORTH);
        pane.add(addButtonPanel(), BorderLayout.CENTER);
        pane.add(addMainBuildPanel(), BorderLayout.SOUTH);
    }


    private static JPanel addTopPanel() {
        JPanel top = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        currentMoney = new JLabel("Currency: " + currentBuild.getCurrencyInChaos() + "c");
        currentMoney.setToolTipText("Current balance in Chaos Orbs");
        c.gridy = 0;
        c.gridx = 0;
        c.insets = new Insets(15, 15, 0, 25);
        top.add(currentMoney,c);

        add = new JButton("Add Currency");
        c.gridy=1;
        c.gridx=0;
        c.insets = new Insets(0,15,15,25);
        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CurrencyMenu x = new CurrencyMenu(CurrentInfo.getCm(), mainFrame, "Currency Input");
            }
        });
        top.add(add, c);

        JLabel title = new JLabel("PoE Budget Planner");
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, (int)(title.getFont().getSize()*2)));
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(15, 35,0,35);
        top.add(title, c);

        String name = CurrentInfo.getCurrentBuild().getName();
        String text = "";
        if (!name.equals("")) {
            text += ("Current Build: " + name);
        }
        currentBuildName = new JLabel(text);
        c.gridx=1;
        c.gridy=1;
        c.insets = new Insets(5,35,15,35);
        top.add(currentBuildName,c);

        JLabel placeHolder = new JLabel("                              ");
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(15, 35, 0, 5);
        top.add(placeHolder, c);
        
        return top;
    }

    public static void updateBuildName() {
        currentBuildName.setText("Current Build: "  + CurrentInfo.getCurrentBuild().getName());
    }

    private static JPanel addButtonPanel() {
        buttonPanel = new MainButtonPanel();
        return buttonPanel;
    }

    private static JPanel addMainBuildPanel() {
        buildPanel = new MainBuildPanel();
        return buildPanel;
    }

    public static void updateCDisplay() {
        currentMoney.setText("Currency: " + currentBuild.getCurrencyInChaos() + "c");
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
