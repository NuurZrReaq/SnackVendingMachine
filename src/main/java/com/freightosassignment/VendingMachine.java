package com.freightosassignment;

import java.io.FileNotFoundException;

public class VendingMachine {
    protected MoneyController moneyController;
    protected Keypad keypad;

    public VendingMachine() throws FileNotFoundException {
        moneyController = new MoneyController();
        keypad = new Keypad();
    }

    public MoneyController getMoneyController() {
        return moneyController;
    }

    public Keypad getKeypad() {
        return keypad;
    }

}
