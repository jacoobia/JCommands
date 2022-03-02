package com.jacoobia.jcommands.util;

import java.util.Arrays;

/**
 * A small class of utility methods for command strings.
 *
 * @author Jacoobia - https://github.com/jacoobia
 */
public class StringUtils {

    /**
     * A constant for a completely blank string
     */
    public static String BLANK = "";

    /**
     * The all-in-one solution to ensure that a string
     *
     * has some kind of char value to it
     * @param string the string to check
     * @return boolean is the string empty, blank or null
     */
    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty() || isBlank(string);
    }

    /**
     * Checks if a string is filled with blank spaces
     *
     * rather than just an empty string
     * @param string the string to check
     * @return boolean is it blank or not
     */
    public static boolean isBlank(String string) {
        String[] arr = string.split("(?!^)");
        int length = string.length();
        int blanks = (int) Arrays.stream(arr).filter(" "::equals).count();
        return blanks == length;
    }

    /**
     * Checks if an array of strings contains a certain key
     *
     * @param array the array to search
     * @param key the key to search for
     * @return bool if the key is present
     */
    public static boolean stringArrayContains(String[] array, String key) {
        return Arrays.stream(array).anyMatch(s -> s.equalsIgnoreCase(key));
    }


}
