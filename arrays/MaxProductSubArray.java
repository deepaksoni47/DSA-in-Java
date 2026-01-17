import java.util.*;

public class MaxProductSubArray {
    int maxProduct(int[] arr) {
        int mp = arr[0];
        int msf = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            if (curr < 0) {
                int temp = mp;
                mp = msf;
                msf = temp;
            }
            mp = Math.max(curr, curr * mp);
            msf = Math.min(curr, curr * msf);
            max = Math.max(mp, max);
        }
        return max;
    }
}
