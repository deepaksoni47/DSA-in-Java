class Solution {

    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums)
            max = Math.max(max, x);

        int[] cnt = new int[max + 1];
        for (int x : nums)
            cnt[x]++;

        long[] exact = new long[max + 1];

        // exact[g] = pairs with gcd exactly g
        for (int g = max; g >= 1; g--) {

            long c = 0;

            for (int m = g; m <= max; m += g)
                c += cnt[m];

            long pairs = c * (c - 1) / 2;

            for (int m = g + g; m <= max; m += g)
                pairs -= exact[m];

            exact[g] = pairs;
        }

        long[] prefix = new long[max + 1];

        for (int i = 1; i <= max; i++)
            prefix[i] = prefix[i - 1] + exact[i];

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            long q = queries[i];

            int lo = 1, hi = max;

            while (lo < hi) {
                int mid = (lo + hi) / 2;

                if (prefix[mid] > q)
                    hi = mid;
                else
                    lo = mid + 1;
            }

            ans[i] = lo;
        }

        return ans;
    }
}