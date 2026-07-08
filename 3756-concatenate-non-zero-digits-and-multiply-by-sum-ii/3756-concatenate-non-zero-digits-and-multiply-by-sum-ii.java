import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();

        // Collect non-zero digits and their original positions
        List<Integer> digitsList = new ArrayList<>();
        List<Integer> posList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                digitsList.add(d);
                posList.add(i);
            }
        }

        int n = digitsList.size();
        int[] digits = new int[n];
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            digits[i] = digitsList.get(i);
            pos[i] = posList.get(i);
        }

        // Precompute powers of 10
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // prefNum[i] = number formed by digits[0..i-1]
        long[] prefNum = new long[n + 1];
        // prefSum[i] = sum of digits[0..i-1]
        long[] prefSum = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefNum[i + 1] = (prefNum[i] * 10 + digits[i]) % MOD;
            prefSum[i + 1] = (prefSum[i] + digits[i]) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int l = queries[qi][0];
            int r = queries[qi][1];

            int L = lowerBound(pos, l);
            int R = upperBound(pos, r) - 1;

            if (L > R) {
                ans[qi] = 0;
                continue;
            }

            int len = R - L + 1;

            long x = (prefNum[R + 1] - (prefNum[L] * pow10[len]) % MOD + MOD) % MOD;
            long sum = (prefSum[R + 1] - prefSum[L] + MOD) % MOD;

            ans[qi] = (int) ((x * sum) % MOD);
        }

        return ans;
    }

    // first index i such that arr[i] >= target
    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }

    // first index i such that arr[i] > target
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}