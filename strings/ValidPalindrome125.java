import java.util.*;

class ValidPalindrome125 {
    public boolean isPalindrome(String s) {
        int a = 0;
        int n = s.length() - 1;

        while (a < n) {
            char start = s.charAt(a);
            char end = s.charAt(n);

            if (!Character.isLetterOrDigit(start)) {
                a++;
                continue;
            }

            if (!Character.isLetterOrDigit(end)) {
                n--;
                continue;
            }

            start = Character.toLowerCase(start);
            end = Character.toLowerCase(end);

            if (start != end) {
                return false;
            }

            a++;
            n--;
        }
        return true;
    }
}
