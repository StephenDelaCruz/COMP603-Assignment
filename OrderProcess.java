/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1;

/**
 *
 * @author mcste
 */
import java.io.*;
import java.util.*;

public class OrderProcess {

    private Inventory inventory;
    private Cart cart;
    private List<Order> orders;

    public OrderProcess() {
        inventory = new Inventory();
        cart = new Cart();
        orders = new ArrayList<>();
    }

    // Load products from inventory
    public void loadProducts(String filename) {
        try ( Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    int stock = Integer.parseInt(parts[2]); // Parse stock level from file
                    Product product = new Product(name, price, stock); // Initialize Product with stock level
                    inventory.addProduct(product);
                } else if (parts.length == 4) {
                    String name = parts[0];
                    String brand = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int stock = Integer.parseInt(parts[3]);
                    Phones phone = new Phones(name, brand, price, stock);
                    inventory.addPhones(phone);
                } else if (parts.length == 5) {
                    String name = parts[0];
                    String brand = parts[1];
                    int megaPixels = Integer.parseInt(parts[2]);
                    double price = Double.parseDouble(parts[3]);
                    int stock = Integer.parseInt(parts[4]);
                    Cameras camera = new Cameras(name, brand, megaPixels, price, stock);
                    inventory.addCameras(camera);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Product file not found.");
        }
    }

    // Display available products
// Display available products with stock levels
    public void displayProducts() {
        List<Product> products = inventory.getProducts();
        List<Phones> phones = inventory.getPhones();
        List<Cameras> cameras = inventory.getCameras();

        System.out.println("\nAvailable Products:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() + ": $" + product.getPrice() + " - Stock: " + product.getStock() + " - " + product.toString());
        }
        for (int i = 0; i < phones.size(); i++) {
            Phones phone = phones.get(i);
            System.out.println((products.size() + i + 1) + ". " + phone.getName() + " (" + phone.getBrand() + "): $" + phone.getPrice() + " - Stock: " + phone.getStock() + " - " + phone.toString());
        }
        for (int i = 0; i < cameras.size(); i++) {
            Cameras camera = cameras.get(i);
            System.out.println((products.size() + phones.size() + i + 1) + ". " + camera.getName() + " (" + camera.getBrand() + ")" + camera.getMegaPixels() + "mp: $" + camera.getPrice() + " - Stock: " + camera.getStock()+ " - " + camera.toString());
        }
        System.out.println();
    }

    // Add product to cart
    public void addToCart(int index) {
        List<Product> products = inventory.getProducts();
        List<Phones> phones = inventory.getPhones();
        List<Cameras> cameras = inventory.getCameras();

        if (index >= 0 && index < products.size()) {
            Product product = products.get(index);
            if (inventory.checkStock(product, 1)) {
                cart.addItem(product);
                System.out.println(product.getName() + " added to cart.");
            } else {
                System.out.println("\nSorry, " + product.getName() + " is currently out of stock.");
            }
        } else if (index >= products.size() && index < products.size() + phones.size()) {
            Phones phone = phones.get(index - products.size());
            if (inventory.checkStock(phone, 1)) {
                cart.addItem(phone);
                System.out.println(phone.getName() + " (" + phone.getBrand() + ") added to cart.");
            } else {
                System.out.println("\nSorry, " + phone.getName() + " (" + phone.getBrand() + ") is currently out of stock.");
            }
        } else if (index >= products.size() + phones.size() && index < products.size() + phones.size() + cameras.size()) {
            Cameras camera = cameras.get(index - products.size() - phones.size());
            if (inventory.checkStock(camera, 1)) {
                cart.addItem(camera);
                System.out.println(camera.getName() + " (" + camera.getBrand() + ")" + camera.getMegaPixels() + "mp, added to cart. " + camera.toString());
            } else {
                System.out.println("\nSorry, " + camera.getName() + " (" + camera.getBrand() + ")" + camera.getMegaPixels() + "mp, is currently out of stock.");
            }
        } else {
            System.out.println("Invalid product index.");
        }
    }

// Display items in the cart
    public void displayCart() {
        List<Product> items = cart.getItems();
        if (items.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            for (Product item : items) {
                if (item instanceof Phones) {
                    Phones phone = (Phones) item;
                    System.out.println("- " + phone.getName() + " (" + phone.getBrand() + "): $" + phone.getPrice() + " - " + phone.toString());
                } else if (item instanceof Cameras) {
                    Cameras camera = (Cameras) item;
                    System.out.println("- " + camera.getName() + " (" + camera.getBrand() + ")" + camera.getMegaPixels() + "mp: $" + camera.getPrice() + " - " + camera.toString());
                } else {
                    System.out.println("- " + item.getName() + ": $" + item.getPrice() + " - " + item.toString());
                }
            }
            System.out.println("Total: $" + cart.getTotalPrice());
        }
    }

    // Save the updated inventory to the product file
    public void saveInventoryToFile(String filename) {
        try ( FileWriter writer = new FileWriter(filename)) {
            List<Product> products = inventory.getProducts();
            List<Phones> phones = inventory.getPhones();
            List<Cameras> cameras = inventory.getCameras();
            
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getPrice() + "," + product.getStock() + "\n");
            }
            for (Phones phone : phones) {
                writer.write(phone.getName() + "," + phone.getBrand() + "," + phone.getPrice() + "," + phone.getStock() + "\n");
            }
            for (Cameras camera : cameras) {
                writer.write(camera.getName() + "," + camera.getBrand() + "," + camera.getMegaPixels() + "," + camera.getPrice() + "," + camera.getStock() + "\n");
            }
            
            System.out.println("Inventory updated and saved to file.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving inventory to file.");
        }
    }

    // Place order
    public void placeOrder(String username) {
        // Check if the cart is empty
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Please add items before placing an order.");
            return;
        }

        // Proceed with order placement
        List<Product> items = cart.getItems();
        double totalPrice = cart.getTotalPrice();
        Order order = new Order(getNextOrderNumber(), items, totalPrice, username); // Ensure to get next order number
        orders.add(order);

        // Update inventory and display stock after each order
        for (Product item : items) {
            inventory.updateStock(item, -1); // Decrease stock by 1 for each ordered item
            System.out.println("Stock for " + item.getName() + " updated to: " + item.getStock());
        }

        // Save inventory changes to file
        saveInventoryToFile("./rsc/Products.txt");

        // Clear cart
        cart.getItems().clear();
        System.out.println("Order placed successfully. Thank you for shopping with us!");
        System.out.println("Order ID: " + order.getOrderId()); // Display order ID

        // Save order to file
        saveOrderToFile(order);
    }

    private int getNextOrderNumber() {
        int nextOrderNumber = 0;
        try ( Scanner scanner = new Scanner(new File("./rsc/orders.txt"))) {
            while (scanner.hasNextLine()) {
                String[] orderInfo = scanner.nextLine().split(",");
                if (orderInfo.length > 0 && orderInfo[0].matches("\\d+")) {
                    nextOrderNumber = Math.max(nextOrderNumber, Integer.parseInt(orderInfo[0]));
                }
            }
            // Increment by 1 to get the next order number
            nextOrderNumber++;
        } catch (FileNotFoundException e) {
            System.out.println("Error: orders.txt file not found.");
        }
        return nextOrderNumber;
    }

    private void saveOrderToFile(Order order) {
        try ( PrintWriter writer = new PrintWriter(new FileWriter("./rsc/orders.txt", true))) {
            writer.println(order.toString());
            writer.println(); // Add an empty line between orders
        } catch (IOException e) {
            System.out.println("Error saving order to file: " + e.getMessage());
        }
    }
}
