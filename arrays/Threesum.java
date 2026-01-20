package arrays;

import java.util.*;

public class Threesum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> sol = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // Stop if the current number is greater than 0
            if (nums[i] > 0)
                break;

            // Skip duplicates for i
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int l = i + 1;
            int r = nums.length - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (sum == 0) {
                    sol.add(Arrays.asList(nums[i], nums[l], nums[r]));

                    l++;
                    r--;

                    // Skip duplicates for l
                    while (l < r && nums[l] == nums[l - 1])
                        l++;

                    // Skip duplicates for r
                    while (l < r && nums[r] == nums[r + 1])
                        r--;

                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return sol;
    }
}
