class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rank = new HashMap<>();
        int currRank = 1;

        for (int num : sorted) {
            if (!rank.containsKey(num)) {
                rank.put(num, currRank++);
            }
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = rank.get(arr[i]);
        }

        return ans;
    }
}