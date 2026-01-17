import java.util.HashSet;

class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> s = new HashSet<>();
        int max = 0;
        for (int num : nums) {
            s.add(num);
        }
        for (int num : s) {
            if (!(s.contains(num - 1))) {
                int curr = num;
                int count = 1;
                while (s.contains(curr + 1)) {
                    count++;
                    curr = curr + 1;
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }
}