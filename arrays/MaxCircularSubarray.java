package arrays;

import java.util.*;

class MaxCircularSubarray {
    public int maxCircularSum(int arr[]) {

        int n = arr.length;

        // Step 1: Initialize variables for Kadane's Algorithm
        int currentMax = arr[0]; // For maximum subarray sum
        int maxSum = arr[0];

        int currentMin = arr[0]; // For minimum subarray sum
        int minSum = arr[0];

        int totalSum = arr[0]; // Total sum of array

        // Step 2: Traverse the array
        for (int i = 1; i < n; i++) {

            // Kadane for maximum subarray sum
            currentMax = Math.max(arr[i], currentMax + arr[i]);
            maxSum = Math.max(maxSum, currentMax);

            // Kadane for minimum subarray sum
            currentMin = Math.min(arr[i], currentMin + arr[i]);
            minSum = Math.min(minSum, currentMin);

            // Compute total sum
            totalSum += arr[i];
        }

        // Step 3: Edge case — all numbers are negative
        // In this case, circular sum logic breaks
        if (maxSum < 0) {
            return maxSum;
        }

        // Step 4: Circular max sum formula
        int circularMax = totalSum - minSum;

        // Step 5: Return the best of normal and circular
        return Math.max(maxSum, circularMax);
    }
}
