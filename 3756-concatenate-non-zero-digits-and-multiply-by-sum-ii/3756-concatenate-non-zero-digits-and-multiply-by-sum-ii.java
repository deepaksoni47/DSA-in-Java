class Solution {
    static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        // Count non-zero digits first
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') count++;
        }

        // Store positions and non-zero digits
        int[] pos = new int[count];
        int[] digits = new int[count];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch != '0') {
                pos[idx] = i;
                digits[idx] = ch - '0';
                idx++;
            }
        }

        // Precompute powers of 10
        long[] pow10 = new long[count + 1];
        pow10[0] = 1;
        for (int i = 1; i <= count; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Prefix for concatenated number and sum of digits
        long[] prefNum = new long[count + 1];
        long[] prefSum = new long[count + 1];

        for (int i = 0; i < count; i++) {
            prefNum[i + 1] = (prefNum[i] * 10 + digits[i]) % MOD;
            prefSum[i + 1] = (prefSum[i] + digits[i]) % MOD;
        }

        int q = queries.length;
        int[] ans = new int[q];

        for (int i = 0; i < q; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);       // first pos >= l
            int right = upperBound(pos, r) - 1;  // last pos <= r

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            int len = right - left + 1;

            long sum = (prefSum[right + 1] - prefSum[left] + MOD) % MOD;

            long x = (prefNum[right + 1]
                    - (prefNum[left] * pow10[len]) % MOD
                    + MOD) % MOD;

            ans[i] = (int) ((x * sum) % MOD);
        }

        return ans;
    }

    // first index i such that arr[i] >= target
    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    // first index i such that arr[i] > target
    private int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] > target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}