package com.freightosassignment;

public class CreditCard {
    private String cardId;
    private double balance;

    public CreditCard(String cardId, double balance) {
        this.cardId = cardId;
        this.balance = balance;
    }

    public String getCardId() {
        return cardId;
    }

    public double getBalance() {
        return balance;
    }

    //Decrementing the balance of the credit card after the buying is done.
    public double decrementBalance(double decrementValue){
        if(decrementValue > this.balance )
            return this.balance;

        this.balance = this.balance - decrementValue;
        return this.balance;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CreditCard))
            return false;
        CreditCard creditCard = (CreditCard)obj;
        if(this.cardId.equals(creditCard.getCardId()))
            return true;

        return false;
    }
}
