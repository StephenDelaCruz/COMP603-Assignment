/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1;

/**
 *
 * @author steph
 */
public class Phones extends Product{
    private String brand;
    
    public Phones(String name, String brand, double price, int stock){
        super(name, price, stock);
        this.brand = brand;
    }
    
    public String getBrand(){
        return this.brand;
    }
    
    @Override
    public String toString(){
        return "You have 3 years of warranty on this item.";
    }
}
