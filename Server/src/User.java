package org.boes.praktikum;

import java.util.ArrayList;
 import java.util.List;
import java.util.Objects;

public class User {
    public List<Good> getInventory() {
        return inventory;
    }

    public void setGuthaben(float guthaben) {
        this.guthaben = guthaben;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public float getGuthaben() {
        return guthaben;
    }

    static List<User> getUserList(){
        return userList;
    }
    private String username;
    private String password;
    private List<Good> inventory = new ArrayList<>();
    private float guthaben;

    public User(String username, String password,  float guthaben) {
        this.username = username;
        this.password = password;
         this.guthaben = guthaben;
    }
    //List of all users registered on the website
    public static  List<User> userList = new ArrayList<>();


    /**
     * outputs a user given his username
     * @param UserName
     * @return
     */
    public static User getUserById (String UserName){
        for(User user: getUserList()){
            if(Objects.equals(user.getUsername(), UserName))
                return user;
        }
        return null;
        //return getUserList().stream()
        //        .filter(user -> Objects.equals(user.getUsername(), UserName))
        //        .findAny()
        //        .orElseThrow();
    }

}
