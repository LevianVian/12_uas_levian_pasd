package models;

public class item{
    public String name;
    public String code;
    public double price; 
    public int stock;

    public item(String code, String name, double price, int stock) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void decreaseStock(int amount) {
        if (this.stock >= amount) {
            this.stock = this.stock - amount;
        } else {
            System.out.println("Maaf, stok barangnya tidak mencukupi");
        }
    }
}