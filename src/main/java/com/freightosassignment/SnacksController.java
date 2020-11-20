package com.freightosassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnacksController {
    private File snacks;
    private List<Snack> snackList;

    public SnacksController()  {
        try {
            snackList = new ArrayList<>();
            snacks = new File("snacks.txt");
            snackList = fillSnackSlots(new Scanner(snacks));
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
    //Reads the snack list from a local file to mock a dataset.
    private List<Snack> fillSnackSlots(Scanner file) {
        List<Snack> snacks = new ArrayList<>();
        String line;
        String []splitLine ;
        for(int i=0; i<25; i++){
            try{
                line = file.nextLine();
            } catch (Exception exception){
                exception.printStackTrace();
                continue;
            }
            splitLine = line.split(" ");
            try {
                snacks.add(new Snack(splitLine[0],Double.parseDouble(splitLine[1]),Integer.parseInt(splitLine[2])));
            } catch (Exception exception){
                exception.printStackTrace();
            }

        }
        return snacks;
    }
    //returns true if the quantity of the provided snack > 0.
    public boolean isSnackAvailable(int slotIndex) {
        try {
            if (snackList.get(slotIndex).getQuantity() > 0)
                return true;
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return false;
    }
    // returns the snack object of index slotIndex.
    public Snack getSnackAtSlot(int slotIndex)  {
        Snack snack;
        try {
            snack = snackList.get(slotIndex);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        return snack;
    }

    //Decrements the quantity of the bought obj at index slotIndex and returns the new quantity.
    public int buySnack(int slotIndex)  {
        Snack snack;
        try{
            snack = snackList.get(slotIndex);
        }catch (Exception exception){
            exception.printStackTrace();
            return -1;
        }
        snack.decrementQuantity();
        snackList.set(slotIndex,snack);
        return snack.getQuantity();
    }
    //prints the whole snack list so the customer can choose from them.
    public void printSnackList(){
        int i =0;
        int row,col;
        System.out.println("-------------------------------------------------------------");
        for(i=0; i<5; i++){
            for (int j=0; j<5; j++){
                System.out.print(i+1+""+(j+1)+"\t\t");
            }
            System.out.print("\n");
            for (int j=0; j<5; j++){
                System.out.print(this.snackList.get(i*5 +j).getName()+"\t");
            }
            System.out.print("\n\n");
        }
        System.out.println("-------------------------------------------------------------");
        System.out.print("Please Enter the index of you chosen snack\n");
    }


}
