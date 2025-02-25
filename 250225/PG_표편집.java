import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        
        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        
        next[n - 1] = -1;
        
        Stack<Integer> removed = new Stack<>();
        
        for (String c : cmd) {
            char command = c.charAt(0);
            
            if (command == 'U') {
                int move = Integer.parseInt(c.substring(2));
                while (move-- > 0) k = prev[k];
            } else if (command == 'D') {
                int move = Integer.parseInt(c.substring(2));
                while (move-- > 0) k = next[k];
            } else if (command == 'C') {
                removed.push(k);
                if (prev[k] != -1) next[prev[k]] = next[k];
                if (next[k] != -1) prev[next[k]] = prev[k];

                k = (next[k] != -1) ? next[k] : prev[k];
            } else if (command == 'Z') {
                int restore = removed.pop();
                if (prev[restore] != -1) next[prev[restore]] = restore;
                if (next[restore] != -1) prev[next[restore]] = restore;
            }
        }
        
        StringBuilder sb = new StringBuilder("O".repeat(n));
        while (!removed.isEmpty()) {
            sb.setCharAt(removed.pop(), 'X');
        }

        return sb.toString();
    }
}
