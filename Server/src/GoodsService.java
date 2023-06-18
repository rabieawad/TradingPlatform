package org.boes.praktikum;

import lombok.Getter;

import java.util.List;

public class GoodsService {
    private static final GoodsService instance = new GoodsService();
    @Getter
    //list of goods available on the Website
    private static final List<Good> goods = List.of(
            new Good("dimond","Dimond"),
            new Good("steel","Steel"),
            new Good("gold","Gold"),
            new Good("silver","Silver"),
            new Good("wood","Wood"),
            new Good("kidny","Kidny")
    );
    private GoodsService(){
    }

    public static GoodsService getInstance() {
        return instance;
    }

    /**
     * finds a good by its Id
     * @param id
     * @return
     */
    public static Good getGood(String id) {
        return goods.stream().filter(good -> good.getId().equals(id)).findAny().orElseThrow();
    }
}
