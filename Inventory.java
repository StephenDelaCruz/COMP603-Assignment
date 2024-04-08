/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1;

/**
 *
 * @author mcste
 */
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;
    private List<Phones> phones;
    private List<Cameras> cameras;

    public Inventory() {
        products = new ArrayList<>();
        phones = new ArrayList<>();
        cameras = new ArrayList<>();
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
    
    public void addPhones(Phones phone){
        phones.add(phone);
    }
    
    public List<Phones> getPhones(){
        return phones;
    }
    
    public void addCameras(Cameras camera){
        cameras.add(camera);
    }
    
    public List<Cameras> getCameras(){
        return cameras;
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
