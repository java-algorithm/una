/**
 * 프로그래머스 압축
 * HashMap 사용
 */

import java.util.*;

class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap<>();
        List<Integer> answer = new ArrayList<>();
        
        char alphabet = 'A';
        for (int i = 0; i < 26; i++) {
            map.put(String.valueOf((char)(alphabet + i)), i + 1);
        }
        
        int index = 0;
        while (index < msg.length()) {
            String w = String.valueOf(msg.charAt(index));
            int next = index + 1;
            
            while (next <= msg.length() && map.containsKey(msg.substring(index, next))) {
                w = msg.substring(index, next);
                next++;
            }
            
            answer.add(map.get(w));
            
            if (next <= msg.length()) {
                map.put(msg.substring(index, next), map.size() + 1);
            }

            index += w.length();
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
