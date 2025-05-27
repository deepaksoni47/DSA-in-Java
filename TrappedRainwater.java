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

    public static void main(String[] args) {

    }

}
