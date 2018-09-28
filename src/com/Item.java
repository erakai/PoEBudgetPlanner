package com;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Item implements Serializable {
    public String img;
    public String name;
    public String type;
    public String description;
    public double value;

    public static String[] types = {
            "Normal",
            "Magic",
            "Rare",
            "Unique",
    };

    public static HashMap<String, Color> colorKey = new HashMap<String, Color>();
    public static HashMap<Color, Color> backgroundKey = new HashMap<>();

    //TODO: get correct colors
    static {
        colorKey.put("Normal", new Color(193,193,193));
        colorKey.put("Magic", new Color(118, 111, 205));
        colorKey.put("Rare", new Color(235, 232,127));
        colorKey.put("Unique", new Color(144, 82, 35));

        backgroundKey.put(new Color(193,193,193), new Color(70,70,70));
        backgroundKey.put(new Color(235, 232,127), new Color(70,70,70));

        backgroundKey.put(new Color(118, 111, 205), new Color(220, 220, 220));
        backgroundKey.put(new Color(144,82,35), new Color(220, 220, 220));

    }

    @JsonCreator
    public Item(@JsonProperty("img") String img, @JsonProperty("name") String name, @JsonProperty("type") String type, @JsonProperty("description") String description, @JsonProperty("value") double value) {
        this.img = img;
        this.name = name;
        this.type = type;
        this.description = description;
        this.value = value;
    }

    public Item(String name, String rarity, String desc, double value) {
        this.name = name;
        this.type = rarity;
        this.description = desc;
        this.value = value;

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
