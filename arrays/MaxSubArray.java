import java.util.*;

public class MaxSubArray {
    public static int brute(int arr[]) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    public static int prefix(int arr[]) {
        int prefix[] = new int[arr.length];
        prefix[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefix[i] = prefix[i - 1] + arr[i];

        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = i == 0 ? prefix[j] : prefix[j] - prefix[i - 1];
                if (sum > max) {
                    max = sum;
                }
            }
        }
        return max;
    }

    public static int kadanes(int arr[]) {
        int max = arr[0];
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum = Math.max(arr[i], sum + arr[i]);
            max = Math.max(sum, max);
        }
        return max;

    }

    public static int kadanesPos(int arr[]) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            if (sum < 0) {
                sum = 0;
            }
            max = Math.max(sum, max);
        }
        return max;
    }

    public static void main(String[] args) {
        int arr[] = { 6, -4, 7, -9, 2 };
        System.out.println(kadanes(arr));
    }
}
