public class Palindrome {
    public static boolean isPalindrome(int n) {
        int inp = n;
        int last = 0;
        int check = 0;
        while (n > 0) {
            last = n % 10;
            check = last + check * 10;
            n /= 10;
        }
        if (inp == check) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(12156));
    }
}
