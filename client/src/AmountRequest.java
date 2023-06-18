package org.boes.praktikum.client;

public class AmountRequest {
    String goodId;
    String userName;

    // basic constructor with all arguments
    public AmountRequest(String userName,String goodId) {
        this.userName = userName;
        this.goodId = goodId;
    }

    // Get/Set methods:

    public String getGoodId() {
        return goodId;
    }

    public String getUserName() {
        return userName;
    }


    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}