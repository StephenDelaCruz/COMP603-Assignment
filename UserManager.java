/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.*;

/**
 *
 * @author steph
 */
public class UserManager {

    List<User> users;
    boolean loggedIn;
    String currentUsername;
    
    public UserManager(){
        users = new ArrayList<>();
        currentUsername = null;
    }
    
    public void loadUsers(){
        try(Scanner scan = new Scanner(new File("./rsc/Users.txt"))){
            users = new ArrayList<>();
            while(scan.hasNextLine()){
                String[] userInfo = scan.nextLine().split(",");
                if(userInfo.length == 3){
                    String username = userInfo[0];
                    String pass = userInfo[1];
                    String email = userInfo[2];
                    users.add(new User(username, pass, email));
                } else {
                    System.out.println("Invalid format for user information: " + Arrays.toString(userInfo));
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("Error, text file Users.txt not found.");
        }
    }
    
    public void createUser(String username, String password, String email){
        User newUser = new User(username, password, email);
        users.add(newUser);
        saveUsers("./rsc/Users.txt");
    }
    
    public void saveUsers(String filename){
        try(FileWriter fw = new FileWriter(filename)){
            for(User user : users){
                fw.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "\n");
            }
            System.out.println("User information saved successfully.");
        } catch (IOException e){
            System.out.println("Error saving users data.");
        }
    }
    
    public boolean authenticationOfUser(String username, String password){
        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                loggedIn = true;
                currentUsername = username;
                return true;
            }
        }
        return false;
    }
}
