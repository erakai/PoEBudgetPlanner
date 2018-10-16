package com;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dataload {

    //TODO: make league not hard coded

    private static String add = "https://poe.ninja/api/data/currencyoverview?league=Delve&type=Currency";
    private static ObjectMapper objMap = new ObjectMapper();
    private static int timeout = 10000;

    public static ArrayList<Item> weapons6Link = new ArrayList<>();
    public static ArrayList<Item> weapons5Link = new ArrayList<>();
    public static ArrayList<Item> weaponsBase = new ArrayList<>();
    public static ArrayList<Item> armours6Link = new ArrayList<>();
    public static ArrayList<Item> armours5Link = new ArrayList<>();
    public static ArrayList<Item> armoursBase = new ArrayList<>();
    public static ArrayList<Item> accessories = new ArrayList<>();
    public static ArrayList<Item> jewels = new ArrayList<>();
    public static ArrayList<Item> flasks = new ArrayList<>();

    public static String[] itemTypes = {
            "UniqueWeapon",
            "UniqueArmour",
            "UniqueAccessory",
            "UniqueJewel",
            "UniqueFlask"
    };


    public static HashMap<String, ArrayList<Item>> items = new HashMap<>();

    static {
        //parameter + link number
        items.put("UniqueWeapon6Links", weapons6Link);
        items.put("UniqueWeapon5Links", weapons5Link);
        items.put("UniqueWeapon0Links", weaponsBase);
        items.put("UniqueArmour6Links", armours6Link);
        items.put("UniqueArmour5Links", armours5Link);
        items.put("UniqueArmour0Links", armoursBase);
        items.put("UniqueAccessory0Links", accessories);
        items.put("UniqueJewel0Links", jewels);
        items.put("UniqueFlask0Links", flasks);



        JsonNode root;


        try {

            for (String x: itemTypes) {
                root = objMap.readTree(getJSON(getUrl(x), timeout)).get("lines");

                int currentI = 0;
                while(root.get(currentI+1) != null) {
                    currentI++;

                    JsonNode current = root.get(currentI);
                    String name = current.get("name").toString().replaceAll("\"", "");
                    String img = current.get("icon").toString().replaceAll("\"","").split("\\?")[0];
                    double cValue = current.get("chaosValue").doubleValue();
                    int links = current.get("links").intValue();
                    String descrip = (links + " links. \n\n") + current.get("flavourText").toString();

                    Item nI = new Item(img, name, "Unique", descrip, cValue);

                   // System.out.println(nI);

                    items.get(x+String.valueOf(links) + "Links").add(nI);
                }

            }




        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }


    private static String getUrl(String iT) {
        return ("https://poe.ninja/api/data/itemoverview?league=Delve&type="+iT);
    }

    public static Map<String, Double> getCurrencyMap() {
        JsonNode root;

        try {
             root = objMap.readTree(getJSON(add, timeout)).get("lines");

            Map<String, Double> currency = new HashMap<>();

            int currentI = 0;
            do {
                String name = root.get(currentI).get("currencyTypeName").toString().replace("\"", "");
                double value = root.get(currentI).get("chaosEquivalent").doubleValue();
                currency.put(name, value);
                currentI++;
            } while (root.get(currentI+1) != null);

            if (!currency.isEmpty()) {
                currency.put("Chaos Orb", 1.0);
            }

        return currency;

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }


    //taken mostly from stackoverflow, need to learn how to do this
    public static String getJSON(String url, int timeout) {
        HttpURLConnection c = null;
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
            c.setConnectTimeout(timeout);
            c.setReadTimeout(timeout);
            c.connect();
            int status = c.getResponseCode();

            switch (status) {
                case 201:case 200:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }




    //mostly useless method in command line
    public static void runCurrencyViewer() throws Exception {
        JsonNode root = objMap.readTree(getJSON(add,timeout));

        Scanner input = new Scanner(System.in);

        while(true) {
            System.out.println("Enter index:");
            String sinp = input.next();

            if (sinp.equals("quit")) {
                break;
            }

            int inp = Integer.parseInt(sinp);

            try {
                JsonNode number = root.get("lines").get(inp);
                System.out.println(number.get("currencyTypeName") + " : " + number.get("chaosEquivalent") + " chaos");
            } catch (Exception ex) {
                System.out.println("Invalid.");
            }
        }
    }

}
