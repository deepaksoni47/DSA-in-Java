/*[Expected Approach 1] Using Juggling Algorithm - O(n) Time and O(1) Space
The idea is to use Juggling Algorithm which is based on rotating elements in cycles. Each cycle is independent and represents a group of elements that will shift among themselves during the rotation. If the starting index of a cycle is i, then next elements of the cycle can be found at indices (i + d) % n, (i + 2d) % n, (i + 3d) % n ... and so on till we return to the original index i.

So for any index i, we know that after rotation, the element that will occupy this position is arr[(i + d) % n]. Consequently, for every index in the cycle, we will place the element that should be in that position after the rotation is completed.

Please refer Juggling Algorithm for Array Rotation to know more about the implementation.

Time Complexity: O(n)
Auxiliary Space: O(1) 

[Expected Approach 2] Using Reversal Algorithm - O(n) Time and O(1) Space
The idea is based on the observation that if we left rotate the array by d positions, the last (n - d) elements will be at the front and the first d elements will be at the end.

Reverse the subarray containing the first d elements of the array.
Reverse the subarray containing the last (n - d) elements of the array.
Finally, reverse all the elements of the array.
Working: */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}