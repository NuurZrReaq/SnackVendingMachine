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

    public void decrementBalance(double decrementValue){
        this.balance = this.balance - decrementValue;
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
