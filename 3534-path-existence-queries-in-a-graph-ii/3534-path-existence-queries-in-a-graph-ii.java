class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // ---------- Step 1: sort nodes by value ----------
        int[][] arr = new int[n][2]; // {value, originalIndex}
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));

        int[] sortedVals = new int[n];
        int[] sortedPos = new int[n]; // original node -> position in sorted order

        for (int i = 0; i < n; i++) {
            sortedVals[i] = arr[i][0];
            sortedPos[arr[i][1]] = i;
        }

        // ---------- Step 2: build connected components ----------
        int[] comp = new int[n];
        int compId = 0;
        comp[0] = 0;

        for (int i = 1; i < n; i++) {
            if (sortedVals[i] - sortedVals[i - 1] > maxDiff) {
                compId++;
            }
            comp[i] = compId;
        }

        // ---------- Step 3: compute reach[i] using two pointers ----------
        int[] reach = new int[n];
        int r = 0;

        for (int i = 0; i < n; i++) {
            while (r + 1 < n && sortedVals[r + 1] - sortedVals[i] <= maxDiff) {
                r++;
            }
            reach[i] = r;

            // Keep window valid when i moves forward
            if (r < i + 1) r = i + 1;
        }

        // ---------- Step 4: binary lifting ----------
        int LOG = 18; // since n <= 1e5, 2^17 > 1e5, 18 is enough
        int[][] jump = new int[LOG][n];

        // 1 step
        for (int i = 0; i < n; i++) {
            jump[0][i] = reach[i];
        }

        // 2^k steps
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                jump[k][i] = jump[k - 1][ jump[k - 1][i] ];
            }
        }

        // ---------- Step 5: answer queries ----------
        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {
            int u = queries[qi][0];
            int v = queries[qi][1];

            int a = sortedPos[u];
            int b = sortedPos[v];

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            // Different components => no path
            if (comp[a] != comp[b]) {
                ans[qi] = -1;
                continue;
            }

            // Same node
            if (a == b) {
                ans[qi] = 0;
                continue;
            }

            int steps = 0;
            int cur = a;

            // We want the minimum steps to reach >= b.
            // Greedily take the largest power jump that still keeps us < b.
            for (int k = LOG - 1; k >= 0; k--) {
                if (jump[k][cur] < b) {
                    cur = jump[k][cur];
                    steps += (1 << k);
                }
            }

            // One final step to reach or cross b
            ans[qi] = steps + 1;
        }

        return ans;
    }
}