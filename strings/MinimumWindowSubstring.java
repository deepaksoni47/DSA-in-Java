import java.util.*;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 ||
                s.length() < t.length()) {
            return new String();
        }
        int[] count = new int[126];
        for (int i = 0; i < t.length(); i++) {
            count[t.charAt(i)]++;
        }
        int required = t.length();
        int left = 0;
        int start = 0;
        int min = Integer.MAX_VALUE;
        for (int right = 0; right < s.length(); right++) {
            if (count[s.charAt(right)] > 0) {

                required--;
            }
            count[s.charAt(right)]--;
            while (required == 0) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    start = left;
                }
                count[s.charAt(left)]++;
                if (count[s.charAt(left)] > 0) {
                    required++;
                }
                left++;
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
    }
}
