import java.util.*;

public class positiveornegative {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int num = sc.nextInt();
            if (num > 0) {
                System.out.println("The given number is positive");
            } else {
                System.out.println("The given number is less than equal to zero");
            }
        }

    }
}