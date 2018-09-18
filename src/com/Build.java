package com;

import java.util.ArrayList;
import java.util.List;

public class Build {
    public double currencyInChaos;
    public List<Item> items = new ArrayList<Item>();
    public String name;

    public Build(String name) {
        this.name = name;
    }

    public double getCurrencyInChaos() {
        return currencyInChaos;
    }

    public void setCurrencyInChaos(double currencyInChaos) {
        currencyInChaos = currencyInChaos;
    }

    public void addItem(Item x) {
        items.add(x);
    }

    public void remItem(Item x) {
        items.remove(x);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
