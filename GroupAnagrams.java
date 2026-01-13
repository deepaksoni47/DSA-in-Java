import java.util.*;

class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            int[] freq = new int[26];

            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                key.append('#');
                key.append(freq[i]);
            }

            String signature = key.toString();

            map.putIfAbsent(signature, new ArrayList<>());
            map.get(signature).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
