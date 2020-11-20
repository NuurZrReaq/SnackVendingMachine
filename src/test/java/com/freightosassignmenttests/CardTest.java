package com.freightosassignmenttests;

import com.freightosassignment.Card;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    Card card = new Card("500az",600);

    @Test
    public void decrementBalanceTest (){
        assertEquals(card.decrementBalance(100.50),499.50);
        assertEquals(card.decrementBalance(800.00),499.50);
        assertEquals(card.decrementBalance(199.50),300.00);
    }
}
