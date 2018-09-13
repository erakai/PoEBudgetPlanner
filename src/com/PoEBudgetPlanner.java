package com;
import com.AddCurrency.CurrencyMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PoEBudgetPlanner {

    public static void main(String[] args) throws Exception {
        CurrencyMenu.init();

        /*Map<String, Double> currency = Dataload.getCurrencyMap();

        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Enter currency name: ");
            String curr = input.nextLine();
            if (curr.equals("quit")) {
                break;
            } else if (currency.containsKey(curr)) {
                System.out.println("Value: " + currency.get(curr) + " chaos.");
            } else {
                System.out.println("That is not a valid currency name.");
            }
        }*/

    }


}
