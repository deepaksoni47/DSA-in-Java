public class PermutationExistIn2ndString {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        int left = 0;
        int needed = s1.length();
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
        }
        for (int right = 0; right < s2.length(); right++) {
            if ((freq[s2.charAt(right) - 'a']--) > 0) {
                needed--;
            }
            if (needed == 0) {
                return true;
            }
            if (right - left + 1 == s1.length()) {
                if (freq[s2.charAt(left) - 'a']++ >= 0) {
                    needed++;
                }
                left++;
            }
        }
        return false;
    }
}