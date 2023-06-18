package org.boes.praktikum.client;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Kurs {



    Double[] kursOverDay = new Double[1440];
    public Kurs(int inv) {

        this.creatKurs();

    }

    public Double[] getKursOverDay() {
        return kursOverDay;
    }

    /**
     * creates today's Course
     * every minute has its own Course a day consists of 1440
     * @param
     */
    void creatKurs(){
        int start = 10;
        double tend = 0.2;
        double dt = 0.001;
        double control = 0.8;
        int number = 1440; //number of minutes a day
        double inter = 1;
        for (int i = 0; i < 1440; i++){
            double y = 2 * Math.random() - 2;
            double v = inter * (1 + tend * dt + control * Math.sqrt(dt) * y);
            inter = v;
            kursOverDay[i] = v;
        }
    }

    /**
     * outputs Course of a specific time point of the day
     * @param min
     * @param hour
     * @return
     */
    @SneakyThrows
    public double getCourseByTime(int min, int hour){
        if(min < 0 || min > 60 || hour < 0 || hour > 24)
            throw  new Exception("Unvalid time");
        int index = hour * 60 + min;



        return kursOverDay[index];

    }

}
