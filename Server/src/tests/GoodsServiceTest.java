package org.boes.pratikum;

import org.boes.praktikum.GoodsService;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoodsServiceTest {

    @Test
    void getGood() {
       Assertions.assertEquals(GoodsService.getGood("steel").getName(),"Steel");
       assertEquals(GoodsService.getGood("dimond").getName(),"Dimond");
       assertEquals(GoodsService.getGood("gold").getName(),"Gold");
       assertEquals(GoodsService.getGood("wood").getName(),"Wood");
       assertEquals(GoodsService.getGood("kidny").getName(),"Kidny");

    }
}