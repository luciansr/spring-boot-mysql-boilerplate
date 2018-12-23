package com.company.boilerplate.services.util;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class DateHelper {
    public Date addMinutes(LocalDateTime initial, int minutesToAdd) {
        return localDateTimeToDate(initial.plusMinutes(minutesToAdd));
    }

    public Date localDateTimeToDate(LocalDateTime dateTime) {
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        Date date = Date.from(instant);
        return date;
    }

    public LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        return localDateTime;
    }
}
