package Deque;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumInTheCurrentSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k == 0)
            return new int[0];

        Deque<Integer> dq = new ArrayDeque<>(); // stores indices
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int idx = 0;

        for (int i = 0; i < n; i++) {

            // 1. Remove indices out of the current window
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // 2. Maintain decreasing order
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            dq.offerLast(i);

            // 3. Add max to result when window is valid
            if (i >= k - 1) {
                result[idx++] = nums[dq.peekFirst()];
            }
        }
        return result;
    }
}
/*
 * 1. First: understand the brute-force reality
 * 
 * Problem:
 * For every window of size k, find the maximum.
 * 
 * Your first natural thought:
 * 
 * For each window:
 * scan k elements
 * find max
 * 
 * Cost:
 * 
 * Number of windows ≈ n
 * 
 * Work per window = k
 * 
 * ⟹ O(n · k) → unacceptable when n = 10^5
 * 
 * So you must reuse work when the window slides.
 * 
 * That is the key insight.
 * 
 * 2. What actually changes when the window moves?
 * 
 * Window moves right by one:
 * 
 * One element leaves (leftmost)
 * 
 * One element enters (rightmost)
 * 
 * Ask yourself:
 * 
 * Can I update the maximum using only these two facts?
 * 
 * 3. Why a single “current max” variable fails
 * 
 * Suppose you store:
 * 
 * currentMax
 * 
 * 
 * When the window slides:
 * 
 * If the new element is bigger → easy, update
 * 
 * But if the leaving element was the max → you are blind
 * You must rescan the whole window
 * 
 * So you need a structure that:
 * 
 * Knows who can become max in the future
 * 
 * Can discard useless elements early
 * 
 * 4. Critical observation (this is the breakthrough)
 * 
 * If you have numbers:
 * 
 * [3, 1, 2]
 * 
 * 
 * Does 1 matter if 2 comes after it?
 * 
 * No.
 * Because:
 * 
 * 2 is to the right
 * 
 * 2 is greater
 * 
 * 1 can never be max again
 * 
 * General rule:
 * 
 * Any number smaller than a newer, larger number is useless forever
 * 
 * This single rule leads directly to a monotonic deque.
 * 
 * 5. What is a deque (in simple words)
 * 
 * A deque is just a list where you can:
 * 
 * add/remove from front
 * 
 * add/remove from back
 * 
 * That’s it.
 * 
 * No magic.
 * 
 * 6. What do we store in the deque?
 * 
 * Indices, not values.
 * 
 * Why?
 * 
 * To know when an element falls out of the window
 * 
 * Values alone don’t give position
 * 
 * So deque = indices of useful elements
 * 
 * 7. The core idea (memorize this)
 * 
 * We maintain a deque such that:
 * 
 * Values are in decreasing order
 * 
 * Front always holds the maximum
 * 
 * Only elements inside the window exist
 * 
 * This is called a monotonic decreasing deque.
 * 
 * 8. How to think step-by-step (mental algorithm)
 * 
 * For every index i:
 * 
 * Step 1: Remove out-of-window elements
 * 
 * If the front index is:
 * 
 * <= i - k
 * 
 * 
 * it’s outside → remove it
 * 
 * Step 2: Remove useless smaller elements
 * 
 * While:
 * 
 * nums[lastIndexInDeque] <= nums[i]
 * 
 * 
 * remove from back
 * 
 * Why?
 * Because the new element:
 * 
 * is bigger
 * 
 * is newer
 * 
 * dominates the smaller one forever
 * 
 * Step 3: Insert current index
 * 
 * Push i at the back
 * 
 * Step 4: Record answer
 * 
 * Once the first window is complete (i >= k - 1):
 * 
 * The front of deque is the max
 * 
 * 9. Dry run (this is where understanding locks in)
 * 
 * Example:
 * 
 * nums = [1, 3, -1, -3, 5]
 * k = 3
 * 
 * 
 * We store indices, but I’ll show values for clarity.
 * 
 * i = 0 → 1
 * 
 * Deque: [1]
 * 
 * i = 1 → 3
 * 
 * Remove smaller from back:
 * 
 * 1 < 3 → remove
 * 
 * Deque: [3]
 * 
 * i = 2 → -1
 * 
 * -1 is smaller than 3 → keep
 * 
 * Deque: [3, -1]
 * Window complete → max = 3
 * 
 * i = 3 → -3
 * 
 * nothing removed
 * 
 * front still valid
 * 
 * Deque: [3, -1, -3]
 * Max = 3
 * 
 * i = 4 → 5
 * 
 * Remove smaller from back:
 * 
 * -3 < 5 → remove
 * 
 * -1 < 5 → remove
 * 
 * 3 < 5 → remove
 * 
 * Deque: [5]
 * Max = 5
 * 
 * 10. Why this is O(n) (very important)
 * 
 * Each element:
 * 
 * enters deque once
 * 
 * leaves deque once
 * 
 * No element is processed more than twice.
 * 
 * ⟹ O(n) guaranteed
 */