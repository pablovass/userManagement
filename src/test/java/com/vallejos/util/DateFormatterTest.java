package com.vallejos.util;

import static org.junit.jupiter.api.Assertions.*;

import com.vallejos.util.DateFormatter;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;

        import java.time.LocalDateTime;
        import java.time.format.DateTimeFormatter;

        import static org.junit.jupiter.api.Assertions.*;

public class DateFormatterTest {

    private DateFormatter dateFormatter;

    @BeforeEach
    public void setUp() {
        dateFormatter = new DateFormatter();
    }

    @Test
    public void testFormatLocalDateTime() {

        LocalDateTime dateTime = LocalDateTime.of(2023, 10, 9, 15, 30, 0); // October 9, 2023 3:30 PM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy hh:mm:ss a");
        String expectedFormattedDate = dateTime.format(formatter);


        String formattedDate = dateFormatter.formatLocalDateTime(dateTime);


        assertEquals(expectedFormattedDate, formattedDate);
    }

    @Test
    public void testFormatLocalDateTimeWithNull() {

        String formattedDate = dateFormatter.formatLocalDateTime(null);


        assertNull(formattedDate);
    }
}
