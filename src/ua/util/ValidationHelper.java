package ua.util;

import java.time.LocalDate;

public class ValidationHelper {

    private ValidationHelper() {}

    public static void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or blank");
        }
    }

    public static void requirePositive(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException(fieldName + " must be positive");
        }
    }

    public static <T> void requireNonNull(T value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }

    public static void requireDateNotNull(LocalDate date, String fieldName) {
        if (date == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
    }
}
