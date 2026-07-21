class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int totalOnes = 0;
        int leftZeros = 0;
        int maxGain = 0;

        int i = 0;
        int n = s.length();

        while (i < n) {

            // Count current block of 1's
            int ones = 0;
            while (i < n && s.charAt(i) == '1') {
                ones++;
                totalOnes++;
                i++;
            }

            // Count current block of 0's
            int rightZeros = 0;
            while (i < n && s.charAt(i) == '0') {
                rightZeros++;
                i++;
            }

            // Valid pattern: 0... 1... 0...
            if (leftZeros > 0 && ones > 0 && rightZeros > 0) {
                maxGain = Math.max(maxGain, leftZeros + rightZeros);
            }

            // Current zero block becomes left zero block
            leftZeros = rightZeros;
        }

        return totalOnes + maxGain;
    }
}