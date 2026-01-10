public class SecondLargest {
    public int getSecondLargest(int[] arr) {
        int lg = -1;
        int slg = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > lg) {
                slg = lg;
                lg = arr[i];
            }

            else if (arr[i] < lg && arr[i] > slg) {
                slg = arr[i];
            }
        }
        return slg;
    }
}
