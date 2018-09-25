package com;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CurrentInfo {

    //TODO: Write this to a file where path given by user

    public static boolean buildLoaded;
    public static Map<String, Double> cm;
    public static Build currentBuild;

    public CurrentInfo() {
        cm = Dataload.getCurrencyMap();

        //lazy fix for there not being a build on start up, should change
        currentBuild = new Build("");
    }


    public static Map<String, Double> getCm() {
        return cm;
    }

    public static ImageIcon getImage(String url) {
        try {
            return new ImageIcon(ImageIO.read(new File("/Users/mbp/Documents/JavaPrograms/10 PoeBudgetPlanner/PoeBudgetPlanner/src/com/placeholder.png")).getScaledInstance(64,64, Image.SCALE_SMOOTH));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void setCm(Map<String, Double> cm) {
        CurrentInfo.cm = cm;
    }

    public static boolean isBuildLoaded() {
        return buildLoaded;
    }

    public static void setBuildLoaded(boolean buildLoaded) {
        CurrentInfo.buildLoaded = buildLoaded;
    }

    public static Build getCurrentBuild() {
        return currentBuild;
    }

    public static void setCurrentBuild(Build currentBuild) {
        CurrentInfo.currentBuild = currentBuild;
    }
}
