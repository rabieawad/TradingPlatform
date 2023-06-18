package org.boes.praktikum;

public class BuyRequest {
    String goodId;
    String userName;
    String password;
    int amount;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getGoodId() {
        return goodId;
    }

    public String getUserName() {
        return userName;
    }

    public int getAmount() {
        return amount;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
