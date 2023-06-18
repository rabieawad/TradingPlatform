package org.boes.praktikum;

public class AddFundRequest {
    public String getUserId() {
        return userId;
    }

    public float getValue() {
        return value;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setValue(float value) {
        this.value = value;
    }

    private String userId;
    private float value;

}
