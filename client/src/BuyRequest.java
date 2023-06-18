package org.boes.praktikum.client;

public class BuyRequest {
    String goodId;
    String userName;

    String password;
    int amount;

    // Basic constructor with all arguments passed
    public BuyRequest(String userName, String password,String goodId,int amount) {
        this.userName = userName;
        this.password = password;
        this.goodId = goodId;
        this.amount = amount;
    }

    // Getter/Setter Methods:
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
