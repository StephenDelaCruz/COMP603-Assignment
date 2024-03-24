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
        
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Welcome user!");
        System.out.println("What would you like to do today?");
        System.out.println("(1) View all questions");
        System.out.println("(2) Add new question");
        System.out.println("(3) Remove question");
        System.out.println("(4) Take a quiz");
        System.out.println("(5) Exit the program");
        
    }
    
}
