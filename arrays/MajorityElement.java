import java.util.Arrays;

public class MajorityElement {
    public static int brute(int arr[]) {
        for (int val : arr) {
            int freq = 0;
            for (int el : arr) {
                if (el == val) {
                    freq++;
                }
            }
            if (freq > arr.length / 2) {
                return val;
            }
        }
        return -1;
    }

    public static int sorted(int arr[]) {
        Arrays.sort(arr);
        int freq = 1, ans = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                freq++;
            } else {
                freq = 1;
                ans = arr[i];
            }
            if (freq > arr.length / 2) {
                return ans;
            }
        }

        return -1;
    }

    public static int MooresAlgorithm(int arr[]) {
        int freq = 0, ans = 0;
        for (int num : arr) {
            if (freq == 0) {
                ans = num;
            }
            if (ans == num) {
                freq++;
            } else {
                freq--;
            }
        }
        freq = 0;
        for (int el : arr) {
            if (ans == el) {
                freq++;
            }
        }
        if (freq > arr.length / 2) {
            return ans;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int arr[] = { 1, 2, 2, 1, 2, 1 };
        System.out.println(MooresAlgorithm(arr));
    }
}
