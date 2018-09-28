package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainButtonPanel extends JPanel {
    static JButton addUnique;
    static JButton addCustom;
    static JButton tba;
    static JButton tba2;
    static JButton saveBuild;
    //public ArrayList<JButton> dependentButtons = new ArrayList<JButton>(4);

    public MainButtonPanel() {
        super(new GridBagLayout());
        this.addParts();
    }

    /*BUTTONS:
        1: New Build
        2: Save Build
        3: Load Build
        4: About
        _Next Row_
        5: Add Unique
        6: Add Custom
        7: TBA
        8: TBA
    */

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(new Color(8, 25, 43));
        g.fillRect(0,0,this.getWidth(),4);
        g.fillRect(0,this.getHeight()-4,this.getWidth(),4);
    }

    //TODO: Use a loop for dependent buttons
    public void addParts() {
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(22,10,10,10);
        JButton newBuild = new JButton("New Build");
        c.gridx=0;
        c.gridy=0;
        newBuild.setPreferredSize(new Dimension(105,25));
        newBuild.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NewBuildDialog nbd = new NewBuildDialog(MainWindow.mainFrame,"New Build");

            }
        });
        this.add(newBuild,c);

        saveBuild = new JButton("Save Build");
        c.gridx=1;
        saveBuild.setPreferredSize(new Dimension(105,25));
        saveBuild.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PathDialog pd = new PathDialog(MainWindow.mainFrame, "Save");
            }
        });
        this.add(saveBuild, c);

        JButton loadBuild = new JButton("Load Build");
        c.gridx=2;
        loadBuild.setPreferredSize(new Dimension(105,25));
        loadBuild.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PathDialog pd = new PathDialog(MainWindow.mainFrame, "Load");
            }
        });
        this.add(loadBuild, c);

        JButton help = new JButton("Help");
        c.gridx=3;
        help.setPreferredSize(new Dimension(105,25));
        this.add(help, c);

        //buttons that are disabled unless a build is loaded:
        c.insets = new Insets(10,10,22,10);
        addUnique = new JButton("Add Unique");
        c.gridx=0;
        c.gridy=1;
        addUnique.setPreferredSize(new Dimension(105,25));
        this.add(addUnique, c);

        addCustom = new JButton("Add Custom");
        c.gridx=1;
        addCustom.setPreferredSize(new Dimension(105,25));
        addCustom.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CustomItemDialog cid = new CustomItemDialog(MainWindow.mainFrame, "Custom Item");
            }
        });
        this.add(addCustom, c);

        tba = new JButton("          ");
        c.gridx=2;
        tba.setPreferredSize(new Dimension(105,25));
        this.add(tba,c);

        tba2 = new JButton("          ");
        c.gridx=3;
        tba2.setPreferredSize(new Dimension(105,25));
        this.add(tba2,c);

        updateButtons();
    }

    public static void updateButtons() {
        addUnique.setEnabled(CurrentInfo.isBuildLoaded());
        addCustom.setEnabled(CurrentInfo.isBuildLoaded());
        tba.setEnabled(CurrentInfo.isBuildLoaded());
        tba2.setEnabled(CurrentInfo.isBuildLoaded());
        saveBuild.setEnabled(CurrentInfo.isBuildLoaded());
        MainWindow.add.setEnabled(CurrentInfo.isBuildLoaded());
    }




}
