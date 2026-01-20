// Intuition:
// Use sliding window with two pointers to track the longest substring without repeating characters.
// Three approaches are implemented below: using a Set, a HashMap, and a fixed-size int array.

// -------------------------
// Approach 1 - Using Set
// -------------------------
import java.util.*;

class Solution_Set {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        Set<Character> charSet = new HashSet<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            while (charSet.contains(s.charAt(right))) {
                charSet.remove(s.charAt(left));
                left++;
            }
            charSet.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}

// -------------------------------
// Approach 2 - Using HashMap
// -------------------------------
class Solution_HashMap {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        int left = 0;

        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            if (charMap.containsKey(c) && charMap.get(c) >= left) {
                left = charMap.get(c) + 1;
            }
            charMap.put(c, right);
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}

package strings;

// -------------------------------------
// Approach 3 - Using Integer Array
// -------------------------------------
class Solution_Array {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int[] charIndex = new int[128]; // Supports ASCII
        for (int i = 0; i < charIndex.length; i++) {
            charIndex[i] = -1;
        }
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (charIndex[s.charAt(right)] >= left) {
                left = charIndex[s.charAt(right)] + 1;
            }
            charIndex[s.charAt(right)] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
