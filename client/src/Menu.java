package org.boes.praktikum.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class Menu {

    RequestBuilder reqBuilder = new RequestBuilder();

    // item sub menu lets the user choose a specific good of interest for his action
    public String itemSubMenu(Scanner userInput){
        // Print json specifying the items structure
        System.out.println("What type of item?");
        System.out.println("1: I'm interested in 'Wood' ");
        System.out.println("2: I'm interested in 'Kidney' ");
        System.out.println("3: I'm interested in 'Diamond' ");
        System.out.println("4: I'm interested in 'Steel' ");
        System.out.println("5: I'm interested in 'Gold' ");
        System.out.println("6: I'm interested in 'Silver' ");

        String informationInput = userInput.nextLine();
        String goodOfInterest = itemSubMenuChoice(informationInput);
        return goodOfInterest;
    }

    public String itemSubMenuChoice(String informationInput){
        String goodOfInterest = "";
        switch(informationInput){
            case "1":
                goodOfInterest = "wood";
                break;
            case "2":
                goodOfInterest = "kidny";
                break;
            case "3":
                goodOfInterest = "dimond";
                break;
            case "4":
                goodOfInterest = "steel";
                break;
            case "5":
                goodOfInterest = "gold";
                break;
            case "6":
                goodOfInterest = "silver";
                break;
            default:
                goodOfInterest = "wood";
                break;
        }
        return goodOfInterest;
    }

    public int amountSubMenu(Scanner userInput, String goodOfInterest, boolean buy){
        // User can now specify the amount of items that shall be bought/sold

        if(buy){
            System.out.println("How many " + goodOfInterest + "-items do you wish to purchase?");
            System.out.println("1: Buy 1 " + goodOfInterest);
            System.out.println("2: Buy 5 " + goodOfInterest);
            System.out.println("3: Buy 10 " + goodOfInterest);
            System.out.println("4: Buy 50 " + goodOfInterest);
            System.out.println("5: Buy 100 " + goodOfInterest);
            System.out.println("6: Buy 500 " + goodOfInterest);
        } else {
            System.out.println("How many " + goodOfInterest + "-items do you wish to sell?");
            System.out.println("1: Sell 1 " + goodOfInterest);
            System.out.println("2: Sell 5 " + goodOfInterest);
            System.out.println("3: Sell 10 " + goodOfInterest);
            System.out.println("4: Sell 50 " + goodOfInterest);
            System.out.println("5: Sell 100 " + goodOfInterest);
            System.out.println("6: Sell 500 " + goodOfInterest);
        }


        String informationInput = userInput.nextLine();
        return amountSubMenuChoice(informationInput);
    }

    public int amountSubMenuChoice(String amountValueInput){
        int amount = 0;
        switch(amountValueInput){
            case "1":
                amount = 1;
                break;
            case "2":
                amount = 5;
                break;
            case "3":
                amount = 10;
                break;
            case "4":
                amount = 50;
                break;
            case "5":
                amount = 100;
                break;
            case "6":
                amount = 500;
                break;
            default:
                amount = 1;
                break;
        }
        return amount;
    }
    public int fundSubMenu(Scanner userInput){
        // Add Funds to Account

        System.out.println("What amount do you want to add to your account?");
        System.out.println("1: 10$");
        System.out.println("2: 25$");
        System.out.println("3: 50$");
        System.out.println("4: 100$");
        System.out.println("5: 500$");

        String informationInput = userInput.nextLine();
        return fundSubMenuChoice(informationInput);
    }

    public int fundSubMenuChoice(String fundValueInput){
        int fundValue = 0;
        switch(fundValueInput){
            case "1":
                fundValue = 10;
                break;
            case "2":
                fundValue = 25;
                break;
            case "3":
                fundValue = 50;
                break;
            case "4":
                fundValue = 100;
                break;
            case "5":
                fundValue = 500;
                break;
            default:
                fundValue = 0;
                break;
        }
        return fundValue;
    }

    public double priceSubMenu(Scanner userInput){
        // Add Funds to Account

        System.out.println("At what price do you wish to sell?");
        float informationInput = Float.parseFloat(userInput.nextLine());
        return informationInput;
    }

    public String stringSubMenu(Scanner userInput, boolean username){
        if(username){
            System.out.println("Enter username:");
        } else {
            System.out.println("Enter password:");
        }

        String informationInput = userInput.nextLine();
        return informationInput;
    }

    public void printInventory(InventoryContent inv){
        System.out.println("Wood: " + inv.getWood());
        System.out.println("Kidney: " + inv.getKidny());
        System.out.println("Diamond: " + inv.getDimond());
        System.out.println("Steel: " + inv.getSteel());
        System.out.println("Gold: " + inv.getGold());
        System.out.println("Silver: " + inv.getSilver());
    }

    public void menuIdle(Scanner userInput, HttpClient client, ObjectMapper objectMapper, Login login) throws URISyntaxException, IOException, InterruptedException {

        System.out.println("Waiting for user input:");
        System.out.println("1: Print all available items");
        System.out.println("2: Item specific information");
        System.out.println("3: Receive information regarding the item's price");
        System.out.println("4: Add Funds to account");
        System.out.println("5: Buy an item");
        System.out.println("6: Sell an item");
        System.out.println("7: Show my inventory");
        System.out.println("8: Register a new user");
        System.out.println("9: Delete an existing user");
        System.out.println("Enter anything else to exit!");

        String input = userInput.nextLine();
        String goodOfInterest = "";

        switch (input) {

            // Receive entire list of goods from server
            case "1":
                var list = reqBuilder.goodsListRequestHandler(client,objectMapper);

                // print list of available goods
                System.out.println("Item-List object:");
                System.out.println(list);
                System.out.println();

                // print the names of all the available goods
                System.out.println("The available items are: ");
                for(Good i: list){
                    System.out.println(i.getName());
                }
                System.out.println();
                break;

            case "2":
                goodOfInterest = itemSubMenu(userInput);
                Good goodReceived = reqBuilder.goodInformationHandler(client,objectMapper,goodOfInterest);
                System.out.println("Good received:");
                System.out.println(goodReceived);
                System.out.println();
                break;
            case "3":
                goodOfInterest = itemSubMenu(userInput);
                Double kursReceived = reqBuilder.kursInformationHandler(client,objectMapper,goodOfInterest);
                System.out.println("Kurs received:");
                System.out.println(kursReceived + "$");
                System.out.println();
                break;
            case "4":
                int fundValue = fundSubMenu(userInput);
                User userReceived = reqBuilder.addFundHandler(client,objectMapper,fundValue,login);
                System.out.println("Your new balance:");
                System.out.println(userReceived.getGuthaben() + "$");
                System.out.println();
                break;
            case "5":
                //choose item to buy
                goodOfInterest = itemSubMenu(userInput);
                //choose amount
                int amount = amountSubMenu(userInput,goodOfInterest,true);

                int receiptReceived = reqBuilder.buyItemHandler(client,objectMapper,goodOfInterest,amount,login);
                System.out.println("You successfully bought " + amount + " " + goodOfInterest + "-Items!");
                System.out.println("Current amount of items in your inventory: " + receiptReceived + " Items");
                System.out.println();
                break;
            case "6":
                //choose item to sell
                goodOfInterest = itemSubMenu(userInput);
                //choose amount
                int amountSell = amountSubMenu(userInput,goodOfInterest,false);
                // choose price to sell at
                double price = priceSubMenu(userInput);

                int sellReceiptReceived = reqBuilder.sellItemHandler(client,objectMapper,goodOfInterest,amountSell,price,login);
                System.out.println("You successfully sold " + amountSell + " " + goodOfInterest + "-Items!");
                System.out.println("Current amount of items in your inventory: " + sellReceiptReceived + " Items");
                System.out.println();
                break;
            case "7":
                InventoryContent inv = reqBuilder.showInventory(client,objectMapper,login);
                System.out.println("Your inventory:");
                printInventory(inv);
                System.out.println();
                break;
            case "8":
                String username = stringSubMenu(userInput,true);
                String password = stringSubMenu(userInput,false);
                float startguthaben = 100f;

                reqBuilder.addUser(client,objectMapper,username,password,startguthaben);
                break;
            case "9":
                String usernameDelete = stringSubMenu(userInput,true);
                String passwordDelete = stringSubMenu(userInput,false);

                reqBuilder.removeUser(client,objectMapper,usernameDelete, passwordDelete);
                break;
            default:
                System.exit(0);
                break;
        }
    }
}
