
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

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<Integer> topKFrequentv2(int[] nums, int k) {

        List<Integer>[] bucket = (List<Integer>[]) new List[nums.length + 1];
        Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }
}