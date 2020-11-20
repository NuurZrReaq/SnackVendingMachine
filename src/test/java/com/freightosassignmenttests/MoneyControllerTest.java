package com.freightosassignmenttests;
import com.freightosassignment.MoneyController;
import org.junit.jupiter.api.Test;

import javax.script.ScriptContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyControllerTest {
    MoneyController moneyController;
    Scanner cardsFile, mockConsole ;
    ArrayList<Boolean> mockDispenseFlag;


    public MoneyControllerTest() throws FileNotFoundException {
        moneyController = new MoneyController();
        mockDispenseFlag=new ArrayList<>();
        mockDispenseFlag.add(Boolean.FALSE);
        cardsFile = new Scanner(new File("cards.txt"));
        mockConsole = new Scanner(new File("readTest.txt"));
    }

    @Test
    void isCreditCardAvailableTest() {
        assertTrue(moneyController.isCreditCardAvailable("500ax"));
        assertFalse(moneyController.isCreditCardAvailable("500ap"));
    }

    @Test
    void buyWithCreditCardTest() {
        assertTrue(moneyController.buyWithCreditCard("500as",2.00));
        assertTrue(moneyController.buyWithCreditCard("500ad",2.00));
        assertFalse(moneyController.buyWithCreditCard("500ax",2.00));
        assertFalse(moneyController.buyWithCreditCard("500ai",2.00));
        assertTrue(moneyController.buyWithCreditCard("500aq",5.00));
        assertFalse(moneyController.buyWithCreditCard("500aq",2.00));

    }

    @Test
    void readTest()  {
        assertEquals(moneyController.read(5.0,mockConsole,mockDispenseFlag),15.0);
        assertTrue(mockDispenseFlag.get(0));
        mockDispenseFlag.set(0,Boolean.FALSE);
        assertEquals(moneyController.read(3.0,mockConsole,mockDispenseFlag),0);
        assertTrue(mockDispenseFlag.get(0));
        mockDispenseFlag.set(0,Boolean.FALSE);
        assertEquals(moneyController.read(4.0,mockConsole,mockDispenseFlag),46.0);
        assertTrue(mockDispenseFlag.get(0));
        mockDispenseFlag.set(0,Boolean.FALSE);
        assertEquals(moneyController.read(2.5,mockConsole,mockDispenseFlag),0.6);
        assertTrue(mockDispenseFlag.get(0));
        mockDispenseFlag.set(0,Boolean.FALSE);
        assertEquals(moneyController.read(1.5,mockConsole,mockDispenseFlag),0.75);
        assertTrue(mockDispenseFlag.get(0));
        mockDispenseFlag.set(0,Boolean.FALSE);
        assertEquals(moneyController.read(2.5,mockConsole,mockDispenseFlag),0.20);
        assertFalse(mockDispenseFlag.get(0));
        mockDispenseFlag.set(0,Boolean.FALSE);
        assertEquals(moneyController.read(2.5,mockConsole,mockDispenseFlag),0);
        assertFalse(mockDispenseFlag.get(0));
        mockDispenseFlag.set(0,Boolean.FALSE);

        assertEquals(moneyController.read(50,mockConsole,mockDispenseFlag),0);
        assertTrue(mockDispenseFlag.get(0));
        mockDispenseFlag.set(0,Boolean.FALSE);
        assertEquals(moneyController.read(50,mockConsole,mockDispenseFlag),0);
        assertFalse(mockDispenseFlag.get(0));
        mockDispenseFlag.set(0,Boolean.FALSE);
        assertEquals(moneyController.read(2.5,mockConsole,mockDispenseFlag),0);
        assertTrue(mockDispenseFlag.get(0));
        mockDispenseFlag.set(0,Boolean.FALSE);
        assertEquals(moneyController.read(700,mockConsole,mockDispenseFlag),0);
        assertFalse(mockDispenseFlag.get(0));



    }
}
