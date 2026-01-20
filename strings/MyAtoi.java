import java.util.*;

class MyAtoi {
    public int myAtoi(String s) {
        int i = 0, n = s.length();
        int sign = 1;
        long result = 0;

        while (i < n && s.charAt(i) == ' ') {
            i++;
        }
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }
        while (i < n && Character.isDigit(s.charAt(i))) {
            result = result * 10 + (s.charAt(i) - '0');

            if (sign * result > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            if (sign * result < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            i++;

        }

        return (int) (sign * result);

    }
}
/*
 * Given a string s, the objective is to convert it into integer format without
 * utilizing any built-in functions. Refer the below steps to know about atoi()
 * function.
 * 
 * Cases for atoi() conversion:
 * 
 * Skip any leading whitespaces.
 * Check for a sign (‘+’ or ‘-‘), default to positive if no sign is present.
 * Read the integer by ignoring leading zeros until a non-digit character is
 * encountered or end of the string is reached. If no digits are present, return
 * 0.
 * If the integer is greater than 231 – 1, then return 231 – 1 and if the
 * integer is smaller than -231, then return -231.
 */