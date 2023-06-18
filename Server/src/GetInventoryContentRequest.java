package org.boes.praktikum;

public class GetInventoryContentRequest {

    String userName ;
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
