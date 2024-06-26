package org.careerjump.server.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Slf4j
public class LocalDateUtils {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parse(String dateString) {
        LocalDate result = LocalDate.parse(dateString, dateFormatter);
        log.debug("""
                LocalDate - parse
                Before : {}
                After : {}
                """, dateString, result);

        return result;
    }


    // LocalDate를 지정된 패턴의 String으로 변환하는 메서드
    public static String formatDate(LocalDate date) {
        String result = dateFormatter.format(date);
        log.debug("""
                String - formatDate
                Before : {}
                After : {}
                """, date, result);

        return result;
    }
}
