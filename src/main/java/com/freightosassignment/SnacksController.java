package com.freightosassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SnacksController {
    private File snacks;
    private List<Snack> snackList;

    public SnacksController() throws FileNotFoundException {
        snackList = new ArrayList<>();
        snacks = new File("snacks.txt");
        snackList = fillSnackSlots(new Scanner(snacks));
    }

    private List<Snack> fillSnackSlots(Scanner file) {
        List<Snack> snacks = new ArrayList<>();
        String line;
        String []splitLine ;
        for(int i=0; i<25; i++){
            line = file.nextLine();
            splitLine = line.split(" ");
            snacks.add(new Snack(splitLine[0],Double.parseDouble(splitLine[1]),Integer.parseInt(splitLine[2])));
        }
        return snacks;
    }

    public boolean isSnackAvailable(int slotIndex) {
        if(snackList.get(slotIndex).getQuantity()>0)
            return true;

        return false;
    }

    public Snack getSnackAtSlot(int slotIndex){
        return snackList.get(slotIndex);
    }

    public int buySnack(int slotIndex){
        Snack snack = snackList.get(slotIndex);
        snack.decrementQuantity();
        snackList.set(slotIndex,snack);
        return snack.getQuantity();
    }
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
