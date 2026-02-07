package BinarySearch;

public class PeakElement {
    public int peakElement(int[] arr) {
        // code here
        if (arr.length == 1) {
            return 0;
        }
        // best case code tc is O(logn) refer github for better solution
        if (arr[0] > arr[1])
            return 0;
        int n = arr.length;

        for (int i = 1; i < n - 1; i++) {
            if (arr[i] > arr[i + 1] && arr[i] > arr[i - 1]) {
                return i;
            }
        }
        if (arr[n - 1] > arr[n - 2]) {
            return n - 1;
        }
        return -1;
    }

    public int peakElementOptimal(int[] arr) {
        int low = 0;
        int high = arr.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] > arr[mid + 1]) {
                // Peak is in the left half (including mid)
                high = mid;
            } else {
                // Peak is in the right half
                low = mid + 1;
            }
        }

        return low; // or high, both are same at this point
    }
}
