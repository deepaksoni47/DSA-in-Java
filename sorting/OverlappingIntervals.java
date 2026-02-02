package sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class OverlappingIntervals {
    public ArrayList<int[]> mergeOverlap(int[][] arr) {
        // Code here
        ArrayList<int[]> sol = new ArrayList<>();

        if (arr == null || arr.length == 0) {
            return sol;
        }
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));
        int start = arr[0][0];
        int end = arr[0][1];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i][0] <= end) {
                end = Math.max(end, arr[i][1]);
            } else {
                sol.add(new int[] { start, end });
                start = arr[i][0];
                end = arr[i][1];
            }
        }
        sol.add(new int[] { start, end });
        return sol;
    }
}
