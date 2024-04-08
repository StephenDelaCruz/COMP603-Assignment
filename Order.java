/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1;

/**
 *
 * @author mcste
 */
import java.util.*;

public class Order {

    private int orderId; // Unique order ID
    private List<Product> items; // List of products in the order
    private double totalPrice; // Total price of the order
    private String username; // Username of the user who placed the order

    public Order(int orderId, List<Product> items, double totalPrice, String username) {
        this.orderId = orderId;
        this.items = items;
        this.totalPrice = totalPrice;
        this.username = username;
    }

    // Add getters and setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(orderId).append(",")
                .append(username).append(",")
                .append(items.toString()).append(",")
                .append(totalPrice);
        return sb.toString();
    }
}
