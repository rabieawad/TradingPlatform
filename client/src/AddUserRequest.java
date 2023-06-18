package org.boes.praktikum.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {

    private String username;
    private String password;
     private float guthaben;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
