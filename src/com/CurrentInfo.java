package com;

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
