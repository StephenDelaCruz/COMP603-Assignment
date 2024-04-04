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

public class OnlineShoppingSystem {

    private Inventory inventory;
    private Cart cart;
    private List<Order> orders;
    private List<User> users;
    boolean loggedIn; // Flag to track login status
    String currentUsername; // Store the username of the currently logged-in user

    public OnlineShoppingSystem() {
        inventory = new Inventory();
        cart = new Cart();
        orders = new ArrayList<>();
        users = new ArrayList<>();
        loggedIn = false;
        currentUsername = null;
    }

    // Load products from inventory
    public void loadProducts(String filename) {
        try ( Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                int stock = Integer.parseInt(parts[2]); // Parse stock level from file
                Product product = new Product(name, price, stock); // Initialize Product with stock level
                inventory.addProduct(product);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Product file not found.");
        }
    }

    // Load users from user.txt file
    public void loadUsers() {
        try ( Scanner scanner = new Scanner(new File("./rsc/Users.txt"))) {
            users = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String[] userInfo = scanner.nextLine().split(",");
                if (userInfo.length == 3) {
                    String username = userInfo[0];
                    String password = userInfo[1];
                    String email = userInfo[2];
                    users.add(new User(username, password, email));
                } else {
                    System.out.println("Invalid user information format: " + Arrays.toString(userInfo));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: users.txt file not found.");
        }
    }

    public void createUser(String username, String password, String email) {
        User newUser = new User(username, password, email);
        users.add(newUser);
        saveUsersToFile("./rsc/Users.txt");
    }

    private void saveUsersToFile(String filename) {
        try ( FileWriter writer = new FileWriter(filename)) {
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "\n");
            }
            System.out.println("Users data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving users data: " + e.getMessage());
        }
    }

    // Display available products
// Display available products with stock levels
    public void displayProducts() {
        List<Product> products = inventory.getProducts();
        System.out.println("\nAvailable Products:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() + " - $" + product.getPrice() + " - Stock: " + product.getStock());
        }
        System.out.println();
    }

    // Add product to cart
    public void addToCart(int index) {
        List<Product> products = inventory.getProducts();
        if (index >= 0 && index < products.size()) {
            Product product = products.get(index);
            if (inventory.checkStock(product, 1)) {
                cart.addItem(product);
                System.out.println(product.getName() + " added to cart.");
            } else {
                System.out.println("\nSorry, " + product.getName() + " is currently out of stock.");
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
                System.out.println("- " + item.getName() + " - $" + item.getPrice());
            }
            System.out.println("Total: $" + cart.getTotalPrice());
        }
    }

    // Save the updated inventory to the product file
    public void saveInventoryToFile(String filename) {
        try ( FileWriter writer = new FileWriter(filename)) {
            List<Product> products = inventory.getProducts();
            for (Product product : products) {
                writer.write(product.getName() + "," + product.getPrice() + "," + product.getStock() + "\n");
            }
            System.out.println("Inventory updated and saved to file.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving inventory to file: " + e.getMessage());
        }
    }

    // Place order
    public void placeOrder(String username) {
        // Check if the cart is empty
        if (cart.getItems().isEmpty()) {
            System.out.println("Your cart is empty. Please add items before placing an order.");
            return;
        }

        // Check if the user is authenticated
        boolean authenticated = false;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                authenticated = true;
                break;
            }
        }
        if (!authenticated) {
            System.out.println("Authentication failed. Please log in.");
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

    // Authenticate user
    public boolean authenticateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedIn = true;
                currentUsername = username;
                return true;
            }
        }
        return false;
    }
}