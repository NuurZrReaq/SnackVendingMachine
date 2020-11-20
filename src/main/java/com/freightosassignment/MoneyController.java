package com.freightosassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoneyController {
    private File cards;
    private List<CreditCard> creditCardList;
    public MoneyController() throws FileNotFoundException {
        creditCardList = new ArrayList<>();
        cards = new File("cards.txt");
        creditCardList = fillCreditCardList(new Scanner(cards));

    }

    public boolean isCreditCardAvailable(String cardId){
        for(CreditCard creditCard : creditCardList){
            if(creditCard.getCardId().equals(cardId))
                return true;
        }
        return false;
    }

    public boolean buyWithCreditCard(String cardId, double price){
        int i=0;
        for(CreditCard creditCard : creditCardList){
            if(creditCard.getCardId().equals(cardId)) {
                if(price <= creditCard.getBalance()){
                    creditCard.decrementBalance(price);
                    creditCardList.set(i,creditCard);
                    return true;
                }
                return false;

            }
            i++;
        }
        return false;
    }

    //Returns the value of the remaining change needed to be sent back to customer.
    public double read (double price, Scanner console,ArrayList<Boolean> dispenseSnack){
        int i=0;
        while (i<5){
            i++;
            System.out.println("Would you like to use credit card for paying?\n Please Enter Yes or No\n");
            String isCredit = console.nextLine().toLowerCase();
            switch(isCredit){
                case "no": {
                    System.out.println("Please start entering your coins or bank notes\nWhen you finish please press ENTER\n");
                    String moneyString;
                    double moneyDouble;
                    double moneyToReturn =0;
                    double accumulatedMoney=0;
                    while(true){
                        moneyString = console.nextLine();
                        if(moneyString.equals("")){
                            break;
                        }
                        moneyDouble = Double.parseDouble(moneyString);
                        if(validateMoney(moneyDouble)) {
                            accumulatedMoney += moneyDouble;
                            System.out.println("Money entered : "+ accumulatedMoney+" \n");
                            if(accumulatedMoney>price) break;
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
                    String cardID = console.nextLine();
                    if(isCreditCardAvailable(cardID)){
                        if(buyWithCreditCard(cardID,price)){
                            System.out.println("Your payment has been performed successfully");
                            dispenseSnack.set(0,Boolean.TRUE);
                            return 0;
                        }
                        else {
                            System.out.println("Your balance is not enough to buy this item, Please use another method to pay");
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

    private boolean validateMoney(double moneyDouble) {
        if(moneyDouble == 20.0 || moneyDouble ==50.0 || moneyDouble ==1.00 || moneyDouble == 0.50 ||
                moneyDouble == 0.20 || moneyDouble == 0.10) {
            return true;
        }
        return false;
    }

    private List<CreditCard> fillCreditCardList(Scanner file) {
        List<CreditCard> creditCards = new ArrayList<>();
        String line;
        String [] splitLine;
        for(int i=0; i<5; i++){
            line = file.nextLine();
            splitLine = line.split(" ");
            creditCards.add(new CreditCard(splitLine[0],Double.parseDouble(splitLine[1])));
        }
        return creditCards;
    }
}
