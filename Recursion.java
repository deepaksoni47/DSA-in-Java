public class Recursion {
    static String digits[] = {
            "zero", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine"
    };

    public static void printDec(int n) {
        if (n == 1) {
            System.out.println(n);
            return;
        }
        System.out.print(n + " ");
        printDec(n - 1);
    }

    public static void printInc(int n) {
        if (n == 1) {
            System.out.print(n + " ");
            return;
        }
        printInc(n - 1);
        System.out.print(n + " ");
    }

    public static int fact(int n) {
        if (n == 0) {
            return 1;
        }
        return n * fact(n - 1);
    }

    public static int sumN(int n) {
        if (n == 1) {
            return 1;
        }
        return n + sumN(n - 1);
    }

    public static int fibo(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fibo(n - 1) + fibo(n - 2);
    }

    public static boolean isSorted(int arr[], int i) {
        if (i == arr.length - 1) {
            return true;
        }
        if (arr[i] > arr[i + 1]) {
            return false;
        }
        return isSorted(arr, i + 1);
    }

    public static int firstOcc(int arr[], int i, int key) {
        if (i == arr.length) {
            return -1;
        }
        if (arr[i] == key) {
            return i;
        }
        return firstOcc(arr, i + 1, key);
    }

    public static int lastOcc(int arr[], int i, int key) {
        if (i == arr.length) {
            return -1;
        }
        int isFound = lastOcc(arr, i + 1, key);
        if (isFound == -1 && arr[i] == key) {
            return i;
        }
        return isFound;
    }

    public static int pow(int x, int n) {
        if (n == 0) {
            return 1;
        }
        int halfPowerSq = pow(x, n / 2);
        halfPowerSq *= halfPowerSq;
        if (n % 2 != 0) {
            halfPowerSq = x * halfPowerSq;
        }
        return halfPowerSq;
    }

    public static int tileWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return tileWays(n - 1) + tileWays(n - 2);
    }

    public static void removeDup(String str, int idx, StringBuilder newStr, boolean map[]) {
        if (idx == str.length()) {
            System.out.println(newStr);
            return;
        }
        char currChar = str.charAt(idx);
        if (map[currChar - 'a'] == true) {
            removeDup(str, idx + 1, newStr, map);
        } else {
            map[currChar - 'a'] = true;
            removeDup(str, idx + 1, newStr.append(currChar), map);
        }
    }

    public static int pair(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return pair(n - 1) + (n - 1) * pair(n - 2);
    }

    public static void binaryStr(int n, int lastPlace, String str) {
        if (n == 0) {
            System.out.println(str);
            return;
        }
        binaryStr(n - 1, 0, str + "0");
        if (lastPlace == 0) {
            binaryStr(n - 1, 1, str + "1");
        }
    }

    public static int gridPathWays(int n, int m) {
        if (n == 1 || m == 1) {
            return 1;
        } else {
            return gridPathWays(n, m - 1) + gridPathWays(n - 1, m);
        }
    }

    public static int partitionWays(int n, int m) {
        if (n == 0) {
            return 1;
        } else if (m == 0 || n < 0) {
            return 0;
        } else {
            return partitionWays(n - m, m) + partitionWays(n, m - 1);
        }
    }

    public static int lengthstr(String str) {
        if (str.length() == 0) {
            return 0;
        }
        return lengthstr(str.substring(1)) + 1;
    }

    public static int countPalinSubstr(String str, int i, int j, int n) {
        if (n == 1) {
            return 1;
        }
        if (n <= 0) {
            return 0;
        }
        int res = countPalinSubstr(str, i + 1, j, n - 1) + countPalinSubstr(str, i, j - 1, n - 1)
                - countPalinSubstr(str, i + 1, j - 1, n - 2);
        if (str.charAt(i) == str.charAt(j)) {
            res++;
        }
        return res;
    }

    public static void towerOfHanoi(int n, int start, int end) {
        if (n == 1) {
            System.out.println(start + "->" + end);
        } else {
            int other = 6 - (start + end);
            towerOfHanoi(n - 1, start, other);
            System.out.println(start + "->" + end);
            towerOfHanoi(n - 1, other, end);
        }

    }

    public static void allOccurences(int arr[], int key, int i) {
        if (i == arr.length) {
            return;
        }

        if (arr[i] == key) {
            System.out.print(i + " ");
        }

        allOccurences(arr, key, i + 1);
    }

    public static void printDigits(int number) {
        if (number == 0) {
            return;
        }

        int lastDigit = number % 10;
        printDigits(number / 10);
        System.out.print(digits[lastDigit] + " ");
    }

    public static void main(String[] args) {
        // printInc(10);
        // System.out.println(fibo(5));
        // for (int i = 0; i <= 6; i++) {
        // System.out.print(fibo(i) + " ");
        // }
        // int arr[] = { 1, 2, 3, 7, 3, 6 };

        // System.out.println(isSorted(arr, 0));
        // System.out.println(lastOcc(arr, 0, 3));
        // System.out.println(tileWays(3));
        // removeDup("deepaka", 0, new StringBuilder(""), new boolean[26]);
        // binaryStr(3, 0, "");
        // System.out.println(partitionWays(3, 1));
        // System.out.println(lengthstr("deepak"));
        // System.out.println(countPalinSubstr("abacda", 0, 5, 6));
        // towerOfHanoi(3, 1, 3);
        printDigits(1947);
    }
}
