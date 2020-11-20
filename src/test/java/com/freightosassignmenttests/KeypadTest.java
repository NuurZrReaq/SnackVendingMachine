package com.freightosassignmenttests;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

public class KeypadTest {
    Scanner mockScanner;

    public KeypadTest() {
        try {
            mockScanner = new Scanner(new File("cards.txt"));
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    public void readTest(){
        assertEquals(mockScanner.nextLine(),"500ad 100.00");

    }
}
