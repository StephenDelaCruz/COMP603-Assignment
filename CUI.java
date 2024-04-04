/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1;

import java.util.Scanner;

/**
 *
 * @author mcste
 */
public class CUI {
    public static void main(String[] args) {
        OnlineShoppingSystem system = new OnlineShoppingSystem();
        system.loadProducts("./rsc/Products.txt");
        system.loadUsers();

        try ( Scanner scanner = new Scanner(System.in)) {
            boolean running = true; // Control flag for the loop
            while (running) {
                int choice = 0; // Default invalid choice
                if (!system.loggedIn) {
                    System.out.println("Welcome to Pear Store!");
                    System.out.println("\n1. Browse Products");
                    System.out.println("2. Log in");
                    System.out.println("3. Create User");
                    System.out.println("4. Exit\n");
                    System.out.print("Enter your choice(1-4): ");

                    // Handle non-numeric input
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                    } else {
                        scanner.next(); // Consume the invalid input
                        System.out.println("Please enter a valid number.");
                        continue; // Skip the rest of the loop iteration
                    }

                    switch (choice) {
                        case 1:
                            system.displayProducts();
                            break;
                        case 2:
                            System.out.print("Enter username: ");
                            String username = scanner.next();
                            System.out.print("Enter password: ");
                            String password = scanner.next();
                            if (system.authenticateUser(username, password)) {
                                System.out.println("\nLogin successful. Welcome to Pear Store, " + username + "!");
                            } else {
                                System.out.println("Invalid username or password. Please try again.");
                            }
                            break;
                        case 3:
                            System.out.print("Enter new username: ");
                            String newUsername = scanner.next();
                            System.out.print("Enter new password: ");
                            String newPassword = scanner.next();
                            System.out.print("Enter email: ");
                            String email = scanner.next();
                            system.createUser(newUsername, newPassword, email);
                            break;
                        case 4:
                            System.out.println("Thank you for visiting. Goodbye!");
                            running = false; // Set flag to false to exit loop
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                } else {
                    // When user is logged in
                    System.out.println("\n1. Browse Products");
                    System.out.println("2. Add to Cart");
                    System.out.println("3. View Cart");
                    System.out.println("4. Place Order");
                    System.out.println("5. Log out");
                    System.out.println("6. Exit\n");
                    System.out.print("Enter your choice(1-6): ");

                    // Handle non-numeric input within logged-in section
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                    } else {
                        scanner.next(); // Consume the invalid input
                        System.out.println("Please enter a valid number.");
                        continue; // Skip the rest of the loop iteration
                    }

                    switch (choice) {
                        case 1:
                            system.displayProducts();
                            break;
                        case 2:
                            System.out.print("Enter the product index to add to cart: ");
                            if (scanner.hasNextInt()) {
                                int index = scanner.nextInt() - 1;
                                system.addToCart(index);
                            } else {
                                System.out.println("Please enter a valid product index.");
                                scanner.next(); // Consume the non-integer input
                            }
                            break;
                        case 3:
                            system.displayCart();
                            break;
                        case 4:
                            system.placeOrder(system.currentUsername);
                            break;
                        case 5:
                            system.loggedIn = false;
                            system.currentUsername = null;
                            System.out.println("Logged out successfully.");
                            break;
                        case 6:
                            System.out.println("Thank you for shopping with us!");
                            running = false; // Set flag to false to exit loop
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }
    }
}
