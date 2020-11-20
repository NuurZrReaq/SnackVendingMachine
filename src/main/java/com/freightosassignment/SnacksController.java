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

    public void buySnack(int slotIndex){
        Snack snack = snackList.get(slotIndex);
        snack.decrementQuantity();
        snackList.set(slotIndex,snack);
    }


}
