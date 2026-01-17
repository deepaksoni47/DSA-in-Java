public class TrappedRainwater {
    public static int trapped(int height[]) {
        int n = height.length;
        int leftMax[] = new int[n];
        leftMax[0] = height[0];
        int trapped = 0;
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);

        }
        int rightMax[] = new int[n];
        rightMax[0] = height[0];
        for (int i = n - 2; i >= 0; i++) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        for (int i = 0; i < n; i++) {
            int waterlevel = Math.min(rightMax[i], leftMax[i]);
            trapped += waterlevel - height[i];
        }
        return trapped;
    }

    public int trap(int[] height) {
        int n = height.length;

        int res = 0, l = 0, r = n - 1;
        int rMax = height[r], lMax = height[l];

        while (l < r) {
            if (lMax < rMax) {
                l++;
                lMax = Math.max(lMax, height[l]);
                res += lMax - height[l];
            } else {
                r--;
                rMax = Math.max(rMax, height[r]);
                res += rMax - height[r];
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }

}
