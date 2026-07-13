class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();

        String digits = "123456789";

        for (int len = 2; len <= 9; len++) {
            for (int i = 0; i + len <= 9; i++) {
                int num = Integer.parseInt(digits.substring(i, i + len));

                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        Collections.sort(ans);
        return ans;
    }
}
//mathematical generation
/*
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();

        for (int start = 1; start <= 9; start++) {
            int num = start;

            for (int next = start + 1; next <= 9; next++) {
                num = num * 10 + next;

                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        Collections.sort(ans);
        return ans;
    }
}
approach 3 bfs
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= 9; i++) {
            q.offer(i);
        }

        while (!q.isEmpty()) {
            int num = q.poll();

            if (num >= low && num <= high) {
                ans.add(num);
            }

            int last = num % 10;

            if (last < 9) {
                int next = num * 10 + (last + 1);

                if (next <= high) {
                    q.offer(next);
                }
            }
        }

        return ans;
    }
}
*/
