package com.nagorniy.webservice.util;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Class for common string utils
 */
public class StringUtils {

    /**
     * Comparator for strings by the string length,
     * if they have the same length - will be compared by lexicographical comparison.
     */
    public static final Comparator<String> STRINGS_BY_LENGTH_AND_LEXICOGRAPHY_COMPARATOR = (o1, o2) -> {

        int o1LongestWordLength = o1.length();
        int o2LongestWordLength = o2.length();
        if (o1LongestWordLength == o2LongestWordLength) {
            return o2.compareTo(o1);
        } else {
            return o2LongestWordLength - o1LongestWordLength;
        }
    };

    /**
     * Regex delimiter
     */
    private static final String WORDS_DELIMITER = "\\s+";
    private static final String DEFAULT_VALUE = "";

    /**
     * Method to find the longest word in the string.
     * Note: {@param string} will be split by {@code WORDS_DELIMITER},
     * be sure it's not null. Otherwise {@code NullPointerException} will be thrown.
     *
     * @param string string to find longest word
     * @return the longest string after split
     */
    public static String getLongestWord(String string) {
        return Arrays.stream(string.split(WORDS_DELIMITER))
                .max(Comparator.comparingInt(String::length))
                .orElse(DEFAULT_VALUE);
    }
}
