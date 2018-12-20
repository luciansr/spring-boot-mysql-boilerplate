package com.company.boilerplate.services.util;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class DateHelper {
    public Date addMinutes(Date initial, int minutesToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(initial);
        calendar.add(Calendar.MINUTE, minutesToAdd);
        return calendar.getTime();
    }
}
