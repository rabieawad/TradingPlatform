package org.boes.praktikum.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellRequest {
    String userName;
    String password;
    int amount ;
    String goodId;
    double price;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getAmount() {
        return amount;
    }

    public String getGoodId() {
        return goodId;
    }

    public double getPrice() {
        return price;
    }
}
