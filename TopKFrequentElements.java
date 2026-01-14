
import java.util.*;

class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        ArrayList<Integer> keyl = new ArrayList<>();
        ArrayList<Integer> valuel = new ArrayList<>();
        int[] sol = new int[k];
        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            if (hm.containsKey(nums[i])) {
                int value = hm.get(nums[i]);
                hm.put(nums[i], value + 1);
            } else {
                hm.put(nums[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            keyl.add(key);
            valuel.add(value);

        }
        int n = keyl.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (valuel.get(i - 1) < valuel.get(i)) {
                    int temp1 = valuel.get(i - 1);
                    int temp2 = keyl.get(i - 1);
                    valuel.set(i - 1, valuel.get(i));
                    keyl.set(i - 1, keyl.get(i));

                    valuel.set(i, temp1);
                    keyl.set(i, temp2);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);
        for (int i = 0; i < k; i++) {
            sol[i] = keyl.get(i);
        }

        return sol;

    }
}