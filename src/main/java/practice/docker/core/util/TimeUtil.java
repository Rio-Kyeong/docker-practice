package practice.docker.core.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TimeUtil {

    private static final DateTimeFormatter STANDARD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    private static final DateTimeFormatter UTC_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private static final DateTimeFormatter HH_MM_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter H_M_FORMATTER = new DateTimeFormatterBuilder()
        .appendPattern("H:m")
        .toFormatter();

    private static final DateTimeFormatter YYYY_MM_DD_MINUS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static final DateTimeFormatter YYYY_MM_DD_SLASH_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    private static final DateTimeFormatter[] YYYY_MM_DD_FORMATTERS = new DateTimeFormatter[]{YYYY_MM_DD_MINUS_FORMATTER, YYYY_MM_DD_SLASH_FORMATTER};

    public static LocalTime toLocalTime(Integer hour, Integer minutes) {
        if (hour == null || minutes == null) {
            return null;
        }
        try {
            return LocalTime.of(hour, minutes);
        } catch (DateTimeException e) {
            return null;
        }
    }

    public static LocalTime toLocalTime(String localTime) {
        if (localTime == null) {
            return null;
        }
        try {
            return LocalTime.parse(localTime, H_M_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static LocalDate toLocalDate(Integer month, Integer day) {
        if (month == null || day == null) {
            return null;
        }
        try {
            return LocalDate.of(LocalDate.now()
                .getYear(), month, day);
        } catch (DateTimeException e) {
            return null;
        }
    }

    public static LocalDate toLocalDate(Integer year, Integer month, Integer day) {
        if (month == null || day == null) {
            return null;
        }
        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException e) {
            return null;
        }
    }

    public static LocalDate toLocalDate(String localDate) {
        if (localDate == null) {
            return null;
        }
        return Arrays
            .stream(YYYY_MM_DD_FORMATTERS)
            .map(formatter -> {
                try {
                    return LocalDate.parse(localDate, formatter);
                } catch (DateTimeParseException e) {
                    return null;
                }
            })
            .filter(Objects::nonNull)
            .findAny()
            .orElse(null);
    }

    public static LocalDateTime toLocalDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, STANDARD_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static String toString(ZonedDateTime zonedDateTime) {
        try {
            if (zonedDateTime.getZone()
                .equals(ZoneOffset.UTC)) {
                return zonedDateTime.format(UTC_FORMATTER);
            } else {
                return zonedDateTime.format(STANDARD_FORMATTER);
            }
        } catch (DateTimeException e) {
            return null;
        }
    }

    public static String toString(LocalTime time) {
        if (time == null) {
            return null;
        }
        try {
            return time.format(HH_MM_FORMATTER);
        } catch (DateTimeException e) {
            return null;
        }
    }
}
