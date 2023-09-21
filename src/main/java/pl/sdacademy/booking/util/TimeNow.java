package pl.sdacademy.booking.util;

import java.time.Clock;
import java.time.LocalDateTime;

public class TimeNow {      //klasa do kontrolowania czasu w testach, lepszy CLOCK
    public LocalDateTime now() {
        System.out.println("Calling base class for passingTimeControl");
        return LocalDateTime.now();
    }
}
