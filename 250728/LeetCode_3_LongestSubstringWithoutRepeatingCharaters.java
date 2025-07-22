// 슬라이딩 윈도우만 사용 : 10ms

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;

        int[] alphabet = new int[128];

        int left = 0;
        int right = 1;

        alphabet[(byte)s.charAt(left)]++;

        int max = 1;

        while (right < s.length()) {
            if (alphabet[(byte)s.charAt(right)] > 0) {
                alphabet[(byte)s.charAt(left++)]--;
            } else {
                alphabet[(byte)s.charAt(right++)]++;
            }
            
            max = Math.max(max, right - left);
        }

        return max;
    }
}

// 슬라이딩 윈도우 + Set 사용 : 6ms

import java.util.*;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;

        Set<Character> set = new HashSet<>();

        int left = 0;
        int right = 1;

        set.add(s.charAt(left));

        int max = 1;

        while (right < s.length()) {
            if (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left++));
            } else {
                set.add(s.charAt(right++));
            }
            
            max = Math.max(max, right - left);
        }

        return max;
    }
}
