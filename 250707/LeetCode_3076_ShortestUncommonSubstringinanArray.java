import java.util.*;

/**
 * 리트코드 3076번 Shortest Uncommon Substring in an Array
 * - HashMap, ArrayList 사용
 */

class Solution {
    public String[] shortestSubstrings(String[] arr) {
        Map<String, Integer> map = new HashMap<>();
        List<String> overlappingWords = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length(); j++) {
                for (int k = j + 1; k <= arr[i].length(); k++) {
                    String str = arr[i].substring(j, k);
                    
                    if (map.containsKey(str) && !map.get(str).equals(i)) {
                        overlappingWords.add(str);
                    }
                    else map.put(str, i);
                }
            }
        }

        for (String s : overlappingWords) {
            if (map.containsKey(s)) map.remove(s);
        }

        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) list.add(new ArrayList<>());

        for (Map.Entry<String, Integer> entry : map.entrySet()) list.get(entry.getValue()).add(entry.getKey());

        String[] answer = new String[arr.length];

        for (int i = 0; i < list.size(); i++) {
            List<String> words = list.get(i);
            
            if (words.size() == 0) answer[i] = "";
            else {
                Collections.sort(words, (a, b) -> {
                    if (a.length() != b.length()) return a.length() - b.length(); // 1. 짧은 순
                    return 
                    a.compareTo(b); // 2. 알파벳 순
                });
                answer[i] = words.get(0);
            }
        }


        return answer;
    }
}
