class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        int LIMIT = 1;
        while (LIMIT <= max) {
            LIMIT <<= 1;
        }

        boolean[] pair = new boolean[LIMIT];
        boolean[] ans = new boolean[LIMIT];

        // All XORs of two elements (i <= j)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                pair[nums[i] ^ nums[j]] = true;
            }
        }

        // Extend to three elements
        for (int x = 0; x < LIMIT; x++) {
            if (!pair[x]) continue;

            for (int num : nums) {
                ans[x ^ num] = true;
            }
        }

        int count = 0;
        for (boolean b : ans) {
            if (b) count++;
        }

        return count;
    }
}