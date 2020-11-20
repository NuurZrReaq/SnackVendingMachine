package com.freightosassignment;

import java.io.FileNotFoundException;

public class SnackVendingMachine extends VendingMachine {

    private SnacksController snacksController;

    public SnackVendingMachine() throws FileNotFoundException {
        super();
        snacksController = new SnacksController();

    }

    public SnacksController getSnacksController() {
        return snacksController;
    }
}
