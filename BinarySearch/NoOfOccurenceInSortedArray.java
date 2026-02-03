package BinarySearch;

public class NoOfOccurenceInSortedArray {
    int countFreq(int[] arr, int target) {
        // code here
        int first = findFirst(arr, target);
        if (first == -1)
            return 0;
        int last = findLast(arr, target);
        return (last - first + 1);
    }

    int findFirst(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int first = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                first = mid;
                high = mid - 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return first;
    }

    int findLast(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int last = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                last = mid;
                low = mid + 1;
            } else if (arr[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return last;
    }
}
