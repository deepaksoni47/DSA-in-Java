public class BinaryToDecimal {
    public static int binToDec(int n) {
        int dec = 0;
        int pow = 0;
        while (n > 0) {
            int last = n % 10;
            dec = dec + (last * (int) Math.pow(2, pow));
            n /= 10;
            pow++;
        }
        return dec;
    }

    public static void main(String[] args) {
        System.out.println(binToDec(111001));
    }
}
