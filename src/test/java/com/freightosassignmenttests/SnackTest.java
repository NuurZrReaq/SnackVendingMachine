package com.freightosassignmenttests;
import com.freightosassignment.Snack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnackTest {
    Snack snack = new Snack("twix",2.30,2);

    @Test
    public void decQuantityTest() {
        assertEquals(snack.decrementQuantity(),1);
        assertEquals(snack.decrementQuantity(),0);
        assertEquals(snack.decrementQuantity(),0);
    }
}
