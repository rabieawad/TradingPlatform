package org.boes.praktikum.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInventoryContentRequest {

    String userName;
    String passward;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassward() {
        return passward;
    }
}