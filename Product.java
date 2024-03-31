/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Task02_3;

/**
 *
 * @author aweso
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
}
