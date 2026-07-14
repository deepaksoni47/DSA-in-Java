class Solution {

    static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int m = 0;
        for (int num : nums) {
            m = Math.max(m, num);
        }

        int[][] dp = new int[m + 1][m + 1];
        dp[0][0] = 1;

        for (int num : nums) {
            int[] g = new int[m + 1];
            for (int i = 0; i <= m; i++) {
                g[i] = gcd(i, num);
            }

            int[][] ndp = new int[m + 1][m + 1];

            for (int j = 0; j <= m; j++) {
                int ng1 = g[j];

                for (int k = 0; k <= m; k++) {
                    int val = dp[j][k];
                    if (val == 0) continue;

                    int ng2 = g[k];

                    // Ignore
                    ndp[j][k] = (ndp[j][k] + val) % MOD;

                    // Put into seq1
                    ndp[ng1][k] = (ndp[ng1][k] + val) % MOD;

                    // Put into seq2
                    ndp[j][ng2] = (ndp[j][ng2] + val) % MOD;
                }
            }

            dp = ndp;
        }

        int ans = 0;
        for (int g = 1; g <= m; g++) {
            ans += dp[g][g];
            if (ans >= MOD) ans -= MOD;
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}