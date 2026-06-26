package models;

public class Transaction{
    public static int sequenceCode = 1;
    public int tcCode;
    public int quantity;
    public item item;
    public double totalPrice;
    public double finalPrice;

    
    public int getTransactionCode(){
        return tcCode;
    }

    public int getQuantity(){
        return quantity;
    }

    public item getItem(){
        return item;
    }

    public double getFinalPrice(){
        return finalPrice;
    }

    public Transaction(item item, int quantity, boolean isMember){
        this.tcCode = sequenceCode++;
        this.item = item;
        this.quantity = quantity;
        calculateTotal(isMember);
    }

    public void calculateTotal(boolean isMember){
        this.totalPrice = this.item.getPrice() * this.quantity;
        this.finalPrice = this.totalPrice;

        if (this.finalPrice > 500000){
            this.finalPrice = this.finalPrice - (this.finalPrice * 0.05);
        }

        if (isMember){
            this.finalPrice = this.finalPrice - (this.finalPrice * 0.02);
        }
    }
}
