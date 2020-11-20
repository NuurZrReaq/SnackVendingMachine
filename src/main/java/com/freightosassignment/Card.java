package com.freightosassignment;

public class Card {
    private String cardId;
    private double balance;

    public Card(String cardId, double balance) {
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
        if(!(obj instanceof Card))
            return false;
        Card card = (Card)obj;
        if(this.cardId.equals(card.getCardId()))
            return true;

        return false;
    }
}
