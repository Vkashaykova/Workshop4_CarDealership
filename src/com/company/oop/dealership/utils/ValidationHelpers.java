package com.company.oop.dealership.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelpers {

    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d; received: %d.";
    public static final String MAKE_NAME_LEN_ERR = "%s must be between %d and %d characters long!";
    public static final String PRICE_VAL_ERR = "Price must be between %.1f and %.1f!";

    public static void validateIntRange(int value, int min, int max, String type) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(String.format(type, min, max));
        }
    }

    public static void validateIntRange1(int value, int min, int max, String type) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(String.format(MAKE_NAME_LEN_ERR, type, min, max));
        }
    }

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String type) {
        validateIntRange1(stringToValidate.length(), minLength, maxLength, type);
    }

    public static void validateDecimalRange(double value, double min, double max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(String.format(PRICE_VAL_ERR, min, max));
        }
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters) {
        if (list.size() < expectedNumberOfParameters) {
            throw new IllegalArgumentException(
                    String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedNumberOfParameters, list.size())
            );
        }
    }

    public static void validatePattern(String value, String pattern, String message) {
        Pattern patternToMatch = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternToMatch.matcher(value);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(message);
        }
    }
}
