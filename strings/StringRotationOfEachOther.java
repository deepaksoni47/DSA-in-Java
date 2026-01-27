public class StringRotationOfEachOther {
    public boolean areRotations(String s1, String s2) {
        // code here
        if (s1.length() != s2.length())
            return false;
        return kmp(s1 + s1, s2);

    }

    boolean kmp(String txt, String pat) {
        int[] lps = buildLps(pat);
        int i = 0;
        int j = 0;
        while (i < txt.length()) {
            if (txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
                if (j == pat.length())
                    return true;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return false;
    }

    int[] buildLps(String pat) {
        int[] lps = new int[pat.length()];
        int len = 0;
        int i = 1;
        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                lps[i++] = ++len;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }
}
/*
 * 2. Reusing work when the window slides
 * 
 * Key idea:
 * 
 * Maintain a data structure that holds candidates for max in the current
 * window.
 * 
 * When the window slides:
 * Remove elements that are out of the window.
 * Remove elements that are smaller than the new element (they can't be max).
 * The front of the data structure is always the max.
 * 
 * Data structure choice:
 * 
 * Deque (double-ended queue) is perfect for this.
 * 
 * Operations needed:
 * Add to back
 * Remove from front
 * Remove from back
 * Peek front
 * All operations are O(1).
 * 
 * Overall complexity:
 * Each element is added and removed at most once.
 * So total work is O(n).
 * 
 * ⟹ O(n) solution achieved!
 * Each element is added and removed at most once.
 * So total work is O(n).
 * ⟹ O(n) solution achieved!
 */