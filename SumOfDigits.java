public class SumOfDigits {
    public static int sumDig(int n) {
        int sum = 0;
        int last = 0;
        while (n > 0) {
            last = n % 10;
            sum += last;
            n /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(sumDig(5555));
    }
}
