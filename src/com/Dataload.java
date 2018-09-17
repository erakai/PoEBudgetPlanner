package com;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dataload {
    private static String add = "https://poe.ninja/api/data/currencyoverview?league=Delve&type=Currency";
    private static ObjectMapper objMap = new ObjectMapper();
    private static int timeout = 5000;

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
