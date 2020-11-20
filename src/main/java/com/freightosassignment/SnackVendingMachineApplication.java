package com.freightosassignment;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SnackVendingMachineApplication {

    public static void main(String[] args){
        Scanner console = new Scanner (System.in);
        String snackIndexString;
        String []splitSnackIndexString;
        SnacksController snacksController = null;
        MoneyController moneyController = null;
        ArrayList<Boolean> dispenseSnack = new ArrayList<>();
        double moneyToReturn = 0;
        Snack snack;
        int snackIndex ;
        try {
            SnackVendingMachine snackVendingMachine = new SnackVendingMachine();
            snacksController = snackVendingMachine.getSnacksController();
            moneyController = snackVendingMachine.getMoneyController();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int row, col;
        while(true){
            dispenseSnack.add(Boolean.FALSE);
            snackIndexString = Keypad.read(console);
            splitSnackIndexString = snackIndexString.split("");
            row = Integer.parseInt(splitSnackIndexString[0]);
            col = Integer.parseInt(splitSnackIndexString[1]);
            if(row <1 || row >5 ){
                System.out.println("Please enter a valid Index");
                continue;
            }
            if(col <1 || col >5) {
                System.out.println("Please enter a valid Index");
                continue;
            }
            snackIndex = (row -1 ) *5 + col -1;

            snack = snacksController.getSnackAtSlot(snackIndex);
            if( !snacksController.isSnackAvailable(snackIndex)){
                System.out.println(snack.getName() + " is empty at this index please choose another one");
                continue;
            }
            System.out.println("The price of "+snack.getName()+" is "+ snack.getPrice());
            moneyToReturn = moneyController.read(snack.getPrice(),console,dispenseSnack);
            if(dispenseSnack.get(0)) {
                System.out.println("Please pick your snack");
                snacksController.buySnack(snackIndex);
            }
            else {
                System.out.println("Please pay enough money");
            }
            System.out.println("Money to be returned is "+ moneyToReturn);



        }

    }

}
