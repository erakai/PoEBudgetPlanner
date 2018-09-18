package com;

import java.util.ArrayList;
import java.util.List;

public class Build {
    public double currencyInChaos;
    public List<Item> items = new ArrayList<Item>();
    public String name;
    public String description;

    public Build(String name) {
        this.name = name;
    }

    public Item getItem(String name) {
        for(Item i: items) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
