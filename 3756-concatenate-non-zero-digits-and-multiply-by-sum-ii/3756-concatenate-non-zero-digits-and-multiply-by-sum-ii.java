// class Solution {
//     static final long MOD = 1_000_000_007L;

//     public int[] sumAndMultiply(String s, int[][] queries) {
//         int n = s.length();

//         // Count non-zero digits first
//         int count = 0;
//         for (int i = 0; i < n; i++) {
//             if (s.charAt(i) != '0') count++;
//         }

//         // Store positions and non-zero digits
//         int[] pos = new int[count];
//         int[] digits = new int[count];

//         int idx = 0;
//         for (int i = 0; i < n; i++) {
//             char ch = s.charAt(i);
//             if (ch != '0') {
//                 pos[idx] = i;
//                 digits[idx] = ch - '0';
//                 idx++;
//             }
//         }

//         // Precompute powers of 10
//         long[] pow10 = new long[count + 1];
//         pow10[0] = 1;
//         for (int i = 1; i <= count; i++) {
//             pow10[i] = (pow10[i - 1] * 10) % MOD;
//         }

//         // Prefix for concatenated number and sum of digits
//         long[] prefNum = new long[count + 1];
//         long[] prefSum = new long[count + 1];

//         for (int i = 0; i < count; i++) {
//             prefNum[i + 1] = (prefNum[i] * 10 + digits[i]) % MOD;
//             prefSum[i + 1] = (prefSum[i] + digits[i]) % MOD;
//         }

//         int q = queries.length;
//         int[] ans = new int[q];

//         for (int i = 0; i < q; i++) {
//             int l = queries[i][0];
//             int r = queries[i][1];

//             int left = lowerBound(pos, l);       // first pos >= l
//             int right = upperBound(pos, r) - 1;  // last pos <= r

//             if (left > right) {
//                 ans[i] = 0;
//                 continue;
//             }

//             int len = right - left + 1;

//             long sum = (prefSum[right + 1] - prefSum[left] + MOD) % MOD;

//             long x = (prefNum[right + 1]
//                     - (prefNum[left] * pow10[len]) % MOD
//                     + MOD) % MOD;

//             ans[i] = (int) ((x * sum) % MOD);
//         }

//         return ans;
//     }

//     // first index i such that arr[i] >= target
//     private int lowerBound(int[] arr, int target) {
//         int lo = 0, hi = arr.length;
//         while (lo < hi) {
//             int mid = lo + (hi - lo) / 2;
//             if (arr[mid] >= target) hi = mid;
//             else lo = mid + 1;
//         }
//         return lo;
//     }

//     // first index i such that arr[i] > target
//     private int upperBound(int[] arr, int target) {
//         int lo = 0, hi = arr.length;
//         while (lo < hi) {
//             int mid = lo + (hi - lo) / 2;
//             if (arr[mid] > target) hi = mid;
//             else lo = mid + 1;
//         }
//         return lo;
//     }
// }
import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        // prefixDigitSum[i] = sum of all digits in s[0 .. i-1]
        int[] prefixDigitSum = new int[n + 1];

        // prefixNonZeroNumber[i] = number formed by concatenating all non-zero digits in s[0 .. i-1]
        long[] prefixNonZeroNumber = new long[n + 1];

        // prefixNonZeroCount[i] = count of non-zero digits in s[0 .. i-1]
        int[] prefixNonZeroCount = new int[n + 1];

        // pow10[i] = 10^i % MOD
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        // Build prefix arrays
        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            prefixDigitSum[i + 1] = prefixDigitSum[i] + digit;
            prefixNonZeroNumber[i + 1] = prefixNonZeroNumber[i];
            prefixNonZeroCount[i + 1] = prefixNonZeroCount[i];

            if (digit != 0) {
                prefixNonZeroNumber[i + 1] =
                        (prefixNonZeroNumber[i] * 10 + digit) % MOD;
                prefixNonZeroCount[i + 1]++;
            }
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1] + 1; // convert [l, r] to prefix range [l, r+1)

            // Number of non-zero digits inside s[left .. right-1]
            int nonZeroDigitsInRange =
                    prefixNonZeroCount[right] - prefixNonZeroCount[left];
            long prefixContribution =
                    (prefixNonZeroNumber[left] * pow10[nonZeroDigitsInRange]) % MOD;

            long queryNumber =
                    (prefixNonZeroNumber[right] - prefixContribution + MOD) % MOD;

            // Sum of digits in x = sum of digits in substring, since zeros add nothing
            long digitSum = prefixDigitSum[right] - prefixDigitSum[left];

            answer[i] = (int) ((queryNumber * digitSum) % MOD);
        }

        return answer;
    }
}