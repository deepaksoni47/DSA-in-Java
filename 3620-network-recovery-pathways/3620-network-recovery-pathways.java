class Solution {

    static class Edge {
        int to;
        int cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;

        List<Edge>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        int[] indegree = new int[n];
        int maxCost = 0;

        for (int[] e : edges) {
            int u = e[0], v = e[1], c = e[2];
            graph[u].add(new Edge(v, c));
            indegree[v]++;
            maxCost = Math.max(maxCost, c);
        }

        // Topological order of the DAG
        int[] topo = getTopoOrder(graph, indegree, n);

        int low = 0, high = maxCost;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (can(mid, graph, topo, online, k, n)) {
                ans = mid;
                low = mid + 1;   // try bigger minimum edge cost
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private boolean can(int minEdge, List<Edge>[] graph, int[] topo, boolean[] online, long k, int n) {
        long INF = Long.MAX_VALUE / 4;
        long[] dp = new long[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int u : topo) {
            if (dp[u] == INF) continue;

            // Offline intermediate nodes are not allowed
            // 0 and n-1 are guaranteed online, but keep condition clean
            if (u != 0 && u != n - 1 && !online[u]) continue;

            for (Edge e : graph[u]) {
                int v = e.to;
                int cost = e.cost;

                if (cost < minEdge) continue;

                // v cannot be offline if it is an intermediate node
                if (v != n - 1 && !online[v]) continue;

                long newCost = dp[u] + cost;
                if (newCost < dp[v]) {
                    dp[v] = newCost;
                }
            }
        }

        return dp[n - 1] <= k;
    }

    private int[] getTopoOrder(List<Edge>[] graph, int[] indegree, int n) {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        int[] topo = new int[n];
        int idx = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;

            for (Edge e : graph[u]) {
                int v = e.to;
                indegree[v]--;
                if (indegree[v] == 0) q.offer(v);
            }
        }

        return topo;
    }
}