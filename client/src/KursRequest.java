package org.boes.praktikum.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KursRequest {

    // hour of interest (price at specified time is to be send by server)
    int hour;

    // minute of interest
    int min;

    // unique identification for good object
    String GoodId;

    // constructor, that sets time variables to current hour/minute
    public KursRequest(String GoodId){
        LocalDateTime tmp = LocalDateTime.now();
        this.hour = tmp.getHour();
        this.min = tmp.getMinute();
        this.GoodId = GoodId;
    }
}
