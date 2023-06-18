package org.boes.praktikum.client;

public class AddFundRequest {
    /**
     * get userId
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * get value of variable "value"
     * @return value of funds to be added
     */
    public float getValue() {
        return value;
    }

    /**
     * set value of variable "UserId"
     * @param userId  new userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * set value of variable "value"
     * @param value new value of funds to be added
     */
    public void setValue(float value) {
        this.value = value;
    }

    /**
     * Constructor
     */
    public AddFundRequest(String userId,float value){
        this.userId = userId;
        this.value = value;
    }

    private String userId;
    private float value;

}
