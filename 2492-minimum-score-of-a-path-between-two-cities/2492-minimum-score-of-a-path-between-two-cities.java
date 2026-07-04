class Solution {
    public int minScore(int n, int[][] roads) {
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for (int[] road : roads) {
            int u = road[0], v = road[1], d = road[2];
            graph[u].add(new int[]{v, d});
            graph[v].add(new int[]{u, d});
        }

        boolean[] vis = new boolean[n + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        vis[1] = true;

        int ans = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int u = q.poll();

            for (int[] nei : graph[u]) {
                int v = nei[0];
                int d = nei[1];

                ans = Math.min(ans, d);

                if (!vis[v]) {
                    vis[v] = true;
                    q.offer(v);
                }
            }
        }

        return ans;
    }
}