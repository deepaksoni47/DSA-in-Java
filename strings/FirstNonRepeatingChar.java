class Solution {
    public char nonRepeatingChar(String s) {
        int[] freq = new int[256];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i)] == 1) {

                return s.charAt(i);
            }
        }
        return '$';
    }
}

public class FirstNonRepeatingChar {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String input = "swiss";
        char result = solution.nonRepeatingChar(input);
        System.out.println("The first non-repeating character is: " + result);
    }
}