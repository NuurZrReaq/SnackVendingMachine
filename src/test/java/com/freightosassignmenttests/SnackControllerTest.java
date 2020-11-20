package com.freightosassignmenttests;
import com.freightosassignment.SnacksController;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
public class SnackControllerTest {
    SnacksController snacksController;

    public SnackControllerTest()  {
        snacksController = new SnacksController();
    }

    @Test
    void isSnackAvailableTest()  {
        assertTrue(snacksController.isSnackAvailable(0));
    }

    @Test
    void getSnackAtSlotTest()  {
        assertEquals(snacksController.getSnackAtSlot(0).getName(),"Twix");
        assertEquals(snacksController.getSnackAtSlot(1).getName(),"KitKat");
        assertEquals(snacksController.getSnackAtSlot(2).getName(),"Doritos");
        assertEquals(snacksController.getSnackAtSlot(3).getName(),"Snickers");
    }

    @Test
    void buySnack()  {
        assertEquals(snacksController.buySnack(0),4);
        assertEquals(snacksController.buySnack(0),3);
        assertEquals(snacksController.buySnack(0),2);
        assertEquals(snacksController.buySnack(0),1);
        assertEquals(snacksController.buySnack(0),0);
        assertFalse(snacksController.isSnackAvailable(0));
    }
}
