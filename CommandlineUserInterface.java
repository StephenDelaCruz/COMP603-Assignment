/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import java.util.Scanner;

/**
 *
 * @author mcste
 */
public class CommandlineUserInterface {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Welcome user!");
        System.out.println("What would you like to do today?");
        System.out.println("(1) View all questions");
        System.out.println("(2) Add new question");
        System.out.println("(3) Remove question");
        System.out.println("(4) Take a quiz");
        System.out.println("(5) Exit the program");

        Scanner scan = new Scanner(System.in);

        while (true) {
            if (scan.hasNextInt()) {
                int input = scan.nextInt();

                if(input == 5){
                    System.out.println("See you next time!");
                    break;
                }
                
                switch (input) {
                    case 1:
                        System.out.println("questions...");
                        break;
                    case 2:
                        System.out.println("add...");
                        break;
                    case 3:
                        System.out.println("remove...");
                        break;
                    case 4:
                        System.out.println("quiz...");
                        break;
                    default:
                        System.out.println("Invalid input");
                }
            }
        }
        scan.close();
    }

}
