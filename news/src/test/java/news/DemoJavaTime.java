package news;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Stream;

public class DemoJavaTime {

    @Test
    void demoDate(){
        // Java 1.0
        var now = new Date();
        System.out.println(now);
    }

    @Test
    void demoCalendar(){
        // Java 1.1: Calendar/GregorianCalendar
        Calendar now = Calendar.getInstance();
        System.out.println(now);
    }

    @Test
    void demoLocalDate(){
        // Java 8
        LocalDate today = LocalDate.now();
        LocalDate february29 = LocalDate.of(2024, 2, 29);
        LocalDate nextDate = february29.plusDays(31);
        Stream.of(today, february29, nextDate)
                .forEach(System.out::println);
        // NB: ToString, parse: ISO Format
        String dateIsoStr = "2024-03-22";
        String dateFrStr = "22/03/2024";
        DateTimeFormatter frDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.parse(dateIsoStr);
        LocalDate date2 = LocalDate.parse(dateFrStr, frDateFormat);
        Stream.of(date1, date2)
                .forEach(d -> {
                    System.out.println(" - " + d
                            + "  " + d.format(frDateFormat));
                });
    }

    @Test
    void demoDateTime(){
        // Java 8: with or without timezone
        var nowLocal = LocalDateTime.now();
        System.out.println(nowLocal);
        var nowTzLocal = ZonedDateTime.now();
        System.out.println(nowTzLocal);
        var tzSydney = ZoneId.of("Australia/Sydney");
        var nowSydney = ZonedDateTime.now(tzSydney);
        System.out.println(nowSydney);
    }

}
