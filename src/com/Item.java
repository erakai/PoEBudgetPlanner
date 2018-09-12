package com;

public class Item {
    private int id;
    public String name;
    public String icon;
    public int chaosValue, exaltedValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getChaosValue() {
        return chaosValue;
    }

    public void setChaosValue(int chaosValue) {
        this.chaosValue = chaosValue;
    }

    public int getExaltedValue() {
        return exaltedValue;
    }

    public void setExaltedValue(int exaltedValue) {
        this.exaltedValue = exaltedValue;
    }
}
