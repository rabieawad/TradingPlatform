package org.boes.praktikum.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Good {
    //Constructor
    public Good(String id, String name) {
        this.id = id;
        this.name = name;
        this.kurs = new Kurs(5);
    }

    // every good can be identified by its specific ID
    String id;

    // every good has an easily understandable name
    String name;

    // kurs represents how the price of a good develops
    private Kurs kurs ;
}
