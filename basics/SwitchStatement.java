import java.util.Scanner;

public class SwitchStatement {
    public static void main(String[] args) {
        System.out.println("Enter the day number 1-7");
        try (Scanner sc = new Scanner(System.in)) {
            int day = sc.nextInt();
            /*
             * in java 14 or later
             * case 1 -> "Monday;
             * case 2 -> "Tuesday"
             * Switch statement will return a value no neeed for break statement
             */
            switch (day) {
                case 1:
                    System.out.println("Monday");
                    break;
                case 2:
                    System.out.println("Tuesday");
                    break;
                case 3:
                    System.out.println("Wednesday");
                    break;
                case 4:
                    System.out.println("Thursday");
                    break;
                case 5:
                    System.out.println("Friday");
                    break;
                case 6:
                    System.out.println("Saturday");
                    break;
                case 7:
                    System.out.println("Sunday");
                    break;
                default:
                    System.out.println("Invalid Input!");

            }
        }
    }

}
