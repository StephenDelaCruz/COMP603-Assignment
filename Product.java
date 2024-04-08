/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1;

/**
 *
 * @author mcste
 */
public class Product {
    private String name;
    private double price;
    private int stock;


    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }


    
       // Method to get stock level
    public int getStock() {
        return stock;
    }

    // Method to update stock level
    public void updateStock(int quantity) {
        stock -= quantity;
    }
    
    public String toString(){
        return "You have 2 years of warranty on this item.";
    }
}
