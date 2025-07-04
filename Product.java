package com.example.ECommerce;

abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;
    
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    
    public boolean isAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity && !isExpired();
    }
    
    public void reduceQuantity(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
        }
    }
    
    public abstract boolean isExpired();
    public abstract boolean requiresShipping();
}
