public class PowerXn {
    public static double power(double x, int n) {
        if (x == 0 && n == 0) {
            return 0.0;
        }
        if (x == 1) {
            return 1.0;
        }
        if (n == 1) {
            return x;
        }
        if (n == 0) {
            return 1.0;
        }
        if (x == 0) {
            return 0.0;
        }
        if (x == -1 && n % 2 == 0) {
            return 1.0;
        }
        if (x == -1 && n % 2 != 0) {
            return -1.0;
        }
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        double ans = 1.0;
        while (n > 0) {
            if (n % 2 == 1) {
                ans *= x;
            }
            x *= x;
            n /= 2;
        }
        return ans;

    }

    public static void main(String[] args) {
        System.out.println(power(0, 0));
    }
}
