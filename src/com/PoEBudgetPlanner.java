package com;

public class PoEBudgetPlanner {

    public static void main(String[] args) throws Exception {
        CurrencyMenu.init(Dataload.getCurrencyMap());

        //need to make currencymenu into a dialog box


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
