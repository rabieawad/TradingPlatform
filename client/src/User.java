package org.boes.praktikum.client;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // Getter and setter methods:
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
    private List<Good> inventory;
    private float guthaben;


    private static  List<User> userList = List.of(
            new User("rabie","1234",null,1000),
            new User("linus","1234",null,1000),
            new User("hanna","1234",null,1000),
            new User("liala","1234",null,1000),
            new User("daria","1234",null,1000)
    );

    /**
     * return user object to a given username
     * @return user object that is identified by the specified UserName
     */
    static User getUserById (String UserName){
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
