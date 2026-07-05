class Solution {

    int MOD = 1_000_000_007;

    public int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        for (int[] row : score)
            Arrays.fill(row, -1);

        score[0][0] = 0;
        ways[0][0] = 1;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (board.get(i).charAt(j) == 'X')
                    continue;

                if (i == 0 && j == 0)
                    continue;

                int best = -1;
                long cnt = 0;

                int[][] prev = {
                        {i - 1, j},
                        {i, j - 1},
                        {i - 1, j - 1}
                };

                for (int[] p : prev) {

                    int r = p[0];
                    int c = p[1];

                    if (r < 0 || c < 0)
                        continue;

                    if (score[r][c] == -1)
                        continue;

                    if (score[r][c] > best) {
                        best = score[r][c];
                        cnt = ways[r][c];
                    } else if (score[r][c] == best) {
                        cnt = (cnt + ways[r][c]) % MOD;
                    }
                }

                if (best == -1)
                    continue;

                char ch = board.get(i).charAt(j);

                int val = 0;

                if (Character.isDigit(ch))
                    val = ch - '0';

                score[i][j] = best + val;
                ways[i][j] = (int) (cnt % MOD);
            }
        }

        if (ways[n - 1][n - 1] == 0)
            return new int[]{0, 0};

        return new int[]{
                score[n - 1][n - 1],
                ways[n - 1][n - 1]
        };
    }
}