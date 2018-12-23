package com.company.boilerplate.services.util;

import org.apache.tomcat.jni.Local;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateHelperTests {

    @Test
    public void convert_LocalDateTimeToDate_Test() {
        DateHelper dateHelper = new DateHelper();

        Date expected = new Date();
        LocalDateTime localDateTime =  Instant.ofEpochMilli(expected.getTime()).atZone(ZoneId.of("UTC")).toLocalDateTime();
        Date result = dateHelper.localDateTimeToDate(localDateTime);

        assertEquals(expected, result);
    }

    @Test
    public void convert_DateToLocalDateTime_Test() {
        DateHelper dateHelper = new DateHelper();

        Date inputDate = new Date();
        LocalDateTime expected =  Instant.ofEpochMilli(inputDate.getTime()).atZone(ZoneId.of("UTC")).toLocalDateTime();
        LocalDateTime result = dateHelper.dateToLocalDateTime(inputDate);

        assertEquals(expected, result);
    }

    @Test
    public void add_30Minutes_Test() {
        DateHelper dateHelper = new DateHelper();

        Date expected = new Date(1500001800000L);
        Date result = dateHelper.addMinutes(LocalDateTime.ofInstant(Instant.ofEpochMilli(1500000000000L), ZoneId.of("UTC")), 30);

        assertEquals(expected, result);
    }
}
