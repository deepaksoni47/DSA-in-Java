public class MoveZeroToEnd {
    public void moveZeroes(int[] arr) {
        int index = 0; // position for next non-zero

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                arr[index++] = arr[i];
            }
        }

        // Fill remaining positions with 0
        while (index < arr.length) {
            arr[index++] = 0;
        }
    }
}
