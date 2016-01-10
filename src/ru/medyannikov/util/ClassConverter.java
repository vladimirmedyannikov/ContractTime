package ru.medyannikov.util;

import java.time.*;
import java.util.Date;

/**
 * Created by Vladimir on 05.01.2016.
 */
public abstract class ClassConverter {

    public static LocalDate convertToLocalDate(Date date){
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    }

    public static Date convertToDate(ZonedDateTime date){
        return new Date().from(Instant.from(date));
    }
}
