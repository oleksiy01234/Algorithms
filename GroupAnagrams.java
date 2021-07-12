import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    // approach 1: inefficient, slower than 95%
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();

        for (String s : strs) {
            int groupIndex = -1;

            for (int i = 0; i < res.size(); i++) {
                if (areAnagrams(res.get(i).get(0), s)) {
                    groupIndex = i;
                    break;
                }
            }

            if (groupIndex != -1) {
                res.get(groupIndex).add(s);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(s);
                res.add(newList);
            }
        }

        return res;
    }

    private boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        Map<Character, Integer> map1 = new HashMap<>();
        for (char c : s1.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        for (char c : s2.toCharArray()) {
            if (!map1.containsKey(c)) {
                return false;
            }

            map1.put(c, map1.get(c) - 1);
            map1.remove(c, 0);
        }

        return map1.isEmpty();
    }

    // approach 2: map
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            String sortedS = sortString(s);

            List<String> anagrams = map.getOrDefault(sortedS, new ArrayList<>());
            anagrams.add(s);
            map.put(sortedS, anagrams);
        }

        return new ArrayList<>(map.values());
    }

    private String sortString(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
