
public class DecimalToBinary {
    public static int dectobin(int n) {
        int pow = 0;
        int bin = 0;
        int rem = 0;
        while (n > 0) {
            rem = n % 2;
            bin = bin + (rem * (int) Math.pow(10, pow));
            n /= 2;
            pow++;
        }
        return bin;
    }

    public static void main(String[] args) {
        System.out.println(dectobin(57));
    }
}
