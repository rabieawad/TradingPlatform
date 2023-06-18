package org.boes.praktikum;

import lombok.Data;

@Data
public class Good {
    public Good(String id, String name) {
        this.id = id;
        this.name = name;
        this.kurs = new Kurs(5);
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Kurs getKurs() {
        return kurs;
    }

    private final String id;
    private final String name;
    private Kurs kurs ;
}
