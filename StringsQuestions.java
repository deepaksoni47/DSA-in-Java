import java.util.*;
import java.util.Arrays;

public class StringsQuestions {
    public static boolean isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static float shortestPath(String str) {
        int x = 0, y = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'S') {
                y--;
            } else if (str.charAt(i) == 'N') {
                y++;
            } else if (str.charAt(i) == 'E') {
                x++;
            } else {
                x--;
            }
        }
        return (float) Math.sqrt(x * x + y * y);
    }

    public static String largest(String arr[]) {
        String largest = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(largest) > 0) {
                largest = arr[i];
            }
        }
        return largest;
    }

    public static String firstLetterUpper(String str) {
        StringBuilder sb = new StringBuilder("");
        sb.append(Character.toUpperCase(str.charAt(0)));
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ' ' && i < str.length() - 1) {
                sb.append(str.charAt(i));
                i++;
                sb.append(Character.toUpperCase(str.charAt(i)));

            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public static String stringCompression(String str) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            Integer count = 1;
            while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }
            sb.append(str.charAt(i));
            if (count > 1) {
                sb.append(count.toString());
            }
        }
        return sb.toString();
    }

    public static boolean anagram(String str1, String str2) {
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] st1 = str1.toCharArray();
        char[] st2 = str2.toCharArray();
        Arrays.sort(st1);
        Arrays.sort(st2);
        if (Arrays.equals(st1, st2)) {
            return true;
        } else {
            return false;
        }

    }

    public static void main(String[] args) {
        // System.out.println(isPalindrome("r"));
        // System.out.println(shortestPath("WNEENESENNN"));
        // String arr[] = { "mango", "apple" };
        // System.out.println(largest(arr));
        // System.out.println(firstLetterUpper("my name is deepak soni i am currently
        // studying in hell"));
        // System.out.println(stringCompression("aaaabbbbcccffdeeggggg"));
        System.out.println(anagram("race", "bcer"));
    }
}
