import java.util.Arrays;

class minimisetheheights3351 {
    public int getMinDiff(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;
        int ans = arr[n - 1] - arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] - k < 0)
                continue;
            int minHeight = Math.min(arr[0] + k, arr[i] - k);
            int maxHeight = Math.max(arr[i - 1] + k, arr[n - 1] - k);
            ans = Math.min(ans, maxHeight - minHeight);
        }
        return ans;

    }
}
/*
 * Example Dry Run
 * Input:
 * arr = [1, 5, 8, 10]
 * k = 2
 * 
 * 
 * Sorted: [1, 5, 8, 10]
 * 
 * Initial ans = 9
 * 
 * i = 1
 * 
 * Left: [1] → +2 → 3
 * Right: [5,8,10] → -2 → [3,6,8]
 * 
 * min = min(3,3) = 3
 * max = max(3,8) = 8
 * diff = 5
 * 
 * i = 2
 * 
 * Left: [1,5] → +2 → [3,7]
 * Right: [8,10] → -2 → [6,8]
 * 
 * min = min(3,6) = 3
 * max = max(7,8) = 8
 * diff = 5
 * 
 * i = 3
 * 
 * Left: [1,5,8] → +2 → [3,7,10]
 * Right: [10] → -2 → [8]
 * 
 * min = min(3,8) = 3
 * max = max(10,8) = 10
 * diff = 7
 * 
 * Final Answer = 5
 * 
 * Time & Space
 * Metric Value
 * Time O(n log n)
 * Space O(1)
 */