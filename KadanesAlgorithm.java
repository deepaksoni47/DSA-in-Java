import java.util.*;

class KadanesAlgorithm {
    int maxSubarraySum(int[] arr) {
        int cs = arr[0];
        int ms = arr[0];
        for (int i = 1; i < arr.length; i++) {

            cs = Math.max(arr[i], cs + arr[i]);
            ms = Math.max(ms, cs);

        }
        return ms;

    }
}
