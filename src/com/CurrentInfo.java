package com;

import java.util.Map;

public class CurrentInfo {

    //TODO: Write this to a file where path given by user

    public static double currencyInChaos;
    public static boolean buildLoaded;
    public static Map<String, Double> cm;

    public CurrentInfo() {
        cm = Dataload.getCurrencyMap();
    }

    public static double getCurrencyInChaos() {
        return currencyInChaos;
    }

    public static void setCurrencyInChaos(double currencyInChaos) {
        CurrentInfo.currencyInChaos = currencyInChaos;
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
}
