package com.vallejos.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class DateFormatter
{
    public String formatLocalDateTime(LocalDateTime dateTime) {
        if (dateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy hh:mm:ss a");
            return dateTime.format(formatter);
        }
        return null;
    }

}
