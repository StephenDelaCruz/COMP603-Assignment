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
        OrderProcess op = new OrderProcess();
        op.loadProducts("./rsc/Products.txt");
        
        UserManager um = new UserManager();
        um.loadUsers();

        try ( Scanner scan = new Scanner(System.in)) {
            boolean running = true; // Control flag for the loop
            while (running) {
                int choice = 0; // Default invalid choice
                if (!um.loggedIn) {
                    System.out.println("Welcome to Pear Store!");
                    System.out.println("\n1. Browse Products");
                    System.out.println("2. Log in");
                    System.out.println("3. Create User");
                    System.out.println("4. Exit\n");
                    System.out.print("Enter your choice(1-4): ");

                    // Handle non-numeric input
                    if (scan.hasNextInt()) {
                        choice = scan.nextInt();
                    } else {
                        scan.next(); // Consume the invalid input
                        System.out.println("Please enter a valid number.");
                        continue; // Skip the rest of the loop iteration
                    }

                    switch (choice) {
                        case 1:
                            op.displayProducts();
                            break;
                        case 2:
                            System.out.print("Enter username: ");
                            String username = scan.next();
                            System.out.print("Enter password: ");
                            String password = scan.next();
                            if (um.authenticationOfUser(username, password)) {
                                System.out.println("\nLogin successful. Welcome to Pear Store, " + username + "!");
                            } else {
                                System.out.println("Invalid username or password. Please try again.");
                            }
                            break;
                        case 3:
                            System.out.print("Enter new username: ");
                            String newUsername = scan.next();
                            System.out.print("Enter new password: ");
                            String newPassword = scan.next();
                            System.out.print("Enter email: ");
                            String email = scan.next();
                            um.createUser(newUsername, newPassword, email);
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
                    if (scan.hasNextInt()) {
                        choice = scan.nextInt();
                    } else {
                        scan.next(); // Consume the invalid input
                        System.out.println("Please enter a valid number.");
                        continue; // Skip the rest of the loop iteration
                    }

                    switch (choice) {
                        case 1:
                            op.displayProducts();
                            break;
                        case 2:
                            System.out.print("Enter the product index to add to cart: ");
                            if (scan.hasNextInt()) {
                                int index = scan.nextInt() - 1;
                                op.addToCart(index);
                            } else {
                                System.out.println("Please enter a valid product index.");
                                scan.next(); // Consume the non-integer input
                            }
                            break;
                        case 3:
                            op.displayCart();
                            break;
                        case 4:
                            op.placeOrder(um.currentUsername);
                            break;
                        case 5:
                            um.loggedIn = false;
                            um.currentUsername = null;
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
//hello world
