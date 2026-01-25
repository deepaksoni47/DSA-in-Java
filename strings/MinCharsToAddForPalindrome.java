public class MinCharsToAddForPalindrome {
    public int minChar(String s) {
        int n = s.length();

        // Step 1: reverse the string
        String rev = new StringBuilder(s).reverse().toString();

        // Step 2: create combined string
        String combined = s + "#" + rev;

        // Step 3: build LPS array
        int[] lps = buildLPS(combined);

        // Step 4: result
        return n - lps[combined.length() - 1];
    }

    private int[] buildLPS(String str) {
        int n = str.length();
        int[] lps = new int[n];

        int len = 0; // length of previous longest prefix suffix
        int i = 1;

        while (i < n) {
            if (str.charAt(i) == str.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
}
/*
 * Problem restated (very precisely)
 * 
 * You are given a string s
 * 
 * You can add characters only at the front
 * 
 * Goal: make the string a palindrome
 * 
 * Return the minimum number of characters to add
 * 
 * Key insight (this decides the whole problem)
 * 
 * If you are allowed to add characters only at the front, then:
 * 
 * You should not disturb the longest palindromic prefix that already exists.
 * 
 * Everything after that prefix must be mirrored and added in front.
 * 
 * So the real problem is:
 * 
 * Find the length of the longest prefix of s that is already a palindrome.
 * 
 * Why brute force is not acceptable
 * 
 * Brute approach:
 * 
 * Check every prefix
 * 
 * Verify palindrome for each prefix
 * 
 * Worst case:
 * 
 * O(n^2) → too slow for large strings
 * 
 * We need O(n).
 * 
 * Trick used (this is the core idea)
 * 
 * We use KMP (LPS array) in a clever way.
 * 
 * Step 1: Reverse the string
 * rev = reverse(s)
 * 
 * Step 2: Build a new string
 * combined = s + "#" + rev
 * 
 * 
 * Why #?
 * 
 * Separator that never appears in s
 * 
 * Prevents false overlaps
 * 
 * What does this achieve?
 * 
 * The LPS (Longest Prefix Suffix) value of the last index of combined gives:
 * 
 * Length of the longest prefix of s that matches a suffix of rev
 * 
 * That exactly corresponds to:
 * 
 * Longest palindromic prefix of s
 * 
 * Final formula
 * minCharsToAdd = s.length() - longestPalindromicPrefixLength
 * 
 * Example (quick sanity check)
 * s = "AACECAAAA"
 * rev = "AAAACECAA"
 * 
 * combined = "AACECAAAA#AAAACECAA"
 * LPS last value = 7
 * 
 * Answer = 9 - 7 = 2
 * 
 * 
 * Correct.
 */