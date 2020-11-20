package com.freightosassignmenttests;

import com.freightosassignment.CreditCard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditCardTest {
    CreditCard creditCard = new CreditCard("500az",600);

    @Test
    public void decrementBalanceTest (){
        assertEquals(creditCard.decrementBalance(100.50),499.50);
        assertEquals(creditCard.decrementBalance(800.00),499.50);
        assertEquals(creditCard.decrementBalance(199.50),300.00);
    }
}
