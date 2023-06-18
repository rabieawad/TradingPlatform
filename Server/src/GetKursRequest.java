package org.boes.praktikum;

public class GetKursRequest {
    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public String getGoodId() {
        return goodId;
    }

    private int hour;
  private   int min;
  private   String goodId;
}
