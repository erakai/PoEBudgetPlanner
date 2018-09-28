package com;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Build implements Serializable {
    public double currencyInChaos;
    public List<Item> items = new ArrayList<Item>();
    public String name;
    public String description;

    @JsonCreator
    public Build(@JsonProperty("currencyInChaos") double currencyInChaos, @JsonProperty("items") List<Item> items, @JsonProperty("name") String name, @JsonProperty("description") String description) {
        this.currencyInChaos = currencyInChaos;
        this.items = items;
        this.name = name;
        this.description = description;
    }

    public Build(String name) {
        this.name = name;
    }

    @JsonIgnore
    public Item getItem(String name) {
        for(Item i: items) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

    @JsonIgnore
    public double getTotalCost() {
        double tC = 0;
        for (Item i: items) {
            tC += i.getValue();
        }
        return tC;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
