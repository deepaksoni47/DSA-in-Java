package sorting;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingInterval {
    public int minRemoval(int intervals[][]) {
        // code here
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int prevEnd = intervals[0][1];
        int ans = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < prevEnd) {
                ans++;
            } else {
                prevEnd = intervals[i][1];
            }
        }
        return ans;
    }
}
