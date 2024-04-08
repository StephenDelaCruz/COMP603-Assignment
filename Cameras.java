/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1;

/**
 *
 * @author steph
 */
public class Cameras extends Product{
    private String brand;
    private int megaPixels;
    
    public Cameras(String name, String brand, int megaPixels, double price, int stock){
        super(name, price, stock);
        this.brand = brand;
        this.megaPixels = megaPixels;
    }
    
    public String getBrand(){
        return this.brand;
    }
    
    public int getMegaPixels(){
        return this.megaPixels;
    }
    
    @Override
    public String toString(){
        return "You have 1 year of warranty on this item.";
    }
}
