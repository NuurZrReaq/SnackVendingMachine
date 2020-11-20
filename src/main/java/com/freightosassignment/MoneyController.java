package com.freightosassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoneyController {
    private File cards;
    private List<CreditCard> creditCardList;
    public MoneyController() {
        creditCardList = new ArrayList<>();
        cards = new File("cards.txt");
        try {
            creditCardList = fillCreditCardList(new Scanner(cards));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
        }

    }
    //Checks if the cardId entered is for a valid credit card.
    public boolean isCreditCardAvailable(String cardId){
        for(CreditCard creditCard : creditCardList){
            try {
                if (creditCard.getCardId().equals(cardId))
                    return true;
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
        return false;
    }
    //Updates the balance of the used credit card.
    public boolean buyWithCreditCard(String cardId, double price){
        int i=0;
        for(CreditCard creditCard : creditCardList){
            try{
                if(creditCard.getCardId().equals(cardId)) {
                    if(price <= creditCard.getBalance()){
                        creditCard.decrementBalance(price);
                        creditCardList.set(i,creditCard);
                        return true;
                    }
                    return false;

                }
            } catch (Exception exception){
                exception.printStackTrace();
            }
            i++;
        }
        return false;
    }

    //Returns the value of the remaining change needed to be sent back to customer.
    //Reads the payment method and scan the money entered.
    public double read (double price, Scanner console,ArrayList<Boolean> dispenseSnack) throws Exception {
        int i=0;
        while (i<5){
            i++;
            System.out.println("Would you like to use credit card for paying?\n " +
                    "Please Enter Yes or No\n");
            String isCredit;
            try{
               isCredit = console.nextLine().toLowerCase();
            } catch (Exception exception){
                throw new Exception("No line to read");
            }
            switch(isCredit){
                case "no": {
                    System.out.println("Please start entering your coins or bank notes\n" +
                            "When you finish please press ENTER\n");
                    String moneyString;
                    double moneyDouble;
                    double moneyToReturn =0;
                    double accumulatedMoney=0;
                    while(true){
                        try{
                            moneyString = console.nextLine();
                        } catch (Exception exception ){
                            throw new Exception("No line to read");
                        }
                        if(moneyString.equals("")){
                            break;
                        }
                        try{
                            moneyDouble = Double.parseDouble(moneyString);
                        } catch (Exception exception){
                            throw new Exception("Enter a double value");
                        }
                        if(validateMoney(moneyDouble)) {
                            accumulatedMoney += moneyDouble;
                            System.out.println("Money entered : "+ accumulatedMoney+" \n");
                            if(accumulatedMoney>=price) break;
                        }
                        else {
                            System.out.println("Unaccepted Money entered");
                            moneyToReturn = moneyToReturn + moneyDouble;
                            continue;
                        }
                    }

                    if(accumulatedMoney < price) {
                        moneyToReturn += accumulatedMoney;
                    }
                    else {
                        dispenseSnack.set(0,Boolean.TRUE);
                        moneyToReturn += (accumulatedMoney - price);
                    }
                    System.out.println("Money to be returned : "+ moneyToReturn+" \n");
                    return moneyToReturn;

                }
                case "yes": {
                    System.out.println("Please enter your credit card id\n");
                    String cardID;
                    try{
                        cardID = console.nextLine();
                    }catch (Exception exception){
                        throw new Exception("No line to read");
                    }
                    if(isCreditCardAvailable(cardID)){
                        if(buyWithCreditCard(cardID,price)){
                            System.out.println("Your payment has been performed successfully");
                            dispenseSnack.set(0,Boolean.TRUE);
                            return 0;
                        }
                        else {
                            System.out.println("Your balance is not enough to buy this item." +
                                    "Please use another method to pay");
                            continue;
                        }
                    }
                    else {
                        System.out.println("Your credit card id is not valid");
                        continue;
                    }
                }
            }

        }
        return 0;


    }
    //checks out that the money entered is from the allowed list.
    private boolean validateMoney(double moneyDouble) {
        if(moneyDouble == 20.0 || moneyDouble ==50.0 || moneyDouble ==1.00 || moneyDouble == 0.50 ||
                moneyDouble == 0.20 || moneyDouble == 0.10) {
            return true;
        }
        return false;
    }
    //Reads the list of credit card from a local file to mock a dataset.
    private List<CreditCard> fillCreditCardList(Scanner file) throws Exception {
        List<CreditCard> creditCards = new ArrayList<>();
        String line;
        String [] splitLine;
        for(int i=0; i<5; i++){
            try{
                line = file.nextLine();
            } catch (Exception e){
                throw new Exception("There is no line to read");
            }
            splitLine = line.split(" ");
            try{
                creditCards.add(new CreditCard(splitLine[0],Double.parseDouble(splitLine[1])));
            }catch (Exception exception){
                throw new Exception("Invalid balance data");
            }
        }
        return creditCards;
    }
}
