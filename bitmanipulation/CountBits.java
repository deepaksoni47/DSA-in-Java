public class CountBits {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }

        return ans;
    }
}
/*
 * Mathematical Relationship
 * The number of 1's in the binary representation of a number i can be derived
 * from the number of 1's in the binary representation of i/2 (which is i
 * right-shifted by 1) plus the least significant bit (i & 1). This relationship
 * allows us to build the solution iteratively.
 * countBits(i) = countBits(i >> 1) + (i & 1)
 * Example:
 * For i = 5 (binary 101):
 * - i >> 1 = 2 (binary 10), which has 1 one.
 * - i & 1 = 1 (since the least significant bit of 5 is 1).The total number of
 * 1's in 5 is countBits(2) + 1 =
 * 1 + 1 = 2.
 * Time Complexity: O(n) Space Complexity: O(n)
 */
