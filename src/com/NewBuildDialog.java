package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewBuildDialog extends JDialog {
    public static JDialog newBuildDialog;
    public static JTextField userFill;

    public NewBuildDialog(Frame frame, String name) {
        super(frame,name,true);
        newBuildDialog = this;
        newBuildDialog.setLocationRelativeTo(frame);
        newBuildDialog.setSize(200,125);
        addComponents();
        newBuildDialog.setVisible(true);
    }

    public void addComponents() {
        JPanel top = new JPanel(new GridBagLayout());
        JPanel bot = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.insets=new Insets(5,5,0,5);

        JLabel nameEnter = new JLabel("Enter build name:");
        top.add(nameEnter, c);

        userFill = new JTextField(7);
        userFill.setEditable(true);
        c.gridy=1;
        top.add(userFill,c);

        c.insets=new Insets(5,5,5,5);


        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newBuildDialog.dispose();
            }
        });
        bot.add(cancel,c);

        JButton create = new JButton("Create");
        c.gridx=1;
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCreate();
            }
        });
        bot.add(create,c);


        newBuildDialog.add(top, BorderLayout.NORTH);
        newBuildDialog.add(bot, BorderLayout.SOUTH);
    }

    private void onCreate() {
        CurrentInfo.setBuildLoaded(true);
        CurrentInfo.currentBuild = new Build(userFill.getText());
        MainButtonPanel.updateButtons();
        MainWindow.updateBuildName();
        MainBuildPanel.updateList();
        newBuildDialog.dispose();
    }

}
