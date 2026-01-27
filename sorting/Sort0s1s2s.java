package sorting;

public class Sort0s1s2s {
    public void sort012(int[] arr) {
        // code here
        int apos = -1;
        int bpos = -1;
        int cpos = -1;
        for (int a : arr) {
            if (a == 1) {
                bpos++;
            }
            if (a == 0) {
                apos++;

            }
            if (a == 2) {
                cpos++;
            }
        }
        int i = 0;
        while (i < arr.length) {
            while (apos >= 0) {
                arr[i] = 0;
                apos--;
                i++;
            }
            while (bpos >= 0) {
                arr[i] = 1;
                bpos--;
                i++;
            }
            while (cpos >= 0) {
                arr[i] = 2;
                cpos--;
                i++;
            }
        }
    }

    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--;
            }
        }
    }

}
/*
 * Best Possible Approach: Dutch National Flag Algorithm
 * Why this is the best
 * 
 * Time Complexity: O(n) (single pass)
 * 
 * Space Complexity: O(1) (in-place)
 * 
 * No counting, no extra array
 * 
 * Optimal in both theory and practice
 * 
 * Anything else is inferior.
 * How it works (brief, no fluff)
 * 
 * low → boundary for 0
 * 
 * mid → current element
 * 
 * high → boundary for 2
 * 
 * Invariant:
 * 
 * [0 … low-1] → all 0
 * 
 * [low … mid-1] → all 1
 * 
 * [high+1 … end] → all 2
 * 
 * Best Case?
 * 
 * Even in the best case, you must scan all elements.
 * So:
 * 
 * Best case = Average case = Worst case = O(n)
 * There is no faster correct algorithm.
 */
