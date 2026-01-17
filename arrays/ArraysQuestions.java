import java.util.*;

public class ArraysQuestions {
    public static int largest(int[] arr) {
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (largest < arr[i]) {
                largest = arr[i];
            }
        }
        return largest;
    }

    public static void reverse(int arr[]) {
        int first = 0, last = arr.length - 1;
        while (first < last) {
            int temp = arr[first];
            arr[first] = arr[last];
            arr[last] = temp;
            first++;
            last--;
        }
    }

    public static void pairs(int[] arr) {
        int tp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                System.out.print("(" + arr[i] + "," + arr[j] + ") ");
                tp++;
            }
            System.out.println();
        }
        System.out.println(tp);
    }

    public static void pairSum(int arr[], int target) {
        // array must be sorted
        int i = 0, j = arr.length - 1;
        while (i < j) {
            int ps = arr[i] + arr[j];
            if (ps > target) {
                j--;
            } else if (ps < target) {
                i++;
            } else {
                System.out.println("paiirs are (" + arr[i] + "," + arr[j] + ")");
                return;
            }
        }
        System.out.println("not found");

    }

    public static void subArrays(int arr[]) {
        int ts = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                for (int k = i; k <= j; k++) {
                    System.out.print(arr[k] + " ");
                }
                System.out.println();
                ts++;
            }
        }
        System.out.println(ts);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 4, 7, 9, 12 };
        // subArrays(arr);
        // pairs(arr);
        // for (int num : arr) {
        // System.out.print(num + " ");
        // }
        pairSum(arr, 16);
    }
}