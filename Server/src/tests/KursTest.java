package org.boes.praktikum;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class KursTest {

    @Test
    void getCourseByTime() {
    var course = new Kurs(1);
    int min = LocalDateTime.now().getMinute();
    int hour = LocalDateTime.now().getHour();

        double c = course.getCourseByTime(min,hour);
        Double aDouble = course.getKursOverDay()[min + 60 * hour];
        assertEquals(aDouble, c);
    }
}