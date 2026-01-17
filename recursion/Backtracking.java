public class Backtracking {
    public static void changeArray(int arr[], int i, int val) {
        if (i == arr.length) {
            printArr(arr);
            return;

        }
        arr[i] = val;
        changeArray(arr, i + 1, val);
        arr[i] = arr[i] - 2;

    }

    public static void findSubsets(String str, int i, String ans) {
        if (i == str.length()) {
            if (ans.length() == 0) {
                System.out.println("null");
            } else {
                System.out.println(ans);
            }
            return;
        }
        findSubsets(str, i + 1, ans);
        findSubsets(str, i + 1, ans + str.charAt(i));
    }

    public static void findPermutations(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
        }
        for (int i = 0; i < str.length(); i++) {
            char curr = str.charAt(i);
            String newstr = str.substring(0, i) + str.substring(i + 1);
            findPermutations(newstr, ans + curr);
        }
    }

    public static void printArr(int arr[]) {
        for (int i = 0; i <= arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void nQueens(String board[][], int row) {

    }

    public static void main(String[] args) {
        // findSubsets("", 0, "");
        // findPermutations("abcd", "");
    }

}