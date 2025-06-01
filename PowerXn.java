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

    public static double fastExpo(double a, int n) {
        if (n < 0) {
            a = 1 / a;
            n = -n;
        }
        double ans = 1.0;
        while (n > 0) {
            if ((n & 1) != 0) {
                ans *= a;
            }
            a *= a;
            n = n >> 1;
        }
        return ans;
    }

    public static int modularExpo(int a, int b, int m) {
        int result = 1;
        a = a % m;
        while (b > 0) {
            if (b % 2 == 1) {
                result = (result * a) % m;
            }
            a = (a * a) % m;
            b = b / 2;
        }
        return result;
    }

    public static void main(String[] args) {
        // System.out.println(power(0, 0));
        System.out.println(fastExpo(2, -2));
    }
}
