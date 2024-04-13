/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment1;

import java.io.*;
import java.util.*;

/**
 *
 * @author steph
 */
public class UserManager {

    Map<String, User> users;
    boolean loggedIn;
    String currentUsername;
    
    public UserManager(){
        users = new HashMap<>();
        currentUsername = null;
    }
    
    public void loadUsers(){
        try(BufferedReader reader = new BufferedReader(new FileReader("./rsc/Users.txt"))){
            String line;
            while((line = reader.readLine()) != null){
                String[] userInfo = line.split(",");
                if(userInfo.length == 3){
                    String username = userInfo[0];
                    String pass = userInfo[1];
                    String email = userInfo[2];
                    users.put(username, new User(username, pass, email));
                } else {
                    System.out.println("Invalid format for user information: " + Arrays.toString(userInfo));
                }
            }
        } catch (IOException e){
            System.out.println("Error, IOException.");
        }
    }
    
    public void createUser(String username, String password, String email){
        User newUser = new User(username, password, email);
        users.put(username, newUser);
        saveUsers("./rsc/Users.txt");
    }
    
    //ChatGPT helped us with this code
    public void saveUsers(String filename){
        try(FileWriter fw = new FileWriter(filename)){
            for(User user : users.values()){
                fw.write(user.getUsername() + "," + user.getPassword() + "," + user.getEmail() + "\n");
            }
            System.out.println("User information saved successfully.");
        } catch (IOException e){
            System.out.println("Error saving users data.");
        }
    }
    
    //ChatGPT helped us with this code
    public boolean authenticationOfUser(String username, String password){
        for(User user : users.values()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                loggedIn = true;
                currentUsername = username;
                return true;
            }
        }
        return false;
    }
}
