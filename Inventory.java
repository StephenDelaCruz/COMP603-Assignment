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
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    // Adjusted checkStock method to use stock level
    public boolean checkStock(Product product, int quantity) {
        return product.getStock() >= quantity;
    }

    // Adjusted updateStock method to decrement stock level
    public void updateStock(Product product, int quantity) {
        product.updateStock(-quantity);
    }
}