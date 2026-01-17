public class ProductArrayExceptSelf {
    public static int[] bestcase(int arr[]) {
        // using prefix and suffix
        int n = arr.length;
        int result[] = new int[n];
        result[0] = 1;
        // prefix calculation
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * arr[i - 1];
        }
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffix;
            suffix *= arr[i];
        }
        return result;

    }

    public static void main(String[] args) {
        int arr[] = { 5, 4, 2, 3 };
        int arr2[] = bestcase(arr);
        for (int num : arr2) {
            System.out.print(num + " ");
        }
    }
}
